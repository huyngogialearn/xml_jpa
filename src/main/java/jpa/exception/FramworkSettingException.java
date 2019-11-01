package jpa.exception;

import jpa.resource.model.TA;

/**
 * The {@link FramworkSettingException} jpa.exception indicates that the given id does not map any jpa.resource in {@link CO} or {@link TA}
 */
public class FramworkSettingException extends RuntimeException{

    public FramworkSettingException(String message) {
        super(message);
    }


}
