package jpa.resource.model;

import jpa.e.DatabaseStrategy;
import jpa.e.SQLType;

/**
 * The {@link SQLConfigurationResource} contains the data for setting the database connection
 */
public class SQLConfigurationResource {
    /**
     * path connecting to database
     */
    String url;
    /**
     * name of database
     */
    String name;
    /**
     * username of the database
     */
    String username;
    /**
     * password of the database
     */
    String password;
    /**
     * type of the database
     * @see {@link SQLType}
     */
    SQLType type;
    /**
     * strategy of the database
     * @see {@link DatabaseStrategy}
     */
    DatabaseStrategy strategy;

    public SQLConfigurationResource(String url, String name, String username, String password, SQLType type, DatabaseStrategy strategy) {
        this.url = url;
        this.name = name;
        this.username = username;
        this.password = password;
        this.type = type;
        this.strategy = strategy;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public SQLType getType() {
        return type;
    }

    public void setType(SQLType type) {
        this.type = type;
    }

    public DatabaseStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(DatabaseStrategy strategy) {
        this.strategy = strategy;
    }
}
