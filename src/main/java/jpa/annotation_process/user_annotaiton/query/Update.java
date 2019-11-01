package jpa.annotation_process.user_annotaiton.query;

import jpa.annotation_process.register_annotation.RegisterQuery;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * The {@link Update} annotation makes the methods become a updated method
 * <p>@Update public void [method_name](UpdatedObject,Paramater,...)</p>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@RegisterQuery(process_class = UpdateProcess.class)
public @interface Update {
    /**
     * condition
     * @return where clause
     */
    String where();
    /**
     * Included fields/columns
     */
    int[] include() default {};
    /**
     * Ignore fields/columns
     */
    int[] ignore() default {};
}
