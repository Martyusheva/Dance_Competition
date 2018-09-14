package dancecomp.gui;

import dancecomp.facade.*;
import dancecomp.logics.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.List;

public class OrganizerFormController {
    @FXML
    private TextField email;
    @FXML
    private TextField fullName;
    @FXML
    private TextField phone;
    @FXML
    private ComboBox cityNewComp;
    @FXML
    private ComboBox clubNewComp;
    @FXML
    private TextField nameNewComp;
    @FXML
    private TextField dateNewComp;
    @FXML
    private ComboBox judgeMainNewComp;
    @FXML
    private ComboBox judge2NewComp;
    @FXML
    private ComboBox judge3NewComp;
    @FXML
    private ComboBox judge4NewComp;
    @FXML
    private ComboBox judge5NewComp;
    @FXML
    private ComboBox countingBoardNewComp;
    @FXML
    private RadioButton nomDebut;
    @FXML
    private RadioButton nomE;
    @FXML
    private RadioButton nomD;
    @FXML
    private RadioButton nomC;
    @FXML
    private RadioButton nomB;
    @FXML
    private RadioButton nomA;
    @FXML
    private RadioButton nomAbc;
    @FXML
    private TableView<Competition> listComps;
    @FXML
    private TableColumn<Competition, String> name;
    @FXML
    private TableColumn<Competition, String> date;
    @FXML
    private TableColumn<Competition, String> status;
    @FXML
    private TableColumn<Competition, String> details;
    @FXML
    private TableColumn<Competition, String> comments;

    @FXML
    private Button saveProfileBtn;
    @FXML
    private Button newCompBtn;



    private Application mainClass;

    private Stage primaryStage;

    /**
     * Конструктор.
     * Конструктор вызывается раньше метода initialize().
     */
    public OrganizerFormController() {
    }

    /**
     * Инициализация класса-контроллера. Этот метод вызывается автоматически
     * после того, как fxml-файл будет загружен.
     */
    @FXML
    private void initialize() {
        this.loadProfile();
        this.fillCitiesLists();
        this.fillClubsLists();
        this.fillJudgesLists();
        this.fillCBsLists();

        listComps.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selectOrder(newValue)
        );
    }

    public void setGeneralVariable(Application mainClass, Stage mainStage) {
        this.mainClass = mainClass;
        this.primaryStage = mainStage;
    }

    public void fillCitiesLists() {
        ObservableList<City> obCities = FXCollections.observableArrayList();
        List<City> cities = CityFacade.getCities();
        obCities.addAll(cities);
        this.cityNewComp.setItems(obCities);
    }

    public void fillClubsLists() {
        ObservableList<Club> obClubs = FXCollections.observableArrayList();
        List<Club> clubs = ClubFacade.getClubs();
        obClubs.addAll(clubs);
        this.clubNewComp.setItems(obClubs);
    }

    public void fillJudgesLists() {
        ObservableList<Judge> obJudges = FXCollections.observableArrayList();
        List<Judge> judges = JudgeFacade.getJudges();
        obJudges.addAll(judges);
        this.judgeMainNewComp.setItems(obJudges);
        this.judge2NewComp.setItems(obJudges);
        this.judge3NewComp.setItems(obJudges);
        this.judge4NewComp.setItems(obJudges);
        this.judge5NewComp.setItems(obJudges);
    }

    public void fillCBsLists() {
        ObservableList<User> obCBs = FXCollections.observableArrayList();
        List<User> cbs = CountingBoardFacade.getCountingBoards();
        obCBs.addAll(cbs);
        this.countingBoardNewComp.setItems(obCBs);
    }

    // Обработчики событий
    public void saveProfile() {
        User currentUser = UserFacade.getLoggedUser();
        currentUser.setEmail(email.getText());
        currentUser.setPhone(phone.getText());
        currentUser.setName(fullName.getText());
        currentUser.save();
        JOptionPane.showMessageDialog(null, "Данные сохранены");
    }

    public void loadProfile() {
        User currentUser = UserFacade.getLoggedUser();
        email.setText(currentUser.getEmail());
        fullName.setText(currentUser.getName());
        phone.setText(currentUser.getPhone());
    }

    public void createComp() {
        OrganizerFacade.createCompetition(
                (City) cityNewComp.getValue(),
                nameNewComp.getText(),
                (Club) clubNewComp.getValue(),
                (CountingBoard) countingBoardNewComp.getValue(),
                (Judge) judgeMainNewComp.getValue(),
                (Judge) judge2NewComp.getValue(),
                (Judge) judge3NewComp.getValue(),
                (Judge) judge4NewComp.getValue(),
                (Judge) judge5NewComp.getValue(),
                nomDebut.selectedProperty().getValue(),
                nomE.selectedProperty().getValue(),
                nomD.selectedProperty().getValue(),
                nomC.selectedProperty().getValue(),
                nomB.selectedProperty().getValue(),
                nomA.selectedProperty().getValue(),
                nomAbc.selectedProperty().getValue(),
                dateNewComp.getText()
        );
        JOptionPane.showMessageDialog(null, "Создано новое соревнование");
    }

    public void showListComps() {
        ObservableList<Competition> obCompetitions = FXCollections.observableArrayList();
        obCompetitions.addAll(OrganizerFacade.getMyComp());
        listComps.setItems(obCompetitions);
        name.setCellValueFactory(new PropertyValueFactory<Competition,String>("name"));
        date.setCellValueFactory(new PropertyValueFactory<Competition, String>("date"));
        status.setCellValueFactory(new PropertyValueFactory<Competition, String>("statusName"));
        comments.setCellValueFactory(new PropertyValueFactory<Competition, String>("comments"));
        details.setCellValueFactory(new PropertyValueFactory<Competition, String>("details"));

    }

    public void terminate() {
        primaryStage.close();
    }

    public void selectOrder(Competition competition) {

    }

}