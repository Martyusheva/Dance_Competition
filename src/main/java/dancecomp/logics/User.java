package dancecomp.logics;

import dancecomp.mappers.UserMapper;
import java.lang.String;

public class User {
    protected int id;
    protected String name;
    protected String email;
    protected String password;
    protected String phone;
    protected int type;

    public static final int TYPE_ASH = 1;
    public static final int TYPE_ORGANIZER = 2;
    public static final int TYPE_COUNTING_BOARD = 3;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean save() {
        return UserMapper.save(this);
    }

    public boolean remove() {
        return UserMapper.remove(this);
    }

    @Override
    public String toString() {
        return this.getName();
    }
}