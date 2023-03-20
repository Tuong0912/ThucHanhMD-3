import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOConnection {
    private static String jdbcURL = "jdbc:mysql://localhost:3306/employee";
    private static String acc = "root";
    private static String pass = "tuong2001";
    private static String className = "com.mysql.cj.jdbc.Driver";

    public static Connection getConnection() {
        Connection connection;

        try {
            Class.forName(className);
            connection = DriverManager.getConnection(jdbcURL, acc, pass);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public static void main(String[] args) {
        Connection connection = DAOConnection.getConnection();
    }
}
