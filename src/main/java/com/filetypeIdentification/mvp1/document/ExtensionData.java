package com.filetypeIdentification.mvp1.document;

/*
 * mvp1
 * 27/8/19
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExtensionData {
	private String category;
	private String shortDescription;
	private String developer;
	private String information;
	private String link;
	private String usefulLink;
	private List<String> programList;
}