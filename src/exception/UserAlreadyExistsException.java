package exception;

public class UserAlreadyExistsException extends Exception {
    public UserAlreadyExistsException(String user) {
        super("User " + user + " already exists");
    }
}
