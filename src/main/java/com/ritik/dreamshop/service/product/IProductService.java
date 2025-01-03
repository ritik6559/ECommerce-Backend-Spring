package com.ritik.dreamshop.service.product;

import com.ritik.dreamshop.model.Product;
import com.ritik.dreamshop.request.AddProductRequest;
import com.ritik.dreamshop.request.UpdateProductRequest;

import java.util.List;

public interface IProductService {

    Product addProduct(AddProductRequest product);
    Product getProductById(Long id);
    Product updateProduct(UpdateProductRequest product, Long id);
    void deleteProductById(Long id);
    List<Product> getAllProducts();
    List<Product> getProductsByCategory(String category);
    List<Product> getProductByBrand(String brand);
    List<Product> getProductByCategoryAndBrand(String category, String brand);
    List<Product> getProductByName(String name);
    List<Product> getProductByBrandAndName(String brand, String name);
    Long countProductsByBrandAndName(String brand, String name);


}
