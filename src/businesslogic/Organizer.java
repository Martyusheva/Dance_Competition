package businesslogic;

import exception.DBConnectionException;
import exception.NoRightsException;
import exception.DuplicatedParameterException;
import exception.CompetitionAlreadyExistsException;
import storage.StorageRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by moresmart on 19.06.18.
 */
public class Organizer extends User {
    private StorageRepository repository;
    private List<Competition> competitions;

    public Organizer(User user) {
        super(user);
        repository = new StorageRepository();
        competitions = new ArrayList<>();
    }

    public Competition createCompetition(String name) throws DBConnectionException, CompetitionAlreadyExistsException {
        Competition competition = repository.addCompetition(name, this);//new Competition(name, city, date, this);
        competitions.add(competition);
        return competition;
    }

    public void addCompetition(Competition competition) throws NoRightsException {
        if (!competition.getOrganizer().equals(this)) throw new NoRightsException("Can't add competition to wrong organizer");
        if (!competitions.contains(competition)) competitions.add(competition);
    }

    public List<Competition> getCompetitions() { return competitions; }

    public void setClub(Competition competition_, Club club_)
            throws DBConnectionException, NoRightsException {
        if (!competitions.contains(competition_))
            throw new NoRightsException("User " + getLogin() + " cannot change competition " + competition_.getName());
        Club club = repository.getClub(club_.getId());
        competition_.setClub(club);
    }

    public void setCountingBoard(Competition competition_, User user_)
            throws DBConnectionException, NoRightsException {
        if (!competitions.contains(competition_))
            throw new NoRightsException("User " + getLogin() + " cannot change competition " + competition_.getName());
        CountingBoard countingBoard = repository.getCountingBoard(user_);
        competition_.setCountingBoard(countingBoard);
        countingBoard.addCompetition(competition_);
    }

    public void addJudge(Competition competition_, Judge judge_)
            throws DBConnectionException, NoRightsException, DuplicatedParameterException {
        if (!competitions.contains(competition_))
            throw new NoRightsException("User " + getLogin() + " cannot change competition " + competition_.getName());
        Judge judge = repository.getJudge(judge_.getId());
        competition_.addJudge(judge);
    }

    public void addNomination(Competition competition_, Nomination nomination_)
            throws NoRightsException, DuplicatedParameterException {
        if (!competitions.contains(competition_))
            throw new NoRightsException("User " + getLogin() + " cannot change competition " + competition_.getName());
        competition_.addNomination(nomination_);
    }

    public void sendToReview(Competition competition_, User user_)
            throws DBConnectionException, NoRightsException{
        if (!competitions.contains(competition_))
            throw new NoRightsException("User " + getLogin() + " cannot manage competition " + competition_.getName());
        ASH ash = repository.getASH(user_);
        ash.addCompetition(competition_);
    }

    public void setCompetitionStatus(Competition competition_, CompetitionStatus status_)
            throws NoRightsException {
        if (!competitions.contains(competition_))
            throw new NoRightsException("User " + getLogin() + " cannot change competition " + competition_.getName());
        if (!(competition_.getStatus().equals(CompetitionStatus.APPROVED)
                && competition_.getStatus().equals(CompetitionStatus.REGISTRATION_IS_OPEN)))
            throw new NoRightsException("User " + getLogin() + " cannot change competition " + competition_.getName());
        if (!(status_.equals(CompetitionStatus.REGISTRATION_IS_OPEN)
                && status_.equals(CompetitionStatus.REGISTRATION_IS_CLOSED)))
            throw new NoRightsException("User " + getLogin() + " cannot change competition " + competition_.getName());
        competition_.setCompetitionStatus(status_);
    }
}
