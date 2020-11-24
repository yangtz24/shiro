package com.sihai.service.impl;

import com.sihai.mapper.ProductMapper;
import com.sihai.pojo.Product;
import com.sihai.pojo.ProductExample;
import com.sihai.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> getProducts() {

        ProductExample pe = new ProductExample();
//		Criteria pc = pe.createCriteria();
        List<Product> list = productMapper.selectByExample(pe);

        return list;
    }

    @Override
    public Product getProductById(String productId) {

        return productMapper.selectByPrimaryKey(productId);
    }

}
