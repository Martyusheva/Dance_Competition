package storage;

/**
 * Created by moresmart on 19.06.18.
 */
import businesslogic.*;
import exception.*;

import java.io.IOException;
import java.sql.SQLException;

public class StorageRepository {

    private static UserMapper userMapper;
    private static OrganizerMapper organizerMapper;
    private static CountingBoardMapper countingBoardMapper;
    private static ASHMapper ashMapper;
    private static CompetitionMapper competitionMapper;
    private static ClubMapper clubMapper;
    private static JudgeMapper judgeMapper;


    public StorageRepository() {
        try {
            if (userMapper == null) userMapper = new UserMapper();
            if (organizerMapper == null) organizerMapper = new OrganizerMapper();
            if (competitionMapper == null) competitionMapper = new CompetitionMapper(organizerMapper);
            if (countingBoardMapper == null) countingBoardMapper = new CountingBoardMapper(competitionMapper);
            if (ashMapper == null) ashMapper = new ASHMapper(competitionMapper);
            if (clubMapper == null) clubMapper = new ClubMapper();
            if (judgeMapper == null) judgeMapper = new JudgeMapper();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addUser(String login, String name, String password)
            throws UserAlreadyExistsException, DBConnectionException {
        try {
            if (userMapper.findByLogin(login) != null)
                throw new UserAlreadyExistsException(login);

            User newUser = new User(0, login, name, null);
            userMapper.addUser(newUser, password);
        } catch (SQLException e) {
            throw new DBConnectionException();
        }
    }

    public User getUser(String login) throws UserNotFoundException, DBConnectionException {
        try {
            User user = userMapper.findByLogin(login);
            if (user == null) throw new UserNotFoundException(login);
            return user;
        } catch (SQLException e) {
            throw new DBConnectionException();
        }
    }

    public Organizer getOrganizer(User user) throws DBConnectionException {
        try {
            return organizerMapper.findByLogin(user.getLogin());
        } catch (SQLException e) {
            throw new DBConnectionException();
        }
    }

    public CountingBoard getCountingBoard(User user) throws DBConnectionException {
        try {
            return countingBoardMapper.findByLogin(user.getLogin());
        } catch (SQLException e) {
            throw new DBConnectionException();
        }
    }

    public ASH getASH(User user) throws DBConnectionException {
        try {
            return ashMapper.findByLogin(user.getLogin());
        } catch (SQLException e) {
            throw new DBConnectionException();
        }
    }

    public void authenticateUser(String login, String password) throws DBConnectionException, IncorrectPasswordException {
        // replase UserNotFound exception with IncorrectPassword exception,
        // so nobody can find out if login was correct or not
        try {
            getUser(login).signIn(password);
        } catch (UserNotFoundException e) {
            throw new IncorrectPasswordException();
        }
    }

    public void authenticateUser(User user, String password) throws DBConnectionException, IncorrectPasswordException {
        try {
            if (!userMapper.authenticateUser(user, password))
                throw new IncorrectPasswordException();
        } catch (SQLException e) {
            throw new DBConnectionException();
        }
    }

    public Competition addCompetition(String name, Organizer organizer)
            throws DBConnectionException, CompetitionAlreadyExistsException {
        Competition competition = new Competition(name, organizer);
        try {
            if (competitionMapper.findByName(name) != null) throw new CompetitionAlreadyExistsException(name);
            competitionMapper.update(competition);
            return competition;
        } catch (SQLException e) {
            throw new DBConnectionException();
        }
    }

    public Competition getCompetition(String name) throws DBConnectionException {
        try {
            return competitionMapper.findByName(name);
        } catch (SQLException e) {
            throw new DBConnectionException();
        }
    }

    public Competition getCompetition(int id) throws DBConnectionException {
        try {
            return competitionMapper.findByID(id);
        } catch (SQLException e) {
            throw new DBConnectionException();
        }
    }

    public Club getClub(int id) throws DBConnectionException {
        try {
            return clubMapper.findByID(id);
        } catch (SQLException e) {
            throw new DBConnectionException();
        }
    }

    public Judge getJudge(int id) throws DBConnectionException {
        try {
            return judgeMapper.findByID(id);
        } catch (SQLException e) {
            throw new DBConnectionException();
        }
    }

    public void clear() {
        userMapper.clear();
        organizerMapper.clear();
        competitionMapper.clear();
        countingBoardMapper.clear();
        ashMapper.clear();
        clubMapper.clear();
        judgeMapper.clear();
    }

    public void update() throws SQLException {
        userMapper.update();
        organizerMapper.update();
        competitionMapper.update();
        countingBoardMapper.update();
        ashMapper.update();
        clubMapper.update();
        judgeMapper.update();
    }

    synchronized public void drop() throws DBConnectionException {
        try {
            DataGateway.getInstance().dropAll();
        } catch (SQLException e) {
            throw new DBConnectionException();
        }
    }
}