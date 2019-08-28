package com.filetypeIdentification.mvp1.service;

/*
 * mvp1
 * 27/8/19
 */


import com.filetypeIdentification.mvp1.document.Extension;
import com.filetypeIdentification.mvp1.document.ExtensionRequestDTO;
import com.filetypeIdentification.mvp1.document.ExtensionResponseDTO;
import com.filetypeIdentification.mvp1.repository.ExtensionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class InformationService {

	@Autowired
	MongoTemplate mongoTemplate;

	public ExtensionResponseDTO getInformation(String queryExtension){
		Query query = new Query();
		query.addCriteria(Criteria.where("extensionString").is(queryExtension.toLowerCase()));
		List<Extension> extensionList = mongoTemplate.find(query, Extension.class);
		ExtensionResponseDTO extensionResponseDTO = new ExtensionResponseDTO(extensionList);
		return extensionResponseDTO;
	}

	public ExtensionResponseDTO getMultipleInformation(ExtensionRequestDTO extensionRequestDTO){
		List<String> queryExtensionList = extensionRequestDTO.getExtensionQueryList();
		Query query = new Query();
		// todo convert it to lower case
		query.addCriteria(Criteria.where("extensionString").in(queryExtensionList));
		List<Extension> extensionList = mongoTemplate.find(query, Extension.class);
		ExtensionResponseDTO extensionResponseDTO = new ExtensionResponseDTO(extensionList);
		return extensionResponseDTO;
	}
	public ExtensionResponseDTO getByCateogry(String category)
	{
		Query query = new Query();
		query.addCriteria(Criteria.where("extensionData.category").is(category));
		List<Extension> extensionList = mongoTemplate.find(query, Extension.class);
		ExtensionResponseDTO extensionResponseDTO = new ExtensionResponseDTO(extensionList);
		return extensionResponseDTO;
	}

}