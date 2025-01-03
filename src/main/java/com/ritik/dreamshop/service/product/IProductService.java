package com.ritik.dreamshop.service.product;

import com.ritik.dreamshop.model.Product;

import java.util.List;

public interface IProductService {

    Product addProduct(Product product);
    List<Product> getAllProducts();
    Product getProductById(Long id);
    void deleteProductById(Long id);
    void updateProduct(Product product, Long id);



}
