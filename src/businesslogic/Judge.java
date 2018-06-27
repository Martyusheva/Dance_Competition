package businesslogic;

/**
 * Created by moresmart on 18.06.18.
 */
public class Judge {
    int id;
    String name;
    int category;

    public Judge(int id_, String name_, int category_){
        id = id_;
        name = name_;
        category = category_;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() { return id; }
    public String getName(){ return name; }
    public int getCategory(){ return category; }
}
