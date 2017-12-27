package threadLocal;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtils {
    //private static Connection connection = null;

    private static ThreadLocal<Connection> connContainer = new ThreadLocal<>();

    private static String driver = "";
    private static String url = "";
    private static String username = "";
    private static String password = "";

    public static Connection getConnection() {
        Connection connection = connContainer.get();
        try {
            if (connection == null) {
                Class.forName(driver);
                connection = DriverManager.getConnection(url, username, password);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connContainer.set(connection);
        }
        return connection;
    }

    public static void closeConnection() {
        Connection connection = connContainer.get();
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connContainer.remove();
        }
    }
}
