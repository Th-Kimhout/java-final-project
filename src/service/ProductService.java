package service;

import model.Product;
import model.dto.CreateProductReq;
import model.dto.UpdateProductReq;

import java.util.List;

public interface ProductService {
    List<Product> getProducts();
    boolean addProduct(CreateProductReq createProductReq);
    boolean updateProduct(int id, UpdateProductReq updateProductReq);
    boolean deleteProduct(int id);
    Product getProduct(int id);
    Product getProduct(String productName);
}
