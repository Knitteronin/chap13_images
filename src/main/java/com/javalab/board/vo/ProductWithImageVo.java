package com.javalab.board.vo;


import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@ToString
public class ProductWithImageVo {
    private Long productId;
    private String name;
    private String description;
    private Double unitPrice;
    private Date regDate;
    private String imgPath; // 대표이미 경로
    private String fileName;// 대표이미지명
    private List<ImgVo> imgList; // 이미지 목록
}
