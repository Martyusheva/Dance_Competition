package storage;

import businesslogic.*;
import exception.DuplicatedParameterException;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CompetitionMapper implements Mapper<Competition> {
    private static Set<Competition> competitions = new HashSet<>();
    private Connection connection;
    private OrganizerMapper organizerMapper;
    private CountingBoardMapper countingBoardMapper;
    private ClubMapper clubMapper;
    private JudgeMapper judgeMapper;
    private NominationMapper nominationMapper;

    public CompetitionMapper(OrganizerMapper om) throws IOException, SQLException {
        connection = DataGateway.getInstance().getDataSource().getConnection();
        organizerMapper = om;
        countingBoardMapper = new CountingBoardMapper(this);
        clubMapper = new ClubMapper();
        judgeMapper = new JudgeMapper();
        nominationMapper = new NominationMapper();
    }

    public List<Competition> findAllOrganizerCompetitions(Organizer organizer) throws SQLException {
        List<Competition> orgCompetition = new ArrayList<>();

        String findSQL = "SELECT * FROM COMPETITION WHERE COMPETITION.organizer = ?;";
        PreparedStatement extract = connection.prepareStatement(findSQL);
        extract.setInt(1, organizer.getId());
        ResultSet rs = extract.executeQuery();
        while (rs.next()) {
            int compid = rs.getInt("id");
            for (Competition it : competitions) {
                if (it.getId() == compid) {
                    orgCompetition.add(it);
                    continue;
                }
            }

            String name = rs.getString("name");
            Competition competition = new Competition(compid, name, organizer);
            competitions.add(competition);

            try {
                CountingBoard cb = countingBoardMapper.findCBForCompetition(competition);
                if (cb != null) competition.setCountingBoard(cb);
                orgCompetition.add(competition);

                for (Judge judge : judgeMapper.findJudgeOfCompetition(competition)) {
                    competition.addJudge(judge);
                }
                Club club = clubMapper.findClubOfCompetition(competition);
                if (club != null) competition.setClub(club);

                for (Nomination nomination : nominationMapper.findNominationsOfCompetition(competition)) {
                    competition.addNomination(nomination);
                }

            } catch (DuplicatedParameterException e) {
                e.printStackTrace();
            }
        }

        return orgCompetition;
    }

    public Competition findByName(String name) throws SQLException {
        for (Competition it : competitions)
            if (it.getName().equals(name)) return it;

        String findSQL = "SELECT * FROM COMPETITION WHERE COMPETITION.name = ?;";
        PreparedStatement extract = connection.prepareStatement(findSQL);
        extract.setString(1, name);
        ResultSet rs = extract.executeQuery();

        if (!rs.next()) return null;

        Organizer organizer = organizerMapper.findByID(rs.getInt("organizer"));
        Competition competition = new Competition(rs.getInt("id"), name, organizer);
        competitions.add(competition);

        try {
            CountingBoard cb = countingBoardMapper.findCBForCompetition(competition);
            if (cb != null) competition.setCountingBoard(cb);

            for (Judge judge : judgeMapper.findJudgeOfCompetition(competition)) {
                competition.addJudge(judge);
            }
            Club club = clubMapper.findClubOfCompetition(competition);
            if (club != null) competition.setClub(club);

            for (Nomination nomination : nominationMapper.findNominationsOfCompetition(competition)) {
                competition.addNomination(nomination);
            }

        } catch (DuplicatedParameterException e) {
            e.printStackTrace();
        }

        return competition;
    }

    @Override
    public Competition findByID(int id) throws SQLException {
        for (Competition it : competitions)
            if (it.getId() == id) return it;

        String findSQL = "SELECT * FROM COMPETITION WHERE COMPETITION.id = ?;";
        PreparedStatement extract = connection.prepareStatement(findSQL);
        extract.setInt(1, id);
        ResultSet rs = extract.executeQuery();

        if (!rs.next()) return null;

        Organizer organizer = organizerMapper.findByID(rs.getInt("organizer"));
        String name = rs.getString("name");
        Competition competition = new Competition(id, name, organizer);
        competitions.add(competition);

        try {
            CountingBoard cb = countingBoardMapper.findCBForCompetition(competition);
            if (cb != null) competition.setCountingBoard(cb);

            for (Judge judge : judgeMapper.findJudgeOfCompetition(competition)) {
                competition.addJudge(judge);
            }
            Club club = clubMapper.findClubOfCompetition(competition);
            if (club != null) competition.setClub(club);

            for (Nomination nomination : nominationMapper.findNominationsOfCompetition(competition)) {
                competition.addNomination(nomination);
            }

        } catch (DuplicatedParameterException e) {
            e.printStackTrace();
        }

        return competition;
    }

    @Override
    public List<Competition> findAll() throws SQLException {
        List<Competition> all = new ArrayList<>();

        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT COMPETITION.id FROM COMPETITION;");
        while (rs.next())
            all.add(findByID(rs.getInt("id")));

        return all;
    }

    @Override
    public void update(Competition item) throws SQLException {
        if (!competitions.contains(item)) {
            String insertSQL = "INSERT INTO COMPETITION(name, organizer, status) VALUES (?, ?, ?);";
            PreparedStatement stmt = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, item.getName());
            stmt.setInt(2, item.getOrganizer().getId());
            stmt.setString(3, item.getStStatus());
            stmt.execute();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                long id = rs.getLong(1);
                item.setId((int) id);
            }
            competitions.add(item);
        }
        if (item.getCountingBoard() != null)
            countingBoardMapper.update(item.getCountingBoard());

        if (item.getClub() != null)
            clubMapper.update(item.getClub());

        for (Nomination nomination : item.getNominations())
            nominationMapper.update(nomination);

        for (Judge judge : item.getJudges())
            judgeMapper.update(judge);
    }

    @Override
    public void closeConnection() throws SQLException {
        organizerMapper.closeConnection();
        countingBoardMapper.closeConnection();
        clubMapper.closeConnection();
        nominationMapper.closeConnection();
        judgeMapper.closeConnection();
        connection.close();
    }

    @Override
    public void clear() {
        organizerMapper.clear();
        countingBoardMapper.clear();
        clubMapper.clear();
        nominationMapper.clear();
        judgeMapper.clear();
        competitions.clear();
    }

    @Override
    public void update() throws SQLException {
        organizerMapper.update();
        countingBoardMapper.update();
        clubMapper.update();
        nominationMapper.update();
        judgeMapper.update();
        for (Competition it : competitions)
            update(it);
    }

}
