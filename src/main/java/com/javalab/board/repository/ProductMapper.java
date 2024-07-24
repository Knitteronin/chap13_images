package com.javalab.board.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.javalab.board.vo.ImgVo;
import com.javalab.board.vo.ProductVo;
import com.javalab.board.vo.ProductWithImageVo;

@Mapper
public interface ProductMapper {

    void insertProduct(ProductVo product);

    //ProductVo getProduct(@Param("productId") Long productId);
    ProductWithImageVo getProductWithImages(@Param("productId") Long productId);

    void insertImages(List<ImgVo> images);

    List<ProductWithImageVo> getAllProduct(); // 추가된 메소드
    
    ProductWithImageVo getProductWithAllImages(Long productId);
}
