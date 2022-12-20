package ru.rsreu.exchangeofthings.database;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;

/**
 * Holder of database connection.
 */
public class ConnectionPool {
    private static final String DATASOURCE_NAME = "java:/comp/env/jdbc/exchangeofthings";
    private static volatile Connection connection;

    private ConnectionPool() {
    }

    /**
     * Get connection.
     *
     * @return Connection
     */
    public static Connection getConnection() {
        synchronized (ConnectionPool.class) {
            if (connection == null) {
                try {
                    Context initContext = new InitialContext();
                    DataSource dataSource = (DataSource) initContext.lookup(DATASOURCE_NAME);
                    connection = dataSource.getConnection();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return connection;
    }
}
