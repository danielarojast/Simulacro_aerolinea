package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public interface CRUD {
    public Object insert(Object obj);
    public List<Object> findAll();

    public boolean update(Object obj);

    public boolean delete(Object obj);
}
