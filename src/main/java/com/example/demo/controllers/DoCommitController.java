package com.example.demo.controllers;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.models.DoCommitRequest;
import com.example.demo.services.DoCommitService;

/**
 * @author YoussefMahmoud
 * @created Apr 22, 2023-5:04:23 PM
 */

@RestController
@RequestMapping("doCommitController") //http://localhost:8080/doCommitController/doCommit
public class DoCommitController {

	@Autowired
	private DoCommitService doCommitService;

	@CrossOrigin
	@PostMapping(path = "/doCommit")
	public BigDecimal doCommit(@RequestBody DoCommitRequest doCommitRequest) {
		return doCommitService.doCommit(doCommitRequest);
	}

}