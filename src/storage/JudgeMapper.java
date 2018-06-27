package storage;

import businesslogic.Club;
import businesslogic.Competition;
import businesslogic.Judge;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JudgeMapper implements Mapper<Judge> {
    private static Set<Judge> judges = new HashSet<>();
    private Connection connection;

    public JudgeMapper() throws IOException, SQLException {
        connection = DataGateway.getInstance().getDataSource().getConnection();
    }

    public List<Judge> findJudgeOfCompetition(Competition competition) throws SQLException {
        List<Judge> result = new ArrayList<>();
        String extractSQL = "SELECT COMPETITION_JUDGE.judge FROM COMPETITION_JUDGE WHERE COMPETITION_JUDGE.competition = ?;";
        PreparedStatement extract = connection.prepareStatement(extractSQL);
        extract.setInt(1, competition.getId());
        ResultSet rs = extract.executeQuery();
        while (rs.next()) {
            result.add(findByID(rs.getInt("judge")));
        }
        return result;
    }

    @Override
    public Judge findByID(int id) throws SQLException {
        for (Judge it : judges)
            if (it.getId() == id) return it;

        String extractSQL = "SELECT * FROM JUDGE WHERE id = ?;";
        PreparedStatement extract = connection.prepareStatement(extractSQL);
        extract.setInt(1, id);

        ResultSet rs = extract.executeQuery();
        if (!rs.next()) return null;

        String name = rs.getString("name");
        int rang = rs.getInt("rang");

        Judge judge = new Judge(id, name, rang);
        judges.add(judge);

        return judge;
    }

    @Override
    public List<Judge> findAll() throws SQLException{
        List<Judge> all = new ArrayList<>();

        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT JUDGE.id FROM JUDGE;");
        while (rs.next()) {
            Judge judge = findByID(rs.getInt("id"));
            all.add(judge);
        }

        return all;
    }

    @Override
    public void update(Judge item) throws SQLException {
        if (!judges.contains(item)) {
            String insertSQL = "INSERT INTO JUDGE(name, rang) VALUES (?, ?);";
            PreparedStatement insert = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
            insert.setString(1, item.getName());
            insert.setInt(2, item.getCategory());
            insert.execute();
            ResultSet rs = insert.getGeneratedKeys();
            if (rs.next()) {
                long id = rs.getLong(1);
                item.setId((int) id);
            }
            judges.add(item);
        }

    }

    @Override
    public void closeConnection() throws SQLException {
        connection.close();
    }

    @Override
    public void clear() {
        judges.clear();
    }

    @Override
    public void update() throws SQLException {
        for (Judge it : judges)
            update(it);
    }
}
