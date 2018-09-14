package dancecomp.logics;

public class CountingBoard extends User {
    public CountingBoard(String name, String email, String password, String phone) {
        this.setName(name);
        this.setEmail(email);
        this.setPassword(password);
        this.setPhone(phone);
        this.id = 0;
        this.type = User.TYPE_COUNTING_BOARD;

        this.save();
    }

}