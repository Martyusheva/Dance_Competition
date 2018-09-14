package dancecomp.facade;

import dancecomp.logics.Club;
import dancecomp.repositories.ClubsRepository;

import java.util.List;

public class ClubFacade {
    public static List<Club> getClubs() {
        return ClubsRepository.getInstance().getAll();
    }

    public static Club getClubById(int id) {
        return ClubsRepository.getInstance().getById(id);
    }
}
