package jpa.annotation_process.user_annotaiton.column;import jpa.annotation_process.register_annotation.RegisterProcess;

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
     * @return name of column
     */
    String  name()      default "";

    /**
     * @return length of varchar of column (if the type of column is varchar)
     */
    int     length()    default 255;
}
