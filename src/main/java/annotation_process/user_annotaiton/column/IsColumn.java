package annotation_process.user_annotaiton.column;


import annotation_process.user_annotaiton.register_annotation.RegisterProcess;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The {@link IsColumn} annotation indicates a field which is a column of table
 * @author Huy Ngo Gia
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@RegisterProcess(process_class = IsColumnProcess.class)
public @interface IsColumn {
    /**
     * By default, the column name is table name. For identifying other name, using this attribute
     * @return name of column
     */
    String  name()      default "";

    /**
     * Indicate length of string of column in database
     * @return length of string of column
     */
    int     length()    default 255;
}
