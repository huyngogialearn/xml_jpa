package jpa.annotation_process.user_annotaiton.table;

import jpa.annotation_process.register_annotation.RegisterProcess;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The {@link IsTable} annotation annotates a class that is a table in database
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@RegisterProcess(process_class = IsTableProcess.class)
public @interface IsTable {
    /**
     * @return A table name in database
     */
    String name() default "";
}
