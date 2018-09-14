package dancecomp.gui;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import dancecomp.facade.CountingBoardFacade;
import dancecomp.facade.OrganizerFacade;
import dancecomp.facade.ASHFacade;
import dancecomp.facade.UserFacade;
import dancecomp.logics.CountingBoard;
import dancecomp.logics.Organizer;
import dancecomp.logics.ASH;
import dancecomp.logics.User;

import javax.swing.*;

public class LoginFormController {
    @FXML
    private TabPane mainPaneTabs;
    @FXML
    private Tab tabLogin;
    @FXML
    private Tab tabReg;

    @FXML
    private TextField email;
    @FXML
    private PasswordField password;

    @FXML
    private Button closeBtn;
    @FXML
    private Button entryBtn;

    // New user
    @FXML
    private RadioButton organizerRole;
    @FXML
    private RadioButton countingboardRole;
    @FXML
    private RadioButton ashRole;
    @FXML
    private TextField nameNew;
    @FXML
    private TextField emailNew;
    @FXML
    private TextField phoneNew;
    @FXML
    private PasswordField passwordNew;
    @FXML
    private PasswordField repeatPasswordNew;
    @FXML
    private Button createUserBtn;



    private Application mainClass;

    private Stage primaryStage;

    /**
     * Конструктор.
     * Конструктор вызывается раньше метода initialize().
     */
    public LoginFormController() {
    }

    /**
     * Инициализация класса-контроллера. Этот метод вызывается автоматически
     * после того, как fxml-файл будет загружен.
     */
    @FXML
    private void initialize() {

    }

    public void setGeneralVariable(Application mainClass, Stage mainStage) {
        this.mainClass = mainClass;
        this.primaryStage = mainStage;
    }

    // Геттеры и сеттеры

    public String getEmail() {
        return email.getText();
    }

    public void setEmail(String email) {
        this.email.setText(email);
    }

    public String getPassword() {
        return password.getText();
    }

    public void setPassword(String password) {
        this.password.setText(password);
    }

    // Обработчики событий

    public void login() {
        User currentUser = UserFacade.login(
                this.getEmail(),
                this.getPassword()
        );
        if (currentUser == null) {
            JOptionPane.showMessageDialog(null, "Ошибка входа. Неверные email или пароль.");
        } else {
            primaryStage.hide();
            JOptionPane.showMessageDialog(null, "Здравствуйте, " + currentUser.getName() + "!");
            UserFacade.setLoggedUser(currentUser);
            if (currentUser instanceof Organizer) {
                OrganizerFacade clientFacade = new OrganizerFacade();
                clientFacade.showMainWindow(this.mainClass, this.primaryStage);
            } else if (currentUser instanceof CountingBoard) {
                CountingBoardFacade carrierFacade = new CountingBoardFacade();
                carrierFacade.showMainWindow(this.mainClass, this.primaryStage);
            } else if (currentUser instanceof ASH) {
                ASHFacade operatorFacade = new ASHFacade();
                operatorFacade.showMainWindow(this.mainClass, this.primaryStage);
            }
        };
    }

    public void exit() {
        primaryStage.close();
    }

    public void createNewUser() {
        if (!passwordNew.getText().equals(repeatPasswordNew.getText())) {
            JOptionPane.showMessageDialog(null, "Пароли не совпадают!");
            return;
        }
        if (nameNew.getText().length() < 1) {
            JOptionPane.showMessageDialog(null, "Неверно введенёно имя");
            return;
        }
        if (emailNew.getText().length() < 1) {
            JOptionPane.showMessageDialog(null, "Неверно введенён email");
            return;
        }
        if (phoneNew.getText().length() < 1) {
            JOptionPane.showMessageDialog(null, "Не введён номер телефона");
            return;
        }
        if (passwordNew.getText().length() < 1) {
            JOptionPane.showMessageDialog(null, "Не введён пароль");
            return;
        }
        if (this.organizerRole.selectedProperty().getValue()) {   // Создаём организатора
            UserFacade.createOrganizer(this.nameNew.getText(), this.emailNew.getText(), this.passwordNew.getText(), this.phoneNew.getText());
        } else if (this.countingboardRole.selectedProperty().getValue()) {    // Создаём счетную комиссию
            UserFacade.createCountingBoard(this.nameNew.getText(), this.emailNew.getText(), this.passwordNew.getText(), this.phoneNew.getText());
        } else if (this.ashRole.selectedProperty().getValue()) {    // Создаём члена АСХ
            UserFacade.createASH(this.nameNew.getText(), this.emailNew.getText(), this.passwordNew.getText(), this.phoneNew.getText());
        }
        JOptionPane.showMessageDialog(null, "Пользователь успешно создан. Войдите в систему.");
        this.mainPaneTabs.getSelectionModel().select(0);
        this.tabReg.setDisable(true);
    }
}