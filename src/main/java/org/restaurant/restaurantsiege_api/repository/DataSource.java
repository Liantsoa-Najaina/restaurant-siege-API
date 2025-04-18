package org.restaurant.restaurantsiege_api.repository;

import org.restaurant.restaurantsiege_api.service.exception.ServerException;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class DataSource {
    private final static int defaultPort = 5432;
    private final String user = System.getenv("DB_USER");
    private final String password = System.getenv("DB_PASSWORD");
    private final String url;

    public DataSource() {
        String database = System.getenv("DB_NAME");
        String host = System.getenv("DB_HOST");
        url = "jdbc:postgresql://" + host + ":"+ defaultPort + "/" + database;
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new ServerException(e);
        }
    }
}
