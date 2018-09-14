package dancecomp.facade;

import dancecomp.logics.*;
import javafx.application.Application;
import javafx.stage.Stage;
import dancecomp.gui.OrganizerForm;
import dancecomp.repositories.CompetitionsRepository;
import dancecomp.repositories.UsersRepository;


import java.util.List;

public class OrganizerFacade {


    /**
     * Вызывает главное окно организатора
     */
    public void showMainWindow(Application mainClass, Stage mainStage) {
        OrganizerForm organizerForm = new OrganizerForm(mainStage, mainClass);
        organizerForm.render();
    }

    public static void createCompetition(
            City city,
            String name,
            Club club,
            CountingBoard countingBoard,
            Judge judgeMain,
            Judge judge2,
            Judge judge3,
            Judge judge4,
            Judge judge5,
            boolean debut,
            boolean e,
            boolean d,
            boolean c,
            boolean b,
            boolean a,
            boolean abc,
            String date
    ) {
        User organizer = UsersRepository.getInstance().getCurrentUserObject();
        Competition competition = new Competition(city.getId(), name, club.getId(), organizer.getId(), countingBoard.getId(), judgeMain.getId(), judge2.getId(), judge3.getId(), judge4.getId(), judge5.getId(), debut, e, d,
                 c, b, a, abc, date);
    }

    public static List<Competition> getMyComp() {
        User currentUser = UsersRepository.getInstance().getCurrentUserObject();
        return CompetitionsRepository.getInstance().getAllByClient(currentUser);
    }

}
