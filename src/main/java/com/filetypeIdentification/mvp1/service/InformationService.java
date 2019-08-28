package com.filetypeIdentification.mvp1.service;

/*
 * mvp1
 * 27/8/19
 */


import com.filetypeIdentification.mvp1.document.Extension;
import com.filetypeIdentification.mvp1.document.ExtensionRequestDTO;
import com.filetypeIdentification.mvp1.document.ExtensionResponse;
import com.filetypeIdentification.mvp1.document.ExtensionResponseDTO;
import com.filetypeIdentification.mvp1.repository.ExtensionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
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

	public ExtensionResponseDTO getMultipleInformationPageWise(ExtensionRequestDTO extensionRequestDTO,
																int pageNo, int pageSize){
		List<String> extensionQueryList = extensionRequestDTO.getExtensionQueryList();
		Collections.sort(extensionQueryList);

		try{
			if(pageNo <= 0 || pageSize <= 0){
				throw new Exception("Page no can't be less than 1");
			}

			Query query = new Query();
			query.addCriteria(Criteria.where("extensionString").in(extensionQueryList));

			if(pageNo == 1){
				query.limit(pageSize);
			}
			else{
				query.skip((long)(pageNo-1)*pageSize).limit(pageSize);
			}

			List<Extension> extensionList = mongoTemplate.find(query, Extension.class);
			if(extensionList.size() == 0){
				throw new Exception("Please input reasonable page no and size!");
			}
			ExtensionResponseDTO extensionResponseDTO = new ExtensionResponseDTO(extensionList);

			return extensionResponseDTO;
		}
		catch(Exception e){
			e.printStackTrace();
			return new ExtensionResponseDTO(new ArrayList<>());
		}
	}



	public ExtensionResponseDTO getByCategory(String category)
	{
		Query query = new Query();
		query.addCriteria(Criteria.where("extensionData.category").is(category));
		List<Extension> extensionList = mongoTemplate.find(query, Extension.class);
		ExtensionResponseDTO extensionResponseDTO = new ExtensionResponseDTO(extensionList);
		return extensionResponseDTO;
	}

}