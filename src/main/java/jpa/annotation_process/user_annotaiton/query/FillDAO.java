package jpa.annotation_process.user_annotaiton.query;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The {@link FillDAO} indicates the <b>public static variable</b> is a instance of DAO interface
 * <p>The interface variable will be filled in runtime</p>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FillDAO {
}
