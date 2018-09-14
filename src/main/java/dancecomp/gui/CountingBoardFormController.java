package dancecomp.gui;

import dancecomp.logics.Competition;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import dancecomp.facade.CountingBoardFacade;
import dancecomp.facade.UserFacade;
import dancecomp.logics.User;

import javax.swing.*;

public class CountingBoardFormController {
    @FXML
    private TextField email;
    @FXML
    private TextField fullName;
    @FXML
    private TextField phone;
    @FXML
    private TableView<Competition> listComps;
    @FXML
    private TableColumn<Competition, String> name;
    @FXML
    private TableColumn<Competition, String> date;
    @FXML
    private TableColumn<Competition, String> status;
    @FXML
    private TableColumn<Competition, String> comments;
    @FXML
    private TableColumn<Competition, String> details;

    @FXML
    private Button saveProfileBtn;

    private Application mainClass;

    private Stage primaryStage;

    /**
     * Конструктор.
     * Конструктор вызывается раньше метода initialize().
     */
    public CountingBoardFormController() {
    }

    /**
     * Инициализация класса-контроллера. Этот метод вызывается автоматически
     * после того, как fxml-файл будет загружен.
     */
    @FXML
    private void initialize() {
        this.loadProfile();
        this.showListComps();

        listComps.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selectComp(newValue)
        );

    }

    public void setGeneralVariable(Application mainClass, Stage mainStage) {
        this.mainClass = mainClass;
        this.primaryStage = mainStage;
    }

    // Обработчики событий
    public void saveProfile() {
        User currentUser = UserFacade.getLoggedUser();
        currentUser.setEmail(email.getText());
        currentUser.setPhone(phone.getText());
        currentUser.setName(fullName.getText());
        currentUser.save();
        JOptionPane.showMessageDialog(null, "Профиль сохранён");
    }

    public void loadProfile() {
        User currentUser = UserFacade.getLoggedUser();
        email.setText(currentUser.getEmail());
        fullName.setText(currentUser.getName());
        phone.setText(currentUser.getPhone());

    }

    public void showListComps() {
        ObservableList<Competition> obCompetitions = FXCollections.observableArrayList();
        obCompetitions.addAll(CountingBoardFacade.getMyComp());
        listComps.setItems(obCompetitions);
        name.setCellValueFactory(new PropertyValueFactory<Competition, String>("name"));
        date.setCellValueFactory(new PropertyValueFactory<Competition, String>("date"));
        status.setCellValueFactory(new PropertyValueFactory<Competition, String>("statusName"));
        comments.setCellValueFactory(new PropertyValueFactory<Competition, String>("comments"));
        details.setCellValueFactory(new PropertyValueFactory<Competition, String>("details"));
    }

    public void selectComp(Competition competition) {
    }

    public void terminate() {
        primaryStage.close();
    }

}