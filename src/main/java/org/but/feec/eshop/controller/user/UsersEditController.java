package org.but.feec.eshop.controller.user;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.but.feec.eshop.api.user.UserBasicView;
import org.but.feec.eshop.api.user.UserEditView;
import org.but.feec.eshop.data.UserRepository;
import org.but.feec.eshop.service.UserService;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class UsersEditController {
    private static final Logger logger = LoggerFactory.getLogger(org.but.feec.eshop.controller.user.UsersEditController.class);

    @FXML
    public Button editUserButton;
    @FXML
    public TextField idTextField;
    @FXML
    private TextField accountNameTextField;
    @FXML
    private TextField accountPasswordTextField;
    @FXML
    private TextField userNameTextField;
    @FXML
    private TextField emailTextField;


    private UserService userService;
    private UserRepository userRepository;
    private ValidationSupport validation;

    // used to reference the stage and to get passed data through it
    public Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void initialize() {
        userRepository = new UserRepository();
        userService = new UserService(userRepository);

        validation = new ValidationSupport();
        validation.registerValidator(idTextField, Validator.createEmptyValidator("The id must not be empty."));
        idTextField.setEditable(false);
        validation.registerValidator(emailTextField, Validator.createEmptyValidator("The email must not be empty."));
        validation.registerValidator(accountNameTextField, Validator.createEmptyValidator("The first name must not be empty."));
        validation.registerValidator(accountPasswordTextField, Validator.createEmptyValidator("The last name must not be empty."));


        editUserButton.disableProperty().bind(validation.invalidProperty());

        loadUsersData();

        logger.info("UsersEditController initialized");
    }

    /**
     * Load passed data from Userns controller. Check this tutorial explaining how to pass the data between controllers: https://dev.to/devtony101/javafx-3-ways-of-passing-information-between-scenes-1bm8
     */
    private void loadUsersData() {
        Stage stage = this.stage;
        if (stage.getUserData() instanceof UserBasicView) {
            UserBasicView userBasicView = (UserBasicView) stage.getUserData();
            idTextField.setText(String.valueOf(userBasicView.getId()));
            accountNameTextField.setText(userBasicView.getAccountName());
            accountPasswordTextField.setText(userBasicView.getAccountPassword());

            emailTextField.setText(userBasicView.getEmail());

        }
    }

    @FXML
    public void handleEditUserButton(ActionEvent event) {
        // can be written easier, its just for better explanation here on so many lines
        Long id = Long.valueOf(idTextField.getText());
        String firstName = accountNameTextField.getText();
        String accountPassword = accountPasswordTextField.getText();
        String lastName = userNameTextField.getText();
        String email = emailTextField.getText();


        UserEditView userEditView = new UserEditView();
        userEditView.setId(id);
        userEditView.setUserName(firstName);
        userEditView.setAccountName(lastName);
        userEditView.setAccountPassword(accountPassword);
        userEditView.setEmail(email);


        userService.editUser(userEditView);

        userEditedConfirmationDialog();
    }

    private void userEditedConfirmationDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("User Edited Confirmation");
        alert.setHeaderText("Your user was successfully edited.");

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
