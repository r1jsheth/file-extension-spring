package com.filetypeIdentification.mvp1.document;

/*
 * mvp1
 * 28/8/19
 */

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ExtensionResponseDTO {
	private int size;
	private List<ExtensionResponse> extensionResponseList;


	public ExtensionResponseDTO(List<Extension> extensionList){
		List<ExtensionResponse> extensionResponseList = new ArrayList<>();
		this.size = extensionList.size();
		for(Extension extension : extensionList){
			ExtensionResponse currentExtensionResponse = new ExtensionResponse(extension);
			extensionResponseList.add(currentExtensionResponse);
		}
		this.extensionResponseList = extensionResponseList;
	}
}
