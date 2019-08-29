package com.filetypeIdentification.mvp1.document;

/*
 * mvp1
 * 27/8/19
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "extension")
public class Extension {

	@Id
	private String id;
	private String extensionString;
	private ExtensionData extensionData;


}
