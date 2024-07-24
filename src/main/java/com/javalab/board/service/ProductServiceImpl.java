package com.javalab.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javalab.board.repository.ProductMapper;
import com.javalab.board.vo.ImgVo;
import com.javalab.board.vo.ProductVo;
import com.javalab.board.vo.ProductWithImageVo;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public void addProduct(ProductVo product) {
        productMapper.insertProduct(product);
    }

    @Override
    public ProductWithImageVo getProductWithImages(Long productId) {
        return productMapper.getProductWithImages(productId);
    }

    @Override
    public void insertImages(List<ImgVo> images) {
        productMapper.insertImages(images);
    }

    @Override
    public List<ProductWithImageVo> getAllProduct() {
        return productMapper.getAllProduct(); // 추가된 메소드
    }
    
    @Override
    public ProductWithImageVo getProductWithAllImages(Long productId) {
        return productMapper.getProductWithAllImages(productId);
    }    
}
