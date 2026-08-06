package com.spring.fintech.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {
	
	@NotBlank(message = "Username is required.")
	@Size(min = 4, max = 20, message = "Username must be between 4 to 20 characters.")
	private String userName;
	
	@NotBlank(message="Password is required.")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,}$", 
	message = "Password must contain at least one uppercase letter, one lowercase letter, one digit, one special character, and be at least 8 characters long."
)
	//1 Uppercase, 1 Lowecase, 1 Character, 1 Digit, minimum 8 
	private String password;
	
	@NotBlank(message = "Email is required.")
	@Email(message = "Invalid format.")
	private String email;
	
}
