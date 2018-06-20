package businesslogic;

import exception.DuplicatedParameterException;
import java.util.Date;
import java.util.Set;

/**
 * Created by moresmart on 18.06.18.
 */
public class Competition {
    private String name;
    private String city;
    private Date date;
    private Organizer organizer;
    private Club club;
    private CountingBoard countingBoard;
    private CompetitionStatus status;
    private Set<Judge> judges;
    private Set<Nomination> nominations;

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

    public String getName(){ return name; }
    public String getCity(){ return city; }
    public Date getDate(){ return  date; }
    public Set<Judge> getJudges(){ return judges; }
    public CompetitionStatus getStatus(){ return status; }
    public String getStStatus(){ return status.toString(); }

}
