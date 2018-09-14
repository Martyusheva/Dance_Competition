package dancecomp.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class CountingBoardForm {
    private Stage primaryStage;
    private VBox rootLayout;
    private Application mainClass;

    public CountingBoardForm(Stage primaryStage, Application mainClass) {
        this.primaryStage = primaryStage;
        this.mainClass = mainClass;
    }

    public void render() {
        this.primaryStage.setTitle("Соревнования АСХ: счетная комиссия");
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.mainClass.getClass().getResource("resources/views/CountingBoardForm.fxml"));
            loader.setController(new CountingBoardFormController());
            rootLayout = (VBox) loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

            CountingBoardFormController controller = loader.getController();
            controller.setGeneralVariable(mainClass, primaryStage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
