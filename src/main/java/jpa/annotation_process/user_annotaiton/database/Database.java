package jpa.annotation_process.user_annotaiton.database;

import jpa.e.DatabaseStrategy;
import jpa.e.SQLType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The {@link Database} annotation providing the needed data for setting up the database connection.
 * @author Huy Ngo Gia
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Database {

    /**
     * @return a url to the database
     */
    String url();

    /**
     * @return a name of the database
     */
    String name();

    /**
     * @return a username of the account to login the database
     */
    String username();

    /**
     * @return a password of the account to login the database
     */
    String password();

    /**
     * @return a value indicating what strategy should be executed
     * @see {@link DatabaseStrategy}
     */
    DatabaseStrategy strategy();

    /**
     * @return a value indicating what Database used
     * @see {@link SQLType}
     */
    SQLType type();
}
