package com.example.demo.converter;

import java.time.ZonedDateTime;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class ZonedDateTimeWriteConverter implements Converter<ZonedDateTime, Date>{

	@Override
	public Date convert(ZonedDateTime zonedDateTime) {
		return Date.from(zonedDateTime.toInstant());
	}

}
