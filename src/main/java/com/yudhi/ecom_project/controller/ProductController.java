package com.yudhi.ecom_project.controller;

import com.yudhi.ecom_project.model.Product;
import com.yudhi.ecom_project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductService service;
    @RequestMapping("/")
    public String greet(){
        return "Hello World";
    }
    @GetMapping("/products")
    public ResponseEntity<List<Product>>getAllProducts(){
        return new ResponseEntity<>(service.getAllProducts(), HttpStatus.OK);

    }
    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable long id){
        Product product = service.getProductById(id);
        if(product != null)
            return new ResponseEntity<>(product, HttpStatus.OK);
        else
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
    @PostMapping("/product")
    public ResponseEntity<?>addProduct(@RequestPart Product product,
                                       @RequestPart MultipartFile imageFile){
        try{
        Product product1=service.addProduct(product,imageFile);
        return new ResponseEntity<>(product1, HttpStatus.CREATED);}
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        }
    @GetMapping("product/{productid}/image")
    public ResponseEntity<byte[]> getImageById(@PathVariable long productid){
        Product product = service.getProductById(productid);
        byte[]imageFile=product.getImageDate();
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(product.getImageType()))
                .body(imageFile);



    }
}
