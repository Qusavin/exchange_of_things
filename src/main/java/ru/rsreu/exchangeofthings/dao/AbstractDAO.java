package ru.rsreu.exchangeofthings.dao;

import com.prutzjow.resourcer.ProjectResourcer;
import com.prutzjow.resourcer.Resourcer;
import ru.rsreu.exchangeofthings.database.ConnectionPool;

import java.sql.Connection;

public abstract class AbstractDAO {
    protected Resourcer resourcer;
    protected Connection connection;

    public AbstractDAO() {
        this.resourcer = ProjectResourcer.getInstance();
        this.connection = ConnectionPool.getConnection();
    }
}
