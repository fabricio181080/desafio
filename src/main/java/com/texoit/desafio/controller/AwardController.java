package com.texoit.desafio.controller;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.texoit.desafio.pojo.Award;
import com.texoit.desafio.service.AwardService;

@RestController
@RequestMapping("/api/v1")
public class AwardController {

	@Autowired
	private AwardService awardServices;

	@PostConstruct
	public void init() throws IOException {
			this.awardServices.init();
	}

	@GetMapping("/awards")
	public Award getAwards() {
		return this.awardServices.getAwards();
	}
}
