package com.gwanho.shopping.web.form;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ItemUpdateForm {

    @NotBlank
    private String itemName;

    private Integer color;

    @NotNull
    @Range(min=1000,max=1000000)
    private Integer price;

    @NotNull
    @Max(value = 10000)
    private Integer quantity;

    private String useYn;
}
