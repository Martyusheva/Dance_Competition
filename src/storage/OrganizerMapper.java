package storage;

import businesslogic.Competition;
import businesslogic.Organizer;
import businesslogic.User;
import exception.NoRightsException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OrganizerMapper implements UserMapperInterface<Organizer> {
    private static Set<Organizer> organizers = new HashSet<>();
    private Connection connection;
    private UserMapper userMapper;
    private CompetitionMapper competitionMapper;

    public OrganizerMapper() throws IOException, SQLException {
        connection = DataGateway.getInstance().getDataSource().getConnection();
        userMapper = new UserMapper();
        competitionMapper = new CompetitionMapper(this);
    }

    @Override
    public Organizer findByLogin(String login) throws SQLException {
        for (Organizer it : organizers)
            if (it.getLogin().equals(login)) return it;

        User user = userMapper.findByLogin(login);
        if (user == null) return null;
        Organizer organizer = new Organizer(user);
        organizers.add(organizer);

        /// Add manager projects
        for (Competition it : competitionMapper.findAllOrganizerCompetitions(organizer)) {
            try {
                organizer.addCompetition(it);
            } catch (NoRightsException e) {
                e.printStackTrace();
            }
        }
        return organizer;
    }

    @Override
    public Organizer findByID(int id) throws SQLException {
        for (Organizer it : organizers)
            if (it.getId() == id) return it;

        User user = userMapper.findByID(id);
        if (user == null) return null;
        Organizer organizer = new Organizer(user);
        organizers.add(organizer);

        /// Add manager projects
        for (Competition it : competitionMapper.findAllOrganizerCompetitions(organizer)) {
            try {
                organizer.addCompetition(it);
            } catch (NoRightsException e) {
                e.printStackTrace();
            }
        }

        return organizer;
    }

    @Override
    public List<Organizer> findAll() throws SQLException {
        organizers.clear();
        List<Organizer> all = new ArrayList<>();

        for (User it : userMapper.findAll()) {
            Organizer organizer = new Organizer(it);
            all.add(organizer);
            organizers.add(organizer);
        }
        return all;
    }

    @Override
    public void update(Organizer item) throws SQLException {
        if (!organizers.contains(item)) {
            userMapper.update(item);
            organizers.add(item);
        }

        for (Competition it : item.getCompetitions()) {
            competitionMapper.update(it);
        }
    }

    @Override
    public void closeConnection() throws SQLException {
        userMapper.closeConnection();
        competitionMapper.closeConnection();
        connection.close();
    }

    @Override
    public void clear() {
        competitionMapper.clear();
        userMapper.clear();
        organizers.clear();
    }

    @Override
    public void update() throws SQLException {
        competitionMapper.update();
        userMapper.update();
        for (Organizer it : organizers)
            update(it);
    }
}
