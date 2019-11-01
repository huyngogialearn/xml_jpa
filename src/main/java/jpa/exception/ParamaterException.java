package jpa.exception;

/**
 * The {@link ParamaterException} exception indicates the number of parameters that don't match to given number
 */
public class ParamaterException extends RuntimeException {
    public ParamaterException(String message) {
        super(message);
    }
    public ParamaterException(int requestNumber,int actualNumber) {
        super("The method requests "+requestNumber+", found "+ actualNumber);
    }
}
