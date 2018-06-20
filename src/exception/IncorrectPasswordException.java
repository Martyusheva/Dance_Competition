package exception;

/**
 * Created by moresmart on 19.06.18.
 */
public class IncorrectPasswordException extends Exception {
    public IncorrectPasswordException() {
        super("Incorrect login or password");
    }
}
