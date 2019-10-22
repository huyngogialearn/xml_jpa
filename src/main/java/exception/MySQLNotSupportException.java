package exception;

public class MySQLNotSupportException extends Exception {
    public MySQLNotSupportException(String message) {
        super(message);
    }

    public MySQLNotSupportException() {
        super();
    }
}
