package jpa.exception;

/**
 * The {@link MySQLNotSupportException} jpa.exception indicates the field type supported by the system.
 */
public class MySQLNotSupportException extends RuntimeException {

    public MySQLNotSupportException(String message) {
        super(message);
    }

    public MySQLNotSupportException() {
        super();
    }
}
