package dancecomp.mappers;

import dancecomp.logics.Club;
import org.sql2o.Connection;

import java.util.List;

public class ClubMapper extends MainMapper{
    public static List<Club> getAll() {
        String sql = "SELECT `id`, `name` FROM `clubs`";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetch(Club.class);
        }
    }

    public static Club getById(int id) {
        String sql = "SELECT `id`, `name` FROM `clubs` WHERE `id` = :id";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Club.class);
        }
    }

    public static boolean save(Club obj) {
        return false;
    }

    public static boolean remove(Club obj) {
        return false;
    }
}
