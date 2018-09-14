package dancecomp.logics;


import dancecomp.mappers.CityMapper;

public class City {
    protected int id;
    protected String name;

    public City() {
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
        return CityMapper.save(this);
    }

    public boolean remove() {
        return CityMapper.remove(this);
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
