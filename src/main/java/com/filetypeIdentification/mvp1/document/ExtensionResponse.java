package com.filetypeIdentification.mvp1.document;

/*
 * mvp1
 * 28/8/19
 */

import lombok.Data;

import java.util.List;

@Data
public class ExtensionResponse {
	private String extensionString;
	private String category;
	private String shortDescription;
	private String developer;
	private String information;
	private String usefulLink;
	private List<String> programList;

	public ExtensionResponse(Extension extension){
		this.extensionString = extension.getExtensionString();
		this.category = extension.getExtensionData().getCategory();
		this.shortDescription = extension.getExtensionData().getShortDescription();
		this.developer = extension.getExtensionData().getDeveloper();
		this.information = extension.getExtensionData().getInformation();
		this.usefulLink = extension.getExtensionData().getUsefulLink();
		this.programList = extension.getExtensionData().getProgramList();
	}

}
