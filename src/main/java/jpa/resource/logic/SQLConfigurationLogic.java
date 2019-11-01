package jpa.resource.logic;

import jpa.Hello;
import jpa.e.DatabaseStrategy;
import jpa.e.SQLType;
import jpa.resource.model.SQLConfigurationResource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The {@link SQLConfigurationLogic} contains the methods processing the {@link SQLConfigurationResource} class.
 */
public class SQLConfigurationLogic {
    /**
     * true -> if transaction is running
     */
    private static boolean inTransaction = false;
    /**
     * connection to database
     */
    public static Connection connection = null;

    /**
     * Starting the transaction
     * @return connection to the current database
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static Connection startTransaction() throws SQLException, ClassNotFoundException {
        inTransaction = true;
        return getConnection(Hello.database);
    }

    /**
     * Closing the transaction
     */
    public static void closeTransaction(){inTransaction = false;closeConnection();};

    /**
     * Closing the connection
     */
    public static void closeConnection(){
        try {
            if(connection != null && !connection.isClosed()){
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Create new connection
     * @param resource the information to create the connection
     * @return new connection if transaction = false <> current transaction if transaction = true
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static Connection getConnection(SQLConfigurationResource resource) throws ClassNotFoundException, SQLException {
            if(inTransaction){
                if(connection!= null && !connection.isClosed()) return connection;
            }
            Class.forName("com.mysql.jdbc.Driver");
            String connectionURL = resource.getUrl() + resource.getName()+"?useSSL=false";
            connection = DriverManager.getConnection(connectionURL, resource.getUsername(),
                    resource.getPassword());
            return connection;
        }
}
