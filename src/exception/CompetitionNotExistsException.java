package exception;

public class CompetitionNotExistsException extends Exception {
    public CompetitionNotExistsException(String competition) {
        super("Competition " + competition + " NOT exists");
    }
}
