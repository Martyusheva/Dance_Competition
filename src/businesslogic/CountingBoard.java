package businesslogic;

import storage.StorageRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by moresmart on 19.06.18.
 */
public class CountingBoard extends User {
    //private StorageRepository repository;
    private List<Competition> competitions;

    public CountingBoard(User user){
        super(user);
        competitions = new ArrayList<>();
        //repository = new StorageRepository();
    }

    public void addCompetition( Competition competition_){
        competitions.add(competition_);
    }

    public List<Competition> getCompetitions(){ return competitions; }

}
