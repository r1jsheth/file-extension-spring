package com.filetypeIdentification.mvp1.controller;

/*
 * mvp1
 * 27/8/19
 */

import com.filetypeIdentification.mvp1.document.ExtensionRequestDTO;
import com.filetypeIdentification.mvp1.document.ExtensionResponseDTO;
import com.filetypeIdentification.mvp1.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MainController {

	@Autowired
	InformationService informationService;

	// Controller for only one extension
	@GetMapping("/search/single")
	public ExtensionResponseDTO callServiceForInformation(@RequestBody String queryExtension){
		return informationService.getInformation(queryExtension);
	}

	// Controller for more than one extension(s)
	@GetMapping("/search/multi")
	public ExtensionResponseDTO callServiceForInformation(@RequestBody ExtensionRequestDTO extensionRequestDTO){
		return informationService.getMultipleInformation(extensionRequestDTO);
	}

}
