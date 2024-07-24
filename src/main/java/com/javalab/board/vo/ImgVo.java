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
public class ImgVo {
    private Long imgId;
    private Long productId;
    private String imgPath;
    private String fileName;
    private int isMain; // 대표 이미지 여부
}
