package com.texoit.desafio.controller;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.texoit.desafio.DesafioApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DesafioApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class AwardControllerIntegrationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;
	
	@Value("${resultado.path}")
	private String resultadoPath;

	
	private String getRootUrl() {
		return "http://localhost:" + port + "/api/v1";
	}

	@Test
	public void contextLoads() {
		assertEquals(8080, port);
	}

	@Test
	public void testGetAwards() throws IOException {
		
		
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/awards", HttpMethod.GET, entity,
				String.class);

		ObjectMapper mapper = new ObjectMapper();
		
		assertEquals(mapper.readTree(new File(resultadoPath)), mapper.readTree(response.getBody()));
	}

}
