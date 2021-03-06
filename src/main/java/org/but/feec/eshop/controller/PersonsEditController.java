package org.but.feec.eshop.controller;

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
import org.but.feec.eshop.api.PersonBasicView;
import org.but.feec.eshop.api.PersonEditView;
import org.but.feec.eshop.data.PersonRepository;
import org.but.feec.eshop.service.PersonService;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.util.Optional;

public class PersonsEditController {
    private static final Logger logger = LoggerFactory.getLogger(PersonsEditController.class);

    @FXML
    public Button editPersonButton;
    @FXML
    public TextField idTextField;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField birthdayTextField;
    @FXML
    private TextField addressTextField;

    private PersonService personService;
    private PersonRepository personRepository;
    private ValidationSupport validation;

    // used to reference the stage and to get passed data through it
    public Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void initialize() {
        personRepository = new PersonRepository();
        personService = new PersonService(personRepository);

        validation = new ValidationSupport();
        validation.registerValidator(idTextField, Validator.createEmptyValidator("The id must not be empty."));
        idTextField.setEditable(false);
        validation.registerValidator(firstNameTextField, Validator.createEmptyValidator("The first name must not be empty."));
        validation.registerValidator(lastNameTextField, Validator.createEmptyValidator("The last name must not be empty."));
        validation.registerValidator(birthdayTextField, Validator.createEmptyValidator("The birthday must not be empty."));
        validation.registerValidator(addressTextField, Validator.createEmptyValidator("The address must not be empty."));

        editPersonButton.disableProperty().bind(validation.invalidProperty());

        loadPersonsData();

        logger.info("PersonsEditController initialized");
    }

    /**
     * Load passed data from Persons controller. Check this tutorial explaining how to pass the data between controllers: https://dev.to/devtony101/javafx-3-ways-of-passing-information-between-scenes-1bm8
     */
    private void loadPersonsData() {
        Stage stage = this.stage;
        if (stage.getUserData() instanceof PersonBasicView) {
            PersonBasicView personBasicView = (PersonBasicView) stage.getUserData();
            idTextField.setText(String.valueOf(personBasicView.getId()));
            firstNameTextField.setText(personBasicView.getGivenName());
            lastNameTextField.setText(personBasicView.getFamilyName());
            birthdayTextField.setText(personBasicView.getBirthday());
            addressTextField.setText(String.valueOf(personBasicView.getAddress()));
        }
    }

    @FXML
    public void handleEditPersonButton(ActionEvent event) {
        // can be written easier, its just for better explanation here on so many lines
        Long id = Long.valueOf(idTextField.getText());
        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        java.sql.Date birthday = Date.valueOf(birthdayTextField.getText());
        Long address = Long.valueOf(addressTextField.getText());

        System.out.println(id);
        System.out.println(firstName);
        System.out.println(birthday);

        PersonEditView personEditView = new PersonEditView();
        personEditView.setId(id);
        personEditView.setFirstName(firstName);
        personEditView.setSurname(lastName);
        personEditView.setBirthday(birthday);
        personEditView.setAddress(address);

        personService.editPerson(personEditView);

        personEditedConfirmationDialog();
    }

    private void personEditedConfirmationDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Person Edited Confirmation");
        alert.setHeaderText("Your person was successfully edited.");

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
