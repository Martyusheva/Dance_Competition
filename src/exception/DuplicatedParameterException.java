package exception;

/**
 * Created by moresmart on 19.06.18.
 */
public class DuplicatedParameterException extends Exception {
    public DuplicatedParameterException (String param, String competition) {
        super("Parameter " + param + " has already contained in competition " + competition);
    }
}
