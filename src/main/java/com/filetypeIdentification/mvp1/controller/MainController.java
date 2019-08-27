package com.filetypeIdentification.mvp1.controller;

/*
 * mvp1
 * 27/8/19
 */

import org.springframework.context.annotation.Description;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Description("Main Controller class for the application")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MainController {



	@GetMapping("/home")
	public String doThis(){
		String ab = "wow";
		System.out.println("hello, world!");
		return ab;
	}


}
