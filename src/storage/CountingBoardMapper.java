package storage;

import businesslogic.ASH;
import businesslogic.Competition;
import businesslogic.CountingBoard;
import businesslogic.User;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CountingBoardMapper implements UserMapperInterface<CountingBoard> {
    private static Set<CountingBoard> countingBoards = new HashSet<>();
    private Connection connection;
    private UserMapper userMapper;
    private CompetitionMapper competitionMapper;

    public CountingBoardMapper(CompetitionMapper cm) throws IOException, SQLException {
        connection = DataGateway.getInstance().getDataSource().getConnection();
        userMapper = new UserMapper();
        competitionMapper = cm;
    }

    public CountingBoard findCBForCompetition(Competition competition) throws SQLException {
        for (CountingBoard countingBoard : countingBoards)
            if (countingBoard.getCompetitions().contains(competition)) return countingBoard;

        String extractCB = "SELECT (COMPETITION_CB.countingBoard) FROM COMPETITION_CB WHERE COMPETITION_CB.countingBoard = ?;";
        PreparedStatement stmt = connection.prepareStatement(extractCB);
        stmt.setInt(1, competition.getId());
        ResultSet rs = stmt.executeQuery();

        if (!rs.next()) return null;

        return findByID(rs.getInt("countingBoard"));
    }

    @Override
    public CountingBoard findByID(int id) throws SQLException {
        for (CountingBoard it : countingBoards)
            if (it.getId() == id) return it;

        User user = userMapper.findByID(id);
        if (user == null) return null;
        CountingBoard countingBoard = new CountingBoard(user);
        countingBoards.add(countingBoard);

        String extractCompetition = "SELECT (COMPETITION_CB.competition) FROM COMPETITION_CB WHERE COMPETITION_CB.countingBoard = ?;";
        PreparedStatement stmt = connection.prepareStatement(extractCompetition);
        stmt.setInt(1, countingBoard.getId());

        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            int competition = rs.getInt("competition");
            countingBoard.addCompetition(competitionMapper.findByID(competition));
        }

        return countingBoard;
    }

    @Override
    public List<CountingBoard> findAll() throws SQLException {
        List<CountingBoard> all = new ArrayList<>();

        String extractAll = "SELECT COMPETITION_CB.countingBoard FROM COMPETITION_CB;";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(extractAll);
        while (rs.next()) {
            int user = rs.getInt("countingBoard");
            all.add(findByID(user));
        }

        return all;
    }

    @Override
    public void update(CountingBoard item) throws SQLException {
        if (!countingBoards.contains(item)) {
            userMapper.update(item);
            countingBoards.add(item);
        }
        for (Competition competition : item.getCompetitions()) {
            String insertSQL = "INSERT INTO COMPETITION_CB(competition, countingBoard) VALUES (?, ?) ON DUPLICATE KEY UPDATE countingBoard = ?;";
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
    public CountingBoard findByLogin(String login) throws SQLException {
        for (CountingBoard it : countingBoards)
            if (it.getName().equals(login)) return it;

        User user = userMapper.findByLogin(login);
        if (user == null) return null;
        CountingBoard countingBoard = new CountingBoard(user);
        countingBoards.add(countingBoard);

        String extractCompetition = "SELECT (COMPETITION_CB.competition) FROM COMPETITION_CB WHERE COMPETITION_CB.countingBoard = ?";
        PreparedStatement stmt = connection.prepareStatement(extractCompetition);
        stmt.setInt(1, countingBoard.getId());

        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            int competition = rs.getInt("countingBoard");
            countingBoard.addCompetition(competitionMapper.findByID(competition));
        }

        return countingBoard;
    }

    @Override
    public void clear() {
        userMapper.clear();
        countingBoards.clear();
    }


    @Override
    public void update() throws SQLException {
        userMapper.update();
        for (CountingBoard it : countingBoards)
            update(it);
    }
}
