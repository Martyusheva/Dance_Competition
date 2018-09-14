package dancecomp.logics;

import dancecomp.mappers.ClubMapper;

public class Club {
    protected int id;
    protected String name;

    public Club() {
        this.setId(0);
        this.setName("");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean save() {
        return ClubMapper.save(this);
    }

    public boolean remove() {
        return ClubMapper.remove(this);
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
