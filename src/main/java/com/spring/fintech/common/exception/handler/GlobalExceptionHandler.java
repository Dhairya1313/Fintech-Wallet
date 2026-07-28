package com.spring.fintech.common.exception.handler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.spring.fintech.common.exception.DuplicateEmailException;
import com.spring.fintech.common.exception.DuplicateUsernameException;
import com.spring.fintech.common.exception.UnauthorizedOperationException;
import com.spring.fintech.common.exception.UserNotFoundException;
import com.spring.fintech.common.exception.WalletNotFoundException;
import com.spring.fintech.common.response.ApiResponse;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	private ApiResponse<Void> buildErrorResponse(String message, String path) {

	    ApiResponse<Void> response = new ApiResponse<>();
	    response.setMessage(message);
	    response.setData(null);
	    response.setTimeStamp(LocalDateTime.now());
	    response.setPath(path);

	    return response;
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ApiResponse<Void>> handleUserNotFound(
			UserNotFoundException ex, HttpServletRequest request){
	    return ResponseEntity.status(HttpStatus.NOT_FOUND)
	            .body(buildErrorResponse(
	                    ex.getMessage(),
	                    request.getRequestURI()));
	}
	
	@ExceptionHandler(WalletNotFoundException.class)
	public ResponseEntity<ApiResponse<Void>> handleWalletNotFound(
			WalletNotFoundException ex, HttpServletRequest request){
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(buildErrorResponse(ex.getMessage(), request.getRequestURI()));
	}
	
	@ExceptionHandler(UnauthorizedOperationException.class)
	public ResponseEntity<ApiResponse<Void>> handleUnauthorizedOperationException(
			UnauthorizedOperationException ex, HttpServletRequest request){
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
				.body(buildErrorResponse(ex.getMessage(), request.getRequestURI()));
	}
	
	@ExceptionHandler({DuplicateEmailException.class, DuplicateUsernameException.class})
	public ResponseEntity<ApiResponse<Void>> handleDuplicateData(
			RuntimeException ex, HttpServletRequest request) {
		
		return ResponseEntity.status(HttpStatus.CONFLICT)
				.body(buildErrorResponse(ex.getMessage(), request.getRequestURI()));
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<Void>> handleInternalServerError(Exception ex, HttpServletRequest request) {

	    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	            .body(buildErrorResponse("An unexpected error occurred.", request.getRequestURI()));
	}
}
