package exception;

/**
 * Created by moresmart on 19.06.18.
 */
public class DBConnectionException extends Exception {
    public DBConnectionException() {
        super("Could not connect to database");
    }
}
