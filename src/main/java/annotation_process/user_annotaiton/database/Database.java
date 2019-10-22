package annotation_process.user_annotaiton.database;

import annotation_process.user_annotaiton.column.Den;
import e.DatabaseStrategy;
import e.SQLType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The {@link Den} annotation indicates a field which is auto-increment.
 * @author Huy Ngo Gia
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Database {
    String url();
    String name();
    String username();
    String password();
    DatabaseStrategy strategy();
    SQLType type();
}
