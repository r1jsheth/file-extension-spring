package com.filetypeIdentification.mvp1.controller;

/*
 * mvp1
 * 27/8/19
 */

import com.filetypeIdentification.mvp1.document.ExtensionRequestDTO;
import com.filetypeIdentification.mvp1.document.ExtensionResponse;
import com.filetypeIdentification.mvp1.document.ExtensionResponseDTO;
import com.filetypeIdentification.mvp1.document.ExtensionResponsePageWiseDTO;
import com.filetypeIdentification.mvp1.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MainController {

	@Autowired
	InformationService informationService;

	// Controller for only one extension
	@PostMapping("/search/single")
	public ExtensionResponseDTO callServiceForInformation(@RequestParam String queryExtension){
		return informationService.getInformation(queryExtension);
	}

	// Controller for more than one extension(s)
	@PostMapping("/search/multi")
	public ExtensionResponseDTO callServiceForInformation(@RequestBody ExtensionRequestDTO extensionRequestDTO){
		return informationService.getMultipleInformation(extensionRequestDTO);
	}


	@PostMapping("/search/pagewise")
	public ExtensionResponseDTO getPageWiseInformation(@RequestParam int pageNo, @RequestParam int pageSize,
	                                                   @RequestBody ExtensionRequestDTO extensionRequestDTO){
		return informationService.getMultipleInformationPageWise(extensionRequestDTO, pageNo, pageSize);
	}


	@PostMapping("/search/pagewise/lastpage")
	public ExtensionResponsePageWiseDTO getPageWiseInformation2(@RequestParam int pageNo, @RequestParam int pageSize,
	                                                            @RequestBody ExtensionRequestDTO extensionRequestDTO) throws Exception {
		return informationService.getPageWiseResponse(extensionRequestDTO, pageNo, pageSize);
	}

	@PostMapping("/category/all")
	public List<String> listAllCategory(){
		return informationService.getAllCategory();
	}


	@PostMapping("/search/byCategory")
	public ExtensionResponseDTO callServiceForINformation(@RequestParam String category){
		return informationService.getByCategory(category);
	}


}
