package com.example.porject2.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Data
public class Category {
    @NotEmpty
    @Size(max = 3)
    private String id;
    @NotEmpty
    @Size(max = 3)
    private String name;

}
