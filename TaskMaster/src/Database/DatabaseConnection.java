package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // Dane połączenia
    private static final String URL = "jdbc:mysql://localhost:3306/taskmaster?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
    private static final String USER = "admin";
    private static final String PASSWORD = "admin"; // zmień na swoje hasło

    private static Connection connection = null;

    private DatabaseConnection() {}

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Połączenie zamknięte");
            } catch (SQLException e) {
                System.err.println("Błąd podczas zamykania połączenia: " + e.getMessage());
            }
        }
    }
}