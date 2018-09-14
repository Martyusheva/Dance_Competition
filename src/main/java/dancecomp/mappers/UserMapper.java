package dancecomp.mappers;

import dancecomp.logics.CountingBoard;
import dancecomp.logics.Organizer;
import dancecomp.logics.ASH;
import dancecomp.logics.User;
import org.sql2o.Connection;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserMapper extends MainMapper {
    public static List<User> getAll() {
        String sql = "SELECT `id`, `name`, `email`, `password`, `type`, `phone` FROM `users` WHERE `type`= :type";
        List<User> list = new ArrayList<User>();
        try (Connection con = sql2o.open()) {
            List<Organizer> organizers;
            organizers = con.createQuery(sql)
                    .addParameter("type", User.TYPE_ORGANIZER)
                    .executeAndFetch(Organizer.class);
            list.addAll(organizers);
            List<ASH> ashes;
            ashes = con.createQuery(sql)
                    .addParameter("type", User.TYPE_ASH)
                    .executeAndFetch(ASH.class);
            list.addAll(ashes);
            List<CountingBoard> countingBoards;
            countingBoards = con.createQuery(sql)
                    .addParameter("type", User.TYPE_COUNTING_BOARD)
                    .executeAndFetch(CountingBoard.class);
            list.addAll(countingBoards);
        }
        return list;
    }

    public static User getById(int id) {
        String sql = "SELECT `id`, `name`, `email`, `password`, `type`, `phone` FROM `users` WHERE `id`= :id AND `type` = :type";

        try (Connection con = sql2o.open()) {
            Optional<User> organizers;
            organizers = Optional.ofNullable(con.createQuery(sql)
                    .addParameter("id", id)
                    .addParameter("type", User.TYPE_ORGANIZER)
                    .executeAndFetchFirst(Organizer.class));
            Optional<User> ashes;
            ashes = Optional.ofNullable(con.createQuery(sql)
                    .addParameter("id", id)
                    .addParameter("type", User.TYPE_ASH)
                    .executeAndFetchFirst(ASH.class));
            CountingBoard countingBoards;
            countingBoards = con.createQuery(sql)
                    .addParameter("id", id)
                    .addParameter("type", User.TYPE_COUNTING_BOARD)
                    .executeAndFetchFirst(CountingBoard.class);
            return organizers.orElse(ashes.orElse(countingBoards));
        }
    }

    public static boolean save(User obj) {
        try (Connection con = sql2o.open()){
            if (obj instanceof Organizer) {
                if (obj.getId() == 0) {
                    String sql = "INSERT INTO `users` (`type`, `name`, `email`, `password`, `phone`) "
                            + "VALUES (:type, :name, :email, :password, :phone)";
                    if (con.createQuery(sql)
                            .addParameter("type", User.TYPE_ORGANIZER)
                            .addParameter("name", obj.getName())
                            .addParameter("email", obj.getEmail())
                            .addParameter("password", obj.getPassword())
                            .addParameter("phone", obj.getPhone())
                            .executeUpdate().getResult() > 0) {
                        return true;
                    }
                } else {
                    String sql = "UPDATE `users` SET `name` = :name, `email` = :email, `password` = :password, `phone` = :phone WHERE `id` = :id";
                    if (con.createQuery(sql)
                            .addParameter("id", obj.getId())
                            .addParameter("name", obj.getName())
                            .addParameter("email", obj.getEmail())
                            .addParameter("password", obj.getPassword())
                            .addParameter("phone", obj.getPhone())
                            .executeUpdate().getResult() > 0) {
                        return true;
                    }
                }
            } else if (obj instanceof ASH) {
                if (obj.getId() == 0) {
                    String sql = "INSERT INTO `users` (`type`, `name`, `email`, `password`, `phone`) "
                            + "VALUES (:type, :name, :email, :password, :phone)";
                    if (con.createQuery(sql)
                            .addParameter("type", User.TYPE_ASH)
                            .addParameter("name", obj.getName())
                            .addParameter("email", obj.getEmail())
                            .addParameter("password", obj.getPassword())
                            .addParameter("phone", obj.getPhone())
                            .executeUpdate().getResult() > 0) {
                        return true;
                    }
                } else {
                    String sql = "UPDATE `users` SET `name` = :name, `email` = :email, `password` = :password, `phone` = :phone WHERE `id` = :id";
                    if (con.createQuery(sql)
                            .addParameter("id", obj.getId())
                            .addParameter("name", obj.getName())
                            .addParameter("email", obj.getEmail())
                            .addParameter("password", obj.getPassword())
                            .addParameter("phone", obj.getPhone())
                            .executeUpdate().getResult() > 0) {
                        return true;
                    }
                }
            } else if (obj instanceof CountingBoard) {
                if (obj.getId() == 0) {
                    String sql = "INSERT INTO `users` (`type`, `name`, `email`, `password`, `phone`) "
                            + "VALUES (:type, :name, :email, :password, :phone)";
                    if (con.createQuery(sql)
                            .addParameter("type", User.TYPE_COUNTING_BOARD)
                            .addParameter("name", obj.getName())
                            .addParameter("email", obj.getEmail())
                            .addParameter("password", obj.getPassword())
                            .addParameter("phone", obj.getPhone())
                            .executeUpdate().getResult() > 0) {
                        return true;
                    }
                } else {
                    String sql = "UPDATE `users` SET `name` = :name, `email` = :email, `password` = :password, `phone` = :phone WHERE `id` = :id";
                    if (con.createQuery(sql)
                            .addParameter("id", obj.getId())
                            .addParameter("name", obj.getName())
                            .addParameter("email", obj.getEmail())
                            .addParameter("password", obj.getPassword())
                            .addParameter("phone", obj.getPhone())
                            .executeUpdate().getResult() > 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean update(User obj) {
        return false;
    }

    public static boolean remove(User obj) {
        try (Connection con = sql2o.open()){
            if (obj.getId() > 0) {
                String sql = "DELETE FROM `users` WHERE `id` = :id";
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
