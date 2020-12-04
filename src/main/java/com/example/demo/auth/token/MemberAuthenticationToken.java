package com.example.demo.auth.token;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class MemberAuthenticationToken extends AbstractAuthenticationToken{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final Object principal;
	private Object credentials;
	private String sessionId;
	
	
	
	public MemberAuthenticationToken(Object principal,
			Object credential, String sessionId) {
		super(null);
		this.principal = principal;
		this.credentials = credential;
		this.sessionId = sessionId;
	}
	
	public MemberAuthenticationToken(Object principal, Object credentials,
			Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		this.principal = principal;
		this.credentials = credentials;
		super.setAuthenticated(true); // must use super, as we override
	}

	public MemberAuthenticationToken(Object principal, Object credentials,
			Collection<? extends GrantedAuthority> authorities, Object detail) {
		super(authorities);
		this.principal = principal;
		this.credentials = credentials;
		super.setDetails(detail);
		super.setAuthenticated(true); // must use super, as we override
	}
	
	@Override
	public Object getCredentials() {
		return this.credentials;
	}

	@Override
	public Object getPrincipal() {
		return this.principal;
	}

}
