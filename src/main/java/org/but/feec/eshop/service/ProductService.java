package org.but.feec.eshop.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.but.feec.eshop.data.ProductRepository;
import org.but.feec.eshop.api.product.ProductBasicView;
import org.but.feec.eshop.api.product.ProductCreateView;
import org.but.feec.eshop.api.product.ProductDetailView;
import org.but.feec.eshop.api.product.ProductEditView;
import org.but.feec.eshop.api.product.ProductDeleteView;

import java.util.List;


public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductDetailView getProductDetailView(Long id) {
        return productRepository.findProductDetailedView(id);
    }

    public List<ProductBasicView> getProductBasicView() {
        return productRepository.getProductBasicView();
    }

    public void createProduct(ProductCreateView productCreateView) {
        productRepository.createProduct(productCreateView);
    }

    public void editProduct(ProductEditView productEditView) {
        productRepository.editProduct(productEditView);
    }

    public void deleteProduct(ProductDeleteView productDeleteView) {
        productRepository.deleteProduct(productDeleteView);
    }

}
