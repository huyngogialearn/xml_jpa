package jpa.annotation_process.user_annotaiton.query;

import jpa.annotation_process.register_annotation.RegisterQuery;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The {@link SelectQuery} annotation makes the methods become a select method
 * <p>@SelectQuery public void [method_name](Paramater,...)</p>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@RegisterQuery(process_class = SelectQueryProcess.class)
public @interface  SelectQuery {
    /**
     * condition
     * @return where clause
     */
    String where() default "";

    /**
     * including the foreign key object
     * @return foreign key
     */
    int[] fill_relationships() default {};

    /**
     * @return number of selected data
     */
    int limit() default 0;

    /**
     * @return decreate
     */
    int[] des() default {};

    /**
     * @return increate
     */
    int[] inc() default {};

    /**
     * Included fields/columns
     */
    int[] include() default {};
    /**
     * Ignore fields/columns
     */
    int[] ignore() default {};
}
