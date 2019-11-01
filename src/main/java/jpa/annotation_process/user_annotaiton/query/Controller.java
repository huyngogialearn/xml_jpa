package jpa.annotation_process.user_annotaiton.query;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The {@link Controller} annotation indicates the class being controller
 * <p>This annotation will be scanned and read {@link FillDAO}</p>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Controller {
}
