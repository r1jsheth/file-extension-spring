package com.filetypeIdentification.mvp1.document;

/*
 * mvp1
 * 29/8/19
 */


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ExtensionResponsePageWiseDTO {
	private int isLastPage;
	private int pageNo;
	private int size;
	private List<ExtensionResponse> extensionResponseList;

	public ExtensionResponsePageWiseDTO(int isLastPage, int pageNo, List<Extension> extensionList){
		this.isLastPage = isLastPage;
		this.pageNo = pageNo;
		List<ExtensionResponse> extensionResponseList = new ArrayList<>();
		this.size = extensionList.size();
		for(Extension extension : extensionList){
			ExtensionResponse currentExtensionResponse = new ExtensionResponse(extension);
			extensionResponseList.add(currentExtensionResponse);
		}
		this.extensionResponseList = extensionResponseList;
	}

}
