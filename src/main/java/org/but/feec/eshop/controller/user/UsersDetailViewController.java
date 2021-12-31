package org.but.feec.eshop.controller.user;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.but.feec.eshop.api.user.UserDetailView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsersDetailViewController {
    private static final Logger logger = LoggerFactory.getLogger(org.but.feec.eshop.controller.user.UsersDetailViewController.class);

    @FXML
    private TextField idTextField;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField accountnameTextField;

    @FXML
    private TextField accountpasswordTextField;

    @FXML
    private TextField emailTextField;


    @FXML
    private TextField createdTextField;

    // used to reference the stage and to get passed data through it
    public Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void initialize() {
        idTextField.setEditable(false);
        firstNameTextField.setEditable(false);

        accountpasswordTextField.setEditable(false);
        emailTextField.setEditable(false);
        accountnameTextField.setEditable(false);


        createdTextField.setEditable(false);

        loadUsersData();

        logger.info("UsersDetailViewController initialized");
    }

    private void loadUsersData() {
        Stage stage = this.stage;
        if (stage.getUserData() instanceof UserDetailView) {
            UserDetailView userBasicView = (UserDetailView) stage.getUserData();
            idTextField.setText(String.valueOf(userBasicView.getId()));
            firstNameTextField.setText(userBasicView.getUserName());
            accountnameTextField.setText(userBasicView.getAccountName());
            accountpasswordTextField.setText(userBasicView.getAccountPassword());
            emailTextField.setText(userBasicView.getEmail());
            createdTextField.setText(userBasicView.getCreated());
        }
    }

}
