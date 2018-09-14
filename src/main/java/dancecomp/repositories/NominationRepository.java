package dancecomp.repositories;

import dancecomp.mappers.NominationMapper;
import dancecomp.logics.Nomination;

import java.util.List;

public class NominationRepository {
    private static NominationRepository instance;

    public static NominationRepository getInstance() {
        if (instance == null) {
            instance = new NominationRepository();
        }

        return instance;
    }

    public List<Nomination> getAll() {
        return NominationMapper.getAll();
    }

    public Nomination getById(int id) {
        return NominationMapper.getById(id);
    }
}
