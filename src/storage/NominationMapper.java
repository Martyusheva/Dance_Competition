package storage;

import businesslogic.Competition;
import businesslogic.Nomination;
import businesslogic.NominationType;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NominationMapper implements Mapper<Nomination> {
    private static Set<Nomination> nominations = new HashSet<>();
    private Connection connection;

    public NominationMapper() throws IOException, SQLException {
        connection = DataGateway.getInstance().getDataSource().getConnection();
    }

    public List<Nomination> findNominationsOfCompetition(Competition competition_) throws SQLException {
        List<Nomination> result = new ArrayList<>();
        String extractSQL = "SELECT COMPETITION_NOMINATION.status FROM COMPETITION_NOMINATION WHERE COMPETITION_NOMINATION.competition = ?;";
        PreparedStatement extract = connection.prepareStatement(extractSQL);
        extract.setInt(1, competition_.getId());
        ResultSet rs = extract.executeQuery();
        while (rs.next()) {
            Nomination nomination = new Nomination(NominationType.valueOf((rs.getString("status"))));
            result.add(nomination);
        }
        return result;
    }

    @Override
    public Nomination findByID(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Nomination> findAll() throws SQLException{
        List<Nomination> all = new ArrayList<>();

        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT COMPETITION_NOMINATION.status FROM COMPETITION_NOMINATION;");
        while (rs.next()) {
            Nomination nomination = new Nomination(NominationType.valueOf((rs.getString("status"))));
            all.add(nomination);
        }

        return all;
    }

    @Override
    public void update(Nomination item) throws SQLException {
        return;
    }

    @Override
    public void closeConnection() throws SQLException {
        connection.close();
    }

    @Override
    public void clear() {
        nominations.clear();
    }

    @Override
    public void update() throws SQLException {
        for (Nomination it : nominations)
            update(it);
    }

}
