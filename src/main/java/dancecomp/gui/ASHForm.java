package dancecomp.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class ASHForm {
    private Stage primaryStage;
    private VBox rootLayout;
    private Application mainClass;

    public ASHForm(Stage primaryStage, Application mainClass) {
        this.primaryStage = primaryStage;
        this.mainClass = mainClass;
    }

    public void render() {
        this.primaryStage.setTitle("Соревнования АСХ: АСХ");
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.mainClass.getClass().getResource("resources/views/ASHForm.fxml"));
            loader.setController(new ASHFormController());
            rootLayout = (VBox) loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

            ASHFormController controller = loader.getController();
            controller.setGeneralVariable(mainClass, primaryStage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
