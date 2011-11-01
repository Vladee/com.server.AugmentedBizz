package com.server.parser.dummy;

import com.server.entity.ServiceTransferEntity;
import com.server.parser.AbstractServiceParser;

public class DummyServiceParser extends AbstractServiceParser {

	@Override
	public String parseServiceTransferEntity(ServiceTransferEntity stEntity) {
		return "DONE";
	}

	@Override
	public String getCreatedContentType() {
		return "text/plain";
	}

}
