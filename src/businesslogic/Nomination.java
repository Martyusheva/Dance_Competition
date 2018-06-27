package businesslogic;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by moresmart on 18.06.18.
 */
public class Nomination {
    private boolean isRating = true;
    private NominationType type;
    private Set<Judge> judges;
    private Set<CompetitorPair> competitior_pair;
    private NominationResult nominationResult;

    public Nomination(NominationType type_){
        type = type_;
        judges = new HashSet<>();
        competitior_pair = new HashSet<>();
    }
    public String getType(){
        return type.toString();
    }
}
