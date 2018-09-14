package dancecomp.facade;

import dancecomp.repositories.CompetitionsRepository;
import javafx.application.Application;
import javafx.stage.Stage;
import dancecomp.gui.ASHForm;
import dancecomp.logics.Competition;
import dancecomp.logics.User;
import dancecomp.repositories.UsersRepository;

import java.util.List;

public class ASHFacade {

    /**
     * Вызывает главное окно члена АСХ
     */
    public void showMainWindow(Application mainClass, Stage mainStage) {
        ASHForm form = new ASHForm(mainStage, mainClass);
        form.render();
    }

    public static List<Competition> getCompetitions() {
        return CompetitionsRepository.getInstance().getAll();
    }

    public static List<User> getCountingBoards() {
        return UsersRepository.getInstance().getAll(User.TYPE_COUNTING_BOARD);
    }
}
