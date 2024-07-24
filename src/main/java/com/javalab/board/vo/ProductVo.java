package com.javalab.board.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@ToString
public class ProductVo {
    private Long productId;
    private String name;
    private String description;
    private Double unitPrice;
    private Date regDate;

}
