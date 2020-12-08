package com.example.demo.converter;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class ZonedDateTimeReadConverter implements Converter<Date,ZonedDateTime>{

	@Override
	public ZonedDateTime convert(Date date) {
		ZoneId zoneId = ZoneId.systemDefault();
		return date.toInstant().atZone(zoneId);
	}

}
