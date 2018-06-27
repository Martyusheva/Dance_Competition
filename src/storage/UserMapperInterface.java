package storage;

import java.sql.SQLException;

public interface UserMapperInterface<T> extends Mapper<T> {
    T findByLogin(String login) throws SQLException;
}
