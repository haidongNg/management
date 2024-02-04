package com.nhd.management.dto.responses;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ZzGlobalErrorResponse {
 private int status;
 private String message;
 private long timestamp;
}
