package jpa.exception;

import jpa.resource.model.TA;

/**
 * The {@link NotFoundResourceException} jpa.exception indicates that the given id does not map any jpa.resource in {@link CO} or {@link TA}
 */
public class NotFoundResourceException extends RuntimeException{

    public NotFoundResourceException(String message) {
        super(message);
    }

    public NotFoundResourceException(Object id) {
        super("Not Found Resource Of " + id);
    }

}
