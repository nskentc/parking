package com.ps.demo.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UtilityServiceTest {

	@Value("${verifyNum.key}")
	private String VERIFYNUM_KEY;

	@Mock
	private RestTemplate restTemplate;

	@InjectMocks
	private UtilityService utilityService;
	
	@Before
	public void init() {
		ReflectionTestUtils.setField(utilityService, "VERIFYNUM_KEY", VERIFYNUM_KEY);
	}

	/**
	 * Test for {@link UtilityService#validatePhoneNo(String, String)}
	 */
	@Test
	public void testValidatePhoneNo() {
		String phoneNo = "9989384983";
		String countryCode = "IN";
		PhoneNoDetail phoneNoDetails = new PhoneNoDetail();
		phoneNoDetails.setValid(true);
		String api = "http://apilayer.net/api/validate?access_key=" + VERIFYNUM_KEY + "&number=" + phoneNo
				+ "&country_code=" + countryCode + "&format=1";
		when(restTemplate.getForObject(api, PhoneNoDetail.class)).thenReturn(phoneNoDetails);
		boolean isValidatePhoneNo = utilityService.validatePhoneNo(phoneNo, countryCode);
		assertEquals(true, isValidatePhoneNo);
	}
}
