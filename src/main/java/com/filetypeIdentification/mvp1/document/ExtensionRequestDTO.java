package com.filetypeIdentification.mvp1.document;

/*
 * mvp1
 * 28/8/19
 */

import lombok.Data;

import java.util.List;

@Data
public class ExtensionRequestDTO {
	private int size;
	private List<String> extensionQueryList;
	
}
