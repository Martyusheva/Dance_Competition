package dancecomp.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginForm {
    private Stage primaryStage;
    private AnchorPane rootLayout;
    private Application mainClass;

    public LoginForm(Stage primaryStage, Application mainClass) {
        this.primaryStage = primaryStage;
        this.mainClass = mainClass;
    }

    public void render() {
        this.primaryStage.setTitle("Форма входа");
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.mainClass.getClass().getResource("resources/views/LoginForm.fxml"));
            loader.setController(new LoginFormController());
            rootLayout = (AnchorPane) loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

            LoginFormController controller = loader.getController();
            controller.setGeneralVariable(mainClass, primaryStage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
