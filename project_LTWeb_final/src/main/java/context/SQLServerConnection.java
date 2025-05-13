package context;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLServerConnection {

    private static Connection conn = null;

    // Open the connection
    public static Connection openConnection() throws SQLException, ClassNotFoundException {
//        String dbDriver = "com.mysql.cj.jdbc.Driver";
//        String dbURL = "jdbc:mysql://b95xv6elmras4lfaadtj-mysql.services.clever-cloud.com:3306";
//        String dbName = "b95xv6elmras4lfaadtj";
//        String dbUsername = "uf697ta5dmba707j";
//        String dbPassword = "7823FkdLIO2OMRjYvQWk";
//        String connectionURL = dbURL + "/" + dbName + "?useSSL=false&serverTimezone=UTC";
        String dbDriver = "com.mysql.cj.jdbc.Driver";
        String dbURL = "jdbc:mysql://localhost:3306";
        String dbName = "quanlybangiay";
        String dbUsername = "root";
        String dbPassword = "123456";
        String connectionURL = dbURL + "/" + dbName + "?useSSL=false&serverTimezone=UTC";

        try {
            // Load the driver
            Class.forName(dbDriver);

            // Establish the connection
            conn = DriverManager.getConnection(connectionURL, dbUsername, dbPassword);
            System.out.println("Connected successfully!");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found!");
            e.printStackTrace();
            throw e;
        } catch (SQLException e) {
            System.out.println("Database connection failed!");
            e.printStackTrace();
            throw e;
        }
        return conn;
    }

    // Close the connection
    public static void closeConnection(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Connection closed successfully.");
            }
        } catch (SQLException e) {
            System.out.println("Error closing the connection!");
            e.printStackTrace();
        }
    }
}
