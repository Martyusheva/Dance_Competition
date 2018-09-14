package dancecomp.repositories;

import dancecomp.mappers.ClubMapper;
import dancecomp.logics.Club;

import java.util.List;

public class ClubsRepository {
    private static ClubsRepository instance;

    public static ClubsRepository getInstance() {
        if (instance == null) {
            instance = new ClubsRepository();
        }

        return instance;
    }

    public List<Club> getAll() {
        return ClubMapper.getAll();
    }

    public Club getById(int id) {
        return ClubMapper.getById(id);
    }
}
