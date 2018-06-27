package businesslogic;

import exception.DuplicatedParameterException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by moresmart on 18.06.18.
 */
public class Competition {
    private int id;
    private String name;
    private String city;
    private Date date;
    private Organizer organizer;
    private Club club;
    private CountingBoard countingBoard;
    private CompetitionStatus status;
    private Set<Judge> judges;
    private Set<Nomination> nominations;


    public Competition(String name_, Organizer organizer_){
        name = name_;
        organizer = organizer_;
        judges = new HashSet<>();
        nominations = new HashSet<>();
        status = CompetitionStatus.WAITING_FOR_REVIEW;
    }

    public Competition(int id_, String name_, Organizer organizer_){
        id = id_;
        name = name_;
        organizer = organizer_;
        judges = new HashSet<>();
        nominations = new HashSet<>();
        status = CompetitionStatus.WAITING_FOR_REVIEW;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setClub(Club club_){ club = club_; }

    public void setCountingBoard(CountingBoard countingBoard_){
        countingBoard = countingBoard_;
    }

    public void addJudge(Judge judge_) throws DuplicatedParameterException {
        if (judges.contains(judge_)) throw new DuplicatedParameterException(judge_.getName(), getName());
        judges.add(judge_);
    }

    public void addNomination(Nomination nomination_) throws DuplicatedParameterException {
        if (nominations.contains(nomination_)) throw new DuplicatedParameterException(nomination_.getType(), getName());
        nominations.add(nomination_);
    }

    public void setCompetitionStatus (CompetitionStatus status_){
        status = status_;
    }

    public int getId() { return id; }
    public String getName(){ return name; }
    public String getCity(){ return city; }
    public Date getDate(){ return  date; }
    public Organizer getOrganizer(){ return organizer; }
    public Set<Judge> getJudges(){ return judges; }
    public CompetitionStatus getStatus(){ return status; }
    public String getStStatus(){ return status.toString(); }
    public CountingBoard getCountingBoard(){ return countingBoard; }
    public Club getClub(){ return  club; }

    public Set<Nomination> getNominations() {
        return nominations;
    }
}
