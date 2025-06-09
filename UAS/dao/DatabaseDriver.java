package org.week12.util;

import java.sql.Connection;

public interface  DatabaseDriver {
    Connection getConnection();
    void closeConnection();
    void preparedSchema();
}
