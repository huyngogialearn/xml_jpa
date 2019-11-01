package jpa.annotation_process.user_annotaiton.column;import jpa.annotation_process.register_annotation.RegisterProcess;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The {@link Null} annotation indicates a field which can be null or not
 * @author Huy Ngo Gia
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@RegisterProcess(process_class = NullProcess.class)
public @interface Null {
    /**
     * @return value which indicates null or not-null column
     */
    boolean is_null() default true;
}
