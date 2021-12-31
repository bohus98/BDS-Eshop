package org.but.feec.eshop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.but.feec.eshop.exception.ExceptionHandler;

public class App extends Application {

    private FXMLLoader loader;
    private VBox mainStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            loader = new FXMLLoader(getClass().getResource("/org.but.feec.eshop/App.fxml"));
            mainStage = loader.load();

            primaryStage.setTitle("BDS Eshop");
            Scene scene = new Scene(mainStage);
            setUserAgentStylesheet(STYLESHEET_MODENA);
            String myStyle = getClass().getResource("css/myStyle.css").toExternalForm();
            scene.getStylesheets().add(myStyle);

            primaryStage.getIcons().add(new Image(App.class.getResourceAsStream("logo/lockC.png")));
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception ex) {
            ExceptionHandler.handleException(ex);
        }
    }
}
