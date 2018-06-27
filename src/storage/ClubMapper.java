package storage;

import businesslogic.Club;
import businesslogic.Competition;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ClubMapper implements Mapper<Club> {
    private static Set<Club> clubs = new HashSet<>();
    private Connection connection;

    public ClubMapper() throws IOException, SQLException {
        connection = DataGateway.getInstance().getDataSource().getConnection();
    }

    public Club findClubOfCompetition(Competition competition) throws SQLException {
        Club result;
        String extractSQL = "SELECT COMPETITION.club FROM COMPETITION WHERE COMPETITION.id = ?;";
        PreparedStatement extract = connection.prepareStatement(extractSQL);
        extract.setInt(1, competition.getId());
        ResultSet rs = extract.executeQuery();
        result = findByID(rs.getInt("club"));

        return result;
    }

    @Override
    public Club findByID(int id) throws SQLException {
        for (Club it : clubs)
            if (it.getId() == id) return it;

        String extractSQL = "SELECT * FROM CLUB WHERE id = ?;";
        PreparedStatement extract = connection.prepareStatement(extractSQL);
        extract.setInt(1, id);

        ResultSet rs = extract.executeQuery();
        if (!rs.next()) return null;

        String name = rs.getString("name");
        String city = rs.getString("city");

        Club club = new Club(id, name, city);
        clubs.add(club);

        return club;
    }

    @Override
    public List<Club> findAll() throws SQLException{
        List<Club> all = new ArrayList<>();

        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT CLUB.id FROM CLUB;");
        while (rs.next()) {
            Club club = findByID(rs.getInt("id"));
            all.add(club);
        }

        return all;
    }

    @Override
    public void update(Club item) throws SQLException {
        if (!clubs.contains(item)) {
            String insertSQL = "INSERT INTO CLUB(name, city) VALUES (?, ?);";
            PreparedStatement insert = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
            insert.setString(1, item.getName());
            insert.setString(2, item.getCity());
            insert.execute();
            ResultSet rs = insert.getGeneratedKeys();
            if (rs.next()) {
                long id = rs.getLong(1);
                item.setId((int) id);
            }
            clubs.add(item);
        }

    }

    @Override
    public void closeConnection() throws SQLException {
        connection.close();
    }

    @Override
    public void clear() {
        clubs.clear();
    }

    @Override
    public void update() throws SQLException {
        for (Club it : clubs)
            update(it);
    }
}
