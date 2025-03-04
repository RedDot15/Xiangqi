package com.example.xiangqi.dto.request;

import com.example.xiangqi.validation.annotation.Match;
import com.example.xiangqi.validation.group.Create;
import com.example.xiangqi.validation.group.Update;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Match(
		fields = {"password", "confirmPassword"},
		message = "These fields {fields} must match.")
public class PlayerRequest {
	@Null(groups = Create.class, message = "Id must not be provided during creation.")
	@NotNull(groups = Update.class, message = "Id is required during update.")
	Long id;

	@NotBlank(groups = Create.class, message = "Username is required.")
	@Size(min = 3, max = 20, message = "Username must be between {min} and {max} characters.")
	@Null(
			groups = Update.class,
			message = "Username must not be provided during update since username should not be updated.")
	String username;

	@NotBlank(
			groups = {Create.class, Update.class},
			message = "Password is required.")
	@Pattern(
			regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$",
			message = "Password must contains at least 8 characters and at most 20 characters."
					+ "Password must contains at least one digit."
					+ "Password must contains at least one upper case alphabet."
					+ "Password must contains at least one lower case alphabet."
					+ "Password must contains at least one special character which includes !@#$%&*()-+=^."
					+ "Password must not contain any white space.")
	String password;

	@NotBlank(
			groups = {Create.class, Update.class},
			message = "Confirm password is required.")
	String confirmPassword;
}
