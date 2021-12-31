package org.but.feec.eshop.controller.product;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import org.but.feec.eshop.api.product.ProductCreateView;
import org.but.feec.eshop.data.ProductRepository;
import org.but.feec.eshop.service.ProductService;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.util.Optional;


public class ProductCreateController {
    private static final Logger logger = LoggerFactory.getLogger(ProductCreateController.class);

    @FXML
    public Button newProductCreateProduct;

    @FXML
    private TextField newProductId;

    @FXML
    private TextField newProductName;

    @FXML
    private TextField newProductDetail;

    @FXML
    private TextField newProductHas_Price;

    @FXML
    private TextField newProductPrice;

    @FXML
    private TextField newProductShopId;

    @FXML
    private TextField newProductCategoryId;

    @FXML
    private TextField newProductCurrencyId;

    private ProductService productService;
    private ProductRepository productRepository;
    private ValidationSupport validation;

    @FXML
    public void initialize() {
        productRepository = new ProductRepository();
        productService = new ProductService(productRepository);

        validation = new ValidationSupport();
        validation.registerValidator(newProductId, Validator.createEmptyValidator("The id must not be empty."));
        validation.registerValidator(newProductName, Validator.createEmptyValidator("The name must not be empty."));
        validation.registerValidator(newProductDetail, Validator.createEmptyValidator("The detail must not be empty."));
        validation.registerValidator(newProductHas_Price, Validator.createEmptyValidator("The has price must not be empty."));
        validation.registerValidator(newProductPrice, Validator.createEmptyValidator("The price must not be empty."));
        validation.registerValidator(newProductShopId, Validator.createEmptyValidator("The shop id must not be empty."));
        validation.registerValidator(newProductCategoryId, Validator.createEmptyValidator("The category id must not be empty."));
        validation.registerValidator(newProductCurrencyId, Validator.createEmptyValidator("The currency id must not be empty."));

        newProductCreateProduct.disableProperty().bind(validation.invalidProperty());

        logger.info("ProductCreateController initialized");
    }

    @FXML
    void handleCreateNewProduct(ActionEvent event) {
        // can be written easier, its just for better explanation here on so many lines
        String name = newProductId.getText();
        Long type = Long.valueOf(newProductName.getText());
        Long country = Long.valueOf(newProductCategoryId.getText());
        Long shop_id = Long.valueOf(newProductHas_Price.getText());
        Long lenght = Long.valueOf(newProductPrice.getText());
        String description = newProductShopId.getText();

        ProductCreateView productCreateView = new ProductCreateView();
        productCreateView.setProduct(name);
        productCreateView.setPrice(type);
        productCreateView.setCategoryid(country);
        productCreateView.setShopid(shop_id);
        productCreateView.setCurrencyid(lenght);
        productCreateView.setDetail(description);

        productService.createProduct(productCreateView);

        productCreatedConfirmationDialog();
    }

    private void productCreatedConfirmationDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Product Created Confirmation");
        alert.setHeaderText("Your product was successfully created.");

        Timeline idlestage = new Timeline(new KeyFrame(Duration.seconds(3), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                alert.setResult(ButtonType.CANCEL);
                alert.hide();
            }
        }));
        idlestage.setCycleCount(1);
        idlestage.play();
        Optional<ButtonType> result = alert.showAndWait();
    }

}

