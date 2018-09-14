package dancecomp.repositories;


import dancecomp.mappers.CompetitionMapper;
import dancecomp.logics.Competition;
import dancecomp.logics.User;

import java.util.List;
import java.util.stream.Collectors;

public class CompetitionsRepository {
    private static CompetitionsRepository instance;

    public static CompetitionsRepository getInstance() {
        if (instance == null) {
            instance = new CompetitionsRepository();
        }

        return instance;
    }

    public List<Competition> getAll() {
        return CompetitionMapper.getAll();
    }

    public List<Competition> getAllByClient(User user) {
        return CompetitionMapper.getAll().stream().filter(order -> order.getOrganizerId() == user.getId()).collect(Collectors.toList());
    }

    public List<Competition> getAllByCountingBoard(User user) {
        return CompetitionMapper.getAll().stream().filter(order -> order.getCountingBoardId() == user.getId()).collect(Collectors.toList());
    }
}
