package com.gwanho.shopping.domain.item;

import lombok.Data;
import lombok.Getter;

import java.util.Date;

@Data
public class Item {

    private Long id;
    private String ItemName;
    private Integer color; // 컬러 테이블을 불리 해야
    private Integer price;
    private Integer quantity;
    private Date firstInputDt;
    private Date firstAmndr;
    private Date LastUpdateDt;
    private Date LastAmndr;
    private String useYn;


    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        ItemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
