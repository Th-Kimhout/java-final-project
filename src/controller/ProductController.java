package controller;

import model.dto.CreateProductReq;
import model.Product;
import model.dto.UpdateProductReq;
import service.ProductServiceImpl;

import java.util.List;

public class ProductController {
    ProductServiceImpl service = new ProductServiceImpl();

    public List<Product> getProducts() {
        return service.getProducts();
    }

    public boolean addProduct(CreateProductReq createProductReq) {
        return service.addProduct(createProductReq);
    }

    public boolean updateProduct(int id, UpdateProductReq updateProductReq) {
        return service.updateProduct(id, updateProductReq);
    }
    public boolean deleteProduct(int id) {
        return service.deleteProduct(id);
    }

    public Product getProduct(int id) {
        return service.getProduct(id);
    }
}
