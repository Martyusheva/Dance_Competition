package exception;

import businesslogic.Competition;

/**
 * Created by moresmart on 19.06.18.
 */
public class CompetitionAlreadyExistsException extends Exception {
    public CompetitionAlreadyExistsException(String competition) {
        super("Project " + competition + " already exists");
    }
}
