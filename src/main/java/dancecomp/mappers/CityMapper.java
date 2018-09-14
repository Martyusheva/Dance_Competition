package dancecomp.mappers;

import dancecomp.logics.City;
import org.sql2o.Connection;

import java.util.List;

public class CityMapper extends MainMapper {

    public static List<City> getAll() {
        String sql = "SELECT `id`, `name` FROM `cities`";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetch(City.class);
        }
    }

    public static City getById(int id) {
        String sql = "SELECT `id`, `name` FROM `cities` WHERE `id` = :id";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(City.class);
        }
    }

    public static boolean save(City obj) {
        return false;
    }

    public static boolean remove(City obj) {
        return false;
    }
}
