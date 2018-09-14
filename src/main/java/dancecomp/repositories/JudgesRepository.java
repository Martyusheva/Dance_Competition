package dancecomp.repositories;

import dancecomp.mappers.JudgeMapper;
import dancecomp.logics.Judge;

import java.util.List;

public class JudgesRepository {
    private static JudgesRepository instance;

    public static JudgesRepository getInstance() {
        if (instance == null) {
            instance = new JudgesRepository();
        }

        return instance;
    }

    public List<Judge> getAll() {
        return JudgeMapper.getAll();
    }

    public Judge getById(int id) {
        return JudgeMapper.getById(id);
    }
}
