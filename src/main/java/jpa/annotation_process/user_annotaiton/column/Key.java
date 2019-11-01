package jpa.annotation_process.user_annotaiton.column;

import jpa.annotation_process.register_annotation.RegisterProcess;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The {@link Key} annotation indicates a field which is a primary key of table
 * @author Huy Ngo Gia
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@RegisterProcess(process_class = KeyProcess.class)
public @interface Key {
}
