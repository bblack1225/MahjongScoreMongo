package com.example.demo.auth.provider;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.auth.token.MemberAuthenticationToken;
import com.example.demo.config.ApplicationRoles;
import com.example.demo.model.Account;
import com.example.demo.model.Member;
import com.example.demo.model.Team;
import com.example.demo.service.ITeamService;
import com.example.demo.viewModel.TeamViewModel;

public class MemberAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	ITeamService teamService;
	
	@Override
	public boolean supports(Class<?> authentication) {
		return MemberAuthenticationToken.class.isAssignableFrom(authentication);
	}
	
	@Transactional
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		MemberAuthenticationToken token = (MemberAuthenticationToken)authentication;
		Account account = new Account();
		String email = token.getPrincipal().toString();
		Team team = Optional.ofNullable(teamService.findByEmail(email))
							.orElseThrow(()-> new BadCredentialsException("Team email not found"));
		
		String rawPassword = token.getCredentials().toString();
		
		if(!passwordEncoder.matches(rawPassword, team.getPassword())) {
			throw new BadCredentialsException("email or password is invalid");
		}
		
		TeamViewModel teamViewModel = new TeamViewModel();
		List<Member> players = teamService.findMembersByTeamId(team.getId());
		teamViewModel.setEmail(email);
		teamViewModel.setTeamId(team.getId());
		teamViewModel.setTeamName(team.getTeamName());
		teamViewModel.setMembersOfTeam(players);
		
		account.setId(team.getId());
		account.setAccountType(ApplicationRoles.MEMBER.name());
		account.setAccountDetails(teamViewModel);
		
		
		List<GrantedAuthority> authorities = Arrays
												.stream("ROLE_MEMBER".split(","))
												.map(SimpleGrantedAuthority::new)
												.collect(Collectors.toList());
		return new MemberAuthenticationToken(token.getPrincipal(), token.getCredentials(), authorities, account);
	}


}
