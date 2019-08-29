package com.filetypeIdentification.mvp1.service;

/*
 * mvp1
 * 27/8/19
 */


import com.filetypeIdentification.mvp1.document.*;
import com.mongodb.client.DistinctIterable;
import com.mongodb.client.MongoCursor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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


	@Cacheable("extension")
	public ExtensionResponseDTO getInformation(String queryExtension){
		Query query = new Query();
		query.addCriteria(Criteria.where("extensionString").is(queryExtension.toLowerCase()));
		List<Extension> extensionList = mongoTemplate.find(query, Extension.class);
		ExtensionResponseDTO extensionResponseDTO = new ExtensionResponseDTO(extensionList);
		return extensionResponseDTO;
	}

	@Cacheable("extension")
	public ExtensionResponseDTO getMultipleInformation(ExtensionRequestDTO extensionRequestDTO){
		List<String> queryExtensionList = extensionRequestDTO.getExtensionQueryList();
		Query query = new Query();
		query.addCriteria(Criteria.where("extensionString").in(queryExtensionList));
		List<Extension> extensionList = mongoTemplate.find(query, Extension.class);
		ExtensionResponseDTO extensionResponseDTO = new ExtensionResponseDTO(extensionList);
		return extensionResponseDTO;
	}

	@Cacheable("extension")
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

	public ExtensionResponsePageWiseDTO getPageWiseResponse(ExtensionRequestDTO extensionRequestDTO,
																int pageNo, int pageSize) throws Exception{
		Query query = new Query();
		List<String> extensionQueryList = extensionRequestDTO.getExtensionQueryList();
		Collections.sort(extensionQueryList);
		query.addCriteria(Criteria.where("extensionString").in(extensionQueryList));
		List<Extension> extensionList = mongoTemplate.find(query, Extension.class);
		int totalResult = extensionList.size();
		int totalPages = (totalResult % pageSize == 0 ? totalResult/pageSize : totalResult/pageSize+1);

		int isLastPage = 0;
		int startIndex = (pageNo-1)*pageSize, endIndex = Math.min(startIndex + pageSize, totalResult);
		if(startIndex >= totalResult){
			throw new Exception("Bad Request");
		}
		if(pageNo == -1 || endIndex >= totalResult ){
			isLastPage = 1;
			startIndex = (totalPages-1)*pageSize;
			endIndex = totalResult;
		}
		ExtensionResponsePageWiseDTO extensionResponsePageWiseDTO = new ExtensionResponsePageWiseDTO(
			isLastPage, pageNo, extensionList.subList(startIndex, endIndex)
		);

		return extensionResponsePageWiseDTO;


	}

	public List<String> getAllCategory(){

		DistinctIterable distinctIterable = mongoTemplate.getCollection("extensionProd").distinct("extensionData.category", String.class);
		MongoCursor cursor = distinctIterable.iterator();
		List<String> categoryList = new ArrayList<>();

		while(cursor.hasNext()){
			categoryList.add((String) cursor.next());
		}

		Collections.sort(categoryList);

		return categoryList;
	}


	public ExtensionResponseDTO getByCategory(String category){
		Query query = new Query();
		query.addCriteria(Criteria.where("extensionData.category").is(category));
		List<Extension> extensionList = mongoTemplate.find(query, Extension.class);
		ExtensionResponseDTO extensionResponseDTO = new ExtensionResponseDTO(extensionList);
		return extensionResponseDTO;
	}

}