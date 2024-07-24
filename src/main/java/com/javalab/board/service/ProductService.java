package com.javalab.board.service;

import java.util.List;

import com.javalab.board.vo.ImgVo;
import com.javalab.board.vo.ProductVo;
import com.javalab.board.vo.ProductWithImageVo;

public interface ProductService {
    void addProduct(ProductVo product);
    ProductWithImageVo getProductWithImages(Long productId);
    void insertImages(List<ImgVo> images); 
    List<ProductWithImageVo> getAllProduct(); 
    ProductWithImageVo getProductWithAllImages(Long productId);
}
