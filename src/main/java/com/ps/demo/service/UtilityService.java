package com.ps.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.Data;

@Service
public class UtilityService {
	
	@Value("${verifyNum.key}")
	private String VERIFYNUM_KEY;
	
	@Autowired
	private RestTemplate restTemplate;
	
	/**
	 * Validate phone number service
	 * @param phoneNo
	 * @param countryCode
	 * @return
	 */
	public boolean validatePhoneNo(String phoneNo, String countryCode) {
		String api = "http://apilayer.net/api/validate?access_key="+VERIFYNUM_KEY
				+ "&number="+phoneNo+"&country_code="+countryCode+"&format=1";
		PhoneNoDetail phoneNoDetail = restTemplate.getForObject(api, PhoneNoDetail.class);
		return phoneNoDetail.isValid();
	}
}

@Data
class PhoneNoDetail {
    private boolean valid;
    private String carrier;
}