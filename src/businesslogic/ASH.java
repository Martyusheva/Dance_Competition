package businesslogic;

import exception.CompetitionNotExistsException;
import exception.NoRightsException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by moresmart on 19.06.18.
 */
public class ASH extends User {
    private List<Competition> competitions;

    public ASH(User user){
        super(user);
        competitions = new ArrayList<>();
    }

    public void addCompetition(Competition competition_){
        competitions.add(competition_);
    }

    public void doReview(Competition competition_)
    throws CompetitionNotExistsException{
        if (!competitions.contains(competition_))
            throw new CompetitionNotExistsException(competition_.getName());

        int judgenum = competition_.getJudges().size();
        if(judgenum<5)
                setCompetitionStatus(competition_, CompetitionStatus.NEED_REVISION);
        else setCompetitionStatus(competition_, CompetitionStatus.APPROVED);
        final boolean remove = competitions.remove(competition_);
    }

    public void setCompetitionStatus(Competition competition_, CompetitionStatus status_) {
        competition_.setCompetitionStatus(status_);
    }

    public List<Competition> getCompetitions(){ return competitions; }
}
