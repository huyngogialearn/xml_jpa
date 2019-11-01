package jpa.annotation_process.user_annotaiton.query;

import jpa.annotation_process.register_annotation.RegisterQuery;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * The {@link Delete} annotation makes the methods become a delete method
 * <p>@Delete public void [method_name](agruments,....)</p>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@RegisterQuery(process_class = DeleteProcess.class)
public @interface Delete {
    /**
     * condition
     * @return condition for deleting
     */
    String where();
}
