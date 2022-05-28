package com.example.porject2.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Data
public class Comment {
    @NotEmpty
    @Size(max = 3)
    private String id;
    @NotEmpty
    @Size(max = 5)
    private String userID;
    @NotEmpty
    @Size(max = 6)
    private String msg;
    @NotNull
    @Pattern(regexp = ("1|2|3|4|5"))
    private Integer rate;
}
