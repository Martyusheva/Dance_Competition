package dancecomp.logics;

public class Organizer extends User {
    public Organizer(String name, String email, String password, String phone) {
        this.setName(name);
        this.setEmail(email);
        this.setPassword(password);
        this.setPhone(phone);
        this.id = 0;
        this.type = User.TYPE_ORGANIZER;

        this.save();
    }

}