package dancecomp.logics;

public class ASH extends User {
    public ASH(String name, String email, String password, String phone) {
        this.setName(name);
        this.setEmail(email);
        this.setPassword(password);
        this.setPhone(phone);
        this.id = 0;
        this.type = User.TYPE_ASH;

        this.save();
    }

}