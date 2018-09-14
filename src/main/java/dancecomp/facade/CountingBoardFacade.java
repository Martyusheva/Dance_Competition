package dancecomp.facade;

import dancecomp.repositories.CompetitionsRepository;
import javafx.application.Application;
import javafx.stage.Stage;
import dancecomp.gui.CountingBoardForm;
import dancecomp.logics.Competition;
import dancecomp.logics.User;
import dancecomp.repositories.UsersRepository;

import java.util.List;

public class CountingBoardFacade {

    /**
     * Вызывает главное окно перевозчика
     */
    public void showMainWindow(Application mainClass, Stage mainStage) {
        CountingBoardForm form = new CountingBoardForm(mainStage, mainClass);
        form.render();
    }

    public static List<Competition> getMyComp() {
        User currentUser = UsersRepository.getInstance().getCurrentUserObject();
        return CompetitionsRepository.getInstance().getAllByCountingBoard(currentUser);
    }

    public static List<User> getCountingBoards() {
        return UsersRepository.getInstance().getAll(User.TYPE_COUNTING_BOARD);
    }
}
