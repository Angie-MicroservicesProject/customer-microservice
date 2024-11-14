package com.example.ms.ms_customer.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDto {

    @NotEmpty(message = "Name can not be a null or empty")
    @Size(min = 1)
    private String name;

    @NotEmpty(message = "Lastname can not be a null or empty")
    @Size(min = 1)
    private String lastname;

    @Pattern(regexp = "(^$|[0-7]{8})", message = "Dni number must be 10 digits")
    private String dni;

    @NotEmpty(message = "Email address can not be a null or empty")
    @Email(message = "Email address should be a valid value")
    private String email;
}
