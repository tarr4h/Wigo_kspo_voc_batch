package com.kspo.voc.batch.dispatch.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DispatchResult {
	int responseCode;
	String errorCode;
	String result;
}
