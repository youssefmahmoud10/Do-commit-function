package com.example.demo.models;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

/**
 * @author YoussefMahmoud
 * @created Apr 22, 2023-5:05:43 PM
 */

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DoCommitRequest {

	Integer pageId;
	Integer userNumber;
	Integer operationNumber;
	String functionMode;
	String formFieldsData;
	String schemaName;

}