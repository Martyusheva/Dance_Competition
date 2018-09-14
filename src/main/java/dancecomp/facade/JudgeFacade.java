package dancecomp.facade;

import dancecomp.logics.Judge;
import dancecomp.repositories.JudgesRepository;

import java.util.List;

public class JudgeFacade {
    public static List<Judge> getJudges() {
        return JudgesRepository.getInstance().getAll();
    }

    public static Judge getJudgeById(int id) {
        return JudgesRepository.getInstance().getById(id);
    }
}
