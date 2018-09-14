package dancecomp.facade;


import dancecomp.logics.User;
import dancecomp.repositories.UsersRepository;

public class UserFacade {

    public static User getLoggedUser() {
        return UsersRepository.getInstance().getCurrentUserObject();
    }

    public static void setLoggedUser(User user) {
        UsersRepository repo = UsersRepository.getInstance();
        repo.setCurrentUserObject(user);
    }

    public static User getById(int id) {
        return UsersRepository.getInstance().getById(id);
    }

    public static User login(String email, String password) {
        UsersRepository repo = UsersRepository.getInstance();
        return repo.getByEmailAndPassword(email, password);
    }

    public static User createOrganizer(String name, String email, String password, String phone) {
        UsersRepository rep = UsersRepository.getInstance();
        return rep.createOrganizer(name, email, password, phone);
    }

    public static User createCountingBoard(String name, String email, String password, String phone) {
        UsersRepository rep = UsersRepository.getInstance();
        return rep.createCountingBoard(name, email, password, phone);
    }

    public static User createASH(String name, String email, String password, String phone) {
        UsersRepository rep = UsersRepository.getInstance();
        return rep.createASH(name, email, password, phone);
    }
}
