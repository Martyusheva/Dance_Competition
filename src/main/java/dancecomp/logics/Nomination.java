package dancecomp.logics;

import dancecomp.mappers.NominationMapper;

public class Nomination {
    protected int id;
    protected String name;

    public Nomination() {
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
        return NominationMapper.save(this);
    }

    public boolean remove() {
        return NominationMapper.remove(this);
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
