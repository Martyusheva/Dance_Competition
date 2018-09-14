package dancecomp.facade;

import dancecomp.logics.Nomination;
import dancecomp.repositories.NominationRepository;

import java.util.List;

public class NominationFacade {
    public static List<Nomination> getNominations() {
        return NominationRepository.getInstance().getAll();
    }

    public static Nomination getNominationById(int id) {
        return NominationRepository.getInstance().getById(id);
    }
}
