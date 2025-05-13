package context;

import java.sql.Connection;
import java.sql.SQLException;

public class DBContext {

    // Method to get the database connection
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        // Open and return the database connection from SQLServerConnection class
        return SQLServerConnection.openConnection();
    }

    // Method to close the connection
    public void closeConnection(Connection conn) {
        // Close the database connection
        SQLServerConnection.closeConnection(conn);
    }
}