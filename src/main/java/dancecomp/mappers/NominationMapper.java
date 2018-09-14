package dancecomp.mappers;

import dancecomp.logics.Nomination;
import org.sql2o.Connection;

import java.util.List;

public class NominationMapper extends MainMapper{
    public static List<Nomination> getAll() {
        String sql = "SELECT `id`, `name` FROM `nominations`";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetch(Nomination.class);
        }
    }

    public static Nomination getById(int id) {
        String sql = "SELECT `id`, `name` FROM `nominations` WHERE `id` = :id";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Nomination.class);
        }
    }

    public static boolean save(Nomination obj) {
        return false;
    }

    public static boolean remove(Nomination obj) {
        return false;
    }
}
