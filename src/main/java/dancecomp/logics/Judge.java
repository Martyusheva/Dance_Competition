package dancecomp.logics;

import dancecomp.mappers.JudgeMapper;

public class Judge {
    protected int id;
    protected String name;

    public Judge() {
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
        return JudgeMapper.save(this);
    }

    public boolean remove() {
        return JudgeMapper.remove(this);
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
