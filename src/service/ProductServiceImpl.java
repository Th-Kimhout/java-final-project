package service;

import model.Product;
import model.dto.CreateProductReq;
import model.dto.UpdateProductReq;
import repository.ProductRepoImpl;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    ProductRepoImpl productRepoImpl = new ProductRepoImpl();

    @Override
    public List<Product> getProducts() {
        return productRepoImpl.getAllProducts();
    }

    @Override
    public boolean addProduct(CreateProductReq createProductReq) {
        return productRepoImpl.addProduct(createProductReq);
    }

    @Override
    public boolean updateProduct(int id, UpdateProductReq updateProductReq) {

        if (!(updateProductReq.product_name().isEmpty()
                && updateProductReq.product_price() == -1
                && updateProductReq.product_quantity() == -1
                && updateProductReq.product_category() == -1)) {

            if (productRepoImpl.getAllProducts().stream().anyMatch(product -> product.getId() == id)) {
                return productRepoImpl.updateProduct(id, updateProductReq);
            }
        }
        return false;
    }

    @Override
    public boolean deleteProduct(int id) {
        if (productRepoImpl.getAllProducts().stream().anyMatch(product -> product.getId() == id)) {
            return productRepoImpl.deleteProduct(id);
        }
        return false;
    }

    @Override
    public Product getProduct(int id) {
        return productRepoImpl.
                getAllProducts().
                stream().
                filter(product -> product.getId() == id)
                .findFirst().orElse(null);
    }

    @Override
    public Product getProduct(String productName) {
        return productRepoImpl.
                getAllProducts().
                stream().
                filter(product -> product.getProduct_name().equals(productName))
                .findFirst().orElse(null);
    }

}
