package com.example.porject2.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Data
public class MerchantStock {
    @NotEmpty
    @Size(max = 3)
    private String id;
    @NotEmpty
    @Size(max = 3)
    private String productID;
    @NotEmpty
    @Size(max = 3)
    private String merchantID;
    @NotNull
    @Size(min = 10)
    private Integer stock;
}
