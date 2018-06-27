package businesslogic;

/**
 * Created by moresmart on 19.06.18.
 */
public class Club {
    private int id;
    private String name;
    private String city;

    public Club(int id_, String name_, String city_){
        id = id_;
        name = name_;
        city = city_;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() { return id; }

    public String getName() { return name; }

    public String getCity() { return city; }
}
