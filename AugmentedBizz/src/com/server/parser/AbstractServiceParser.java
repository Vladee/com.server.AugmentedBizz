package com.server.parser;

import com.server.entity.ServiceTransferEntity;

public abstract class AbstractServiceParser {

	/**
	 * Parses a service transfer entity in order to send the created string to the client
	 * @return
	 */
	public abstract String parseServiceTransferEntity(ServiceTransferEntity stEntity) throws Exception;
	
	/**
	 * @return The content type of the created string
	 */
	public abstract String getCreatedContentType();
}
