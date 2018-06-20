package businesslogic;

import exception.NotAuthenticatedException;
import java.util.List;

/**
 * Created by moresmart on 19.06.18.
 */
public interface UserInterface {
    void setId(int id_);
    int getId();
    User getUser();
    void checkAuthenticated() throws NotAuthenticatedException;
    void addMessage(String message);
    List<Message> getMessages();
}
