package essentials;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQL
{
    private static String database="masrafTakipDB";
    private static String username = "root";
    private static String password = "";

    public static Connection getConnection()
    {
        Connection connection = null;
        String url = "jdbc:mysql://localhost:3306/"+database;

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        }
        catch (Exception ex)
        {
            connection = null;
        }

        return connection;
    }
}
