package storage;

import businesslogic.ASH;
import businesslogic.Competition;
import businesslogic.User;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ASHMapper implements UserMapperInterface<ASH> {
    private static Set<ASH> ashSet = new HashSet<>();
    private Connection connection;
    private UserMapper userMapper;
    private CompetitionMapper competitionMapper;

    public ASHMapper(CompetitionMapper cm) throws IOException, SQLException {
        connection = DataGateway.getInstance().getDataSource().getConnection();
        userMapper = new UserMapper();
        competitionMapper = cm;
    }

    public ASH findASHForCompetition(Competition competition) throws SQLException {
        for (ASH ash : ashSet)
            if (ash.getCompetitions().contains(competition)) return ash;

        String extractASH = "SELECT (COMPETITION_ASH.ash) FROM COMPETITION_ASH WHERE COMPETITION_ASH.competition = ?;";
        PreparedStatement stmt = connection.prepareStatement(extractASH);
        stmt.setInt(1, competition.getId());
        ResultSet rs = stmt.executeQuery();

        if (!rs.next()) return null;

        return findByID(rs.getInt("ash"));
    }

    @Override
    public ASH findByID(int id) throws SQLException {
        for (ASH it : ashSet)
            if (it.getId() == id) return it;

        User user = userMapper.findByID(id);
        if (user == null) return null;
        ASH ash = new ASH(user);
        ashSet.add(ash);

        String extractCompetition = "SELECT (COMPETITION_ASH.competition) FROM COMPETITION_ASH WHERE COMPETITION_ASH.ash = ?;";
        PreparedStatement stmt = connection.prepareStatement(extractCompetition);
        stmt.setInt(1, ash.getId());

        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            int competition = rs.getInt("competition");
            ash.addCompetition(competitionMapper.findByID(competition));
        }

        return ash;
    }

    @Override
    public List<ASH> findAll() throws SQLException {
        List<ASH> all = new ArrayList<>();

        String extractAll = "SELECT COMPETITION_ASH.ash FROM COMPETITION_ASH;";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(extractAll);
        while (rs.next()) {
            int user = rs.getInt("ash");
            all.add(findByID(user));
        }

        return all;
    }

    @Override
    public void update(ASH item) throws SQLException {
        if (!ashSet.contains(item)) {
            userMapper.update(item);
            ashSet.add(item);
        }
        for (Competition competition : item.getCompetitions()) {
            String insertSQL = "INSERT INTO COMPETITION_ASH(competition, ash) VALUES (?, ?) ON DUPLICATE KEY UPDATE ash = ?;";
            PreparedStatement statement = connection.prepareStatement(insertSQL);
            statement.setInt(1, competition.getId());
            statement.setInt(2, item.getId());
            statement.setInt(3, item.getId());
            statement.execute();
        }

    }

    @Override
    public void closeConnection() throws SQLException {
        userMapper.closeConnection();
        connection.close();
    }

    @Override
    public ASH findByLogin(String login) throws SQLException {
        for (ASH it : ashSet)
            if (it.getName().equals(login)) return it;

        User user = userMapper.findByLogin(login);
        if (user == null) return null;
        ASH ash = new ASH(user);
        ashSet.add(ash);

        String extractCompetition = "SELECT (COMPETITION_ASH.competition) FROM COMPETITION_ASH WHERE COMPETITION_ASH.ash = ?";
        PreparedStatement stmt = connection.prepareStatement(extractCompetition);
        stmt.setInt(1, ash.getId());

        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            int competition = rs.getInt("ash");
            ash.addCompetition(competitionMapper.findByID(competition));
        }

        return ash;
    }

    @Override
    public void clear() {
        userMapper.clear();
        ashSet.clear();
    }


    @Override
    public void update() throws SQLException {
        userMapper.update();
        for (ASH it : ashSet)
            update(it);
    }
}
