package dancecomp.mappers;

import dancecomp.logics.Judge;
import org.sql2o.Connection;

import java.util.List;

public class JudgeMapper extends MainMapper{
    public static List<Judge> getAll() {
        String sql = "SELECT `id`, `name` FROM `judges`";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetch(Judge.class);
        }
    }

    public static Judge getById(int id) {
        String sql = "SELECT `id`, `name` FROM `judges` WHERE `id` = :id";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Judge.class);
        }
    }

    public static boolean save(Judge obj) {
        return false;
    }

    public static boolean remove(Judge obj) {
        return false;
    }
}
