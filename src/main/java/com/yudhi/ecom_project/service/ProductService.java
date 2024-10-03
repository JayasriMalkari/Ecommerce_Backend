package com.yudhi.ecom_project.service;

import com.yudhi.ecom_project.model.Product;
import com.yudhi.ecom_project.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;
    public List<Product> getAllProducts() {
         return repository.findAll();
    }

    public Product getProductById(long id) {
        //return repository.findById(id).orElse(new Product());
        return repository.findById(id).orElse(null);
    }

    public Product addProduct(Product product, MultipartFile imageFile) throws IOException {
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageDate(imageFile.getBytes());
       return repository.save(product);
    }
//update
    public Product updateProduct(long id, Product product, MultipartFile imageFile) throws IOException {
        product.setImageDate(imageFile.getBytes());
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        return repository.save(product);

    }
//delete
    public void deleteProduct(long id) {
        repository.deleteById(id);
    }

    public List<Product> searchproducts(String keyword) {
        return repository.searchProducts(keyword);
    }
}
