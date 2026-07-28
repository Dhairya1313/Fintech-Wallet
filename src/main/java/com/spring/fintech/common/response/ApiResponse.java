package com.spring.fintech.common.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiResponse<T> {

	private String message;
	private T data;
	private LocalDateTime timeStamp;
	private String path;
}
