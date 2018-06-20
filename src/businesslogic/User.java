package businesslogic;

import storage.StorageRepository;
import exception.NotAuthenticatedException;
import exception.DBConnectionException;
import exception.IncorrectPasswordException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by moresmart on 18.06.18.
 */
public class User implements UserInterface {
    private int id;
    private String login;
    private String name;
    private String city;
    private boolean authenticated;
    private List<Message> messages;

    public User(int id_, String login_, String name_, String city_, List<Message> messages_) {
        id = id_;
        login = login_;
        name = name_;
        city = city_;
        authenticated = false;
        messages = messages_;
    }

    public User(User user) {
        id = user.id;
        login = user.login;
        name = user.name;
        city = user.city;
        authenticated = user.authenticated;
        messages = new ArrayList<>();
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public void signIn(String password) throws DBConnectionException, IncorrectPasswordException {
        (new StorageRepository()).authenticateUser(this, password);
        authenticated = true;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void signOut() {
        authenticated = false;
    }

    public String toString() {
        return  login + ":" + name + "," + city;
    }

    @Override
    public void setId(int id_) {
        id = id_;
    }

    @Override
    public int getId() { return id; }

    @Override
    public User getUser() {
        return this;
    }

    @Override
    public void checkAuthenticated() throws NotAuthenticatedException {
        if (isAuthenticated()) return;
        throw new NotAuthenticatedException(toString() + " is not authenticated");
    }

    @Override
    public void addMessage(String message) {
        Message m = new Message(this, message);
        messages.add(m);
    }

    @Override
    public List<Message> getMessages() {
        return messages;
    }

    @Override
    public boolean equals(Object obj) {
        if ( (obj == null) ) return false;
        User other = (User)obj;
        return login.equals(other.login);
    }

    @Override
    public int hashCode() {
        return login.hashCode();
    }
}
