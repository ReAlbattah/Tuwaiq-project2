package com.example.porject2.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.*;

@AllArgsConstructor
@Data
public class User {
    @NotEmpty
    @Size(max = 3)
    private String id;
    @NotEmpty
    @Size(max = 5)
    private String userName;
    @NotEmpty
    @Size(max = 6)
   // @Pattern(regexp = ([0-9])*([A-Za-z]*))
    private String password;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    @Pattern(regexp = ("admin|customer"))
    private String role;
    @NotNull
    @Positive
    private Integer balance;

}
