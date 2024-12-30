package repository;

import model.Product;
import model.dto.CreateProductReq;
import model.dto.UpdateProductReq;

import java.util.List;

public interface ProductRepo {
    List<Product> getAllProducts();
    boolean addProduct(CreateProductReq createProduct);
    boolean updateProduct(int id, UpdateProductReq updateProduct);
    boolean deleteProduct(int id);
}
