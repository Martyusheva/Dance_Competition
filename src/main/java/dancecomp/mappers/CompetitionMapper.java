package dancecomp.mappers;

import dancecomp.logics.Competition;
import org.sql2o.Connection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompetitionMapper extends MainMapper {

    public CompetitionMapper() {
        Map<String, String> colMaps = new HashMap<>();
        colMaps.put("city_id", "cityId");
        colMaps.put("club_id", "clubId");
        colMaps.put("organizer_id", "organizerId");
        colMaps.put("counting_board_id", "countingBoardId");
        colMaps.put("judge_main_id", "judgeMainId");
        colMaps.put("judge2_id", "judge2Id");
        colMaps.put("judge3_id", "judge3Id");
        colMaps.put("judge4_id", "judge4Id");
        colMaps.put("judge5_id", "judge5Id");

        sql2o.setDefaultColumnMappings(colMaps);
    }

    public static List<Competition> getAll() {
        String sql = "SELECT `id`, `city_id` AS 'cityId', `name`, `club_id` AS 'clubId', `cost`, `status`, `organizer_id` AS 'organizerId', `counting_board_id` AS 'countingBoardId', `judge_main_id` AS 'judgeMainId', `judge2_id` AS 'judge2Id', `judge3_id` AS 'judge3Id', `judge4_id` AS 'judge4Id', `judge5_id` AS 'judge5Id', `debut`, `e`, `d`, `c`, `b`, `a`, `abc`, `comments`, `date` FROM `competitions`";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetch(Competition.class);
        }
    }

    public static Competition getById(int id) {
        String sql = "SELECT `id`, `city_id` AS 'cityId', `name`, `club_id` AS 'clubId', `cost`, `status`, `organizer_id` AS 'organizerId', `counting_board_id` AS 'countingBoardId', `judge_main_id` AS 'judgeMainId', `judge2_id` AS 'judge2Id', `judge3_id` AS 'judge3Id', `judge4_id` AS 'judge4Id', `judge5_id` AS 'judge5Id', `debut`, `e`, `d`, `c`, `b`, `a`, `abc`, `comments`, `date` FROM `competitions` WHERE `id` = :id";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Competition.class);
        }
    }

    public static boolean save(Competition obj) {
        try (Connection con = sql2o.open()) {
            if (obj.getId() == 0) {
                String sql = "INSERT INTO `competitions` (`city_id`, `name`, `club_id`, `cost`, `status`, `organizer_id`, `counting_board_id`, `judge_main_id`, `judge2_id`, `judge3_id`, `judge4_id`, `judge5_id`, `debut`, `e`, `d`, `c`, `b`, `a`, `abc`, `comments`, `date`) "
                        + "VALUES (:city_id, :name, :club_id, :cost, :status, :organizer_id, :counting_board_id, :judge_main_id, :judge2_id, :judge3_id, :judge4_id, :judge5_id, :debut, :e, :d, :c, :b, :a, :abc, :comments, :date)";
                if (con.createQuery(sql)
                        .addParameter("city_id", obj.getCityId())
                        .addParameter("name", obj.getName())
                        .addParameter("club_id", obj.getClubId())
                        .addParameter("cost", obj.getCost())
                        .addParameter("status", obj.getStatus())
                        .addParameter("organizer_id", obj.getOrganizerId())
                        .addParameter("counting_board_id", obj.getCountingBoardId())
                        .addParameter("judge_main_id", obj.getJudgeMainId())
                        .addParameter("judge2_id", obj.getJudge2Id())
                        .addParameter("judge3_id", obj.getJudge3Id())
                        .addParameter("judge4_id", obj.getJudge4Id())
                        .addParameter("judge5_id", obj.getJudge5Id())
                        .addParameter("debut", obj.getDebut())
                        .addParameter("e", obj.getE())
                        .addParameter("d", obj.getD())
                        .addParameter("c", obj.getC())
                        .addParameter("b", obj.getB())
                        .addParameter("a", obj.getA())
                        .addParameter("abc", obj.getAbc())
                        .addParameter("comments", obj.getComments())
                        .addParameter("date", obj.getDate())
                        .executeUpdate().getResult() > 0) {
                    return true;
                }
            } else {
                String sql = "UPDATE `competitions` SET `city_id` = :city_id, `name` = :name, `club_id` = :club_id, `cost` = :cost, `status` = :status, `organizer_id` = :organizer_id, `counting_board_id` = :counting_board_id, `judge_main_id` = :judge_main_id, `judge2_id` = :judge2_id, `judge3_id` = :judge3_id, `judge4_id` = :judge4_id, `judge5_id` = :judge5_id, `debut` = :debut, `e` = :e, `d` = :d, `c` = :c, `b` = :b, `a` = :a, `abc` = :abc, `comments` = :comments, `date` = :date WHERE `id` = :id";
                if (con.createQuery(sql)
                        .addParameter("city_id", obj.getCityId())
                        .addParameter("name", obj.getName())
                        .addParameter("club_id", obj.getClubId())
                        .addParameter("cost", obj.getCost())
                        .addParameter("status", obj.getStatus())
                        .addParameter("organizer_id", obj.getOrganizerId())
                        .addParameter("counting_board_id", obj.getCountingBoardId())
                        .addParameter("judge_main_id", obj.getJudgeMainId())
                        .addParameter("judge2_id", obj.getJudge2Id())
                        .addParameter("judge3_id", obj.getJudge3Id())
                        .addParameter("judge4_id", obj.getJudge4Id())
                        .addParameter("judge5_id", obj.getJudge5Id())
                        .addParameter("debut", obj.getDebut())
                        .addParameter("e", obj.getE())
                        .addParameter("d", obj.getD())
                        .addParameter("c", obj.getC())
                        .addParameter("b", obj.getB())
                        .addParameter("a", obj.getA())
                        .addParameter("abc", obj.getAbc())
                        .addParameter("comments", obj.getComments())
                        .addParameter("date", obj.getDate())
                        .addParameter("id", obj.getId())
                        .executeUpdate().getResult() > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean remove(Competition obj) {
        try (Connection con = sql2o.open()){
            if (obj.getId() > 0) {
                String sql = "DELETE FROM `competitions` WHERE `id` = :id";
                if (con.createQuery(sql)
                        .addParameter("id", obj.getId())
                        .executeUpdate().getResult() > 0) {
                    return true;
                }
            }
        }
        return false;
    }
}
