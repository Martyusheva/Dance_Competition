package exception;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String user) {
        super("User " + user + " not found");
    }
}
