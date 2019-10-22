package annotation_process.user_annotaiton.table;

import annotation_process.user_annotaiton.register_annotation.RegisterProcess;

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
     * By default, the class name is table name. For identifying other name, using this attribute
     * @return table name in database that the class map to
     */
    String name() default "";
}
