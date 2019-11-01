package jpa.annotation_process.user_annotaiton.query;

import jpa.annotation_process.register_annotation.RegisterQuery;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * The {@link Insert} annotation makes the methods become a insert method
 * <p>@Insert public void [method_name](Object insertedObject)</p>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@RegisterQuery(process_class = InsertProcess.class)

public @interface Insert {
    int[] include() default {};
    int[] ignore() default {};
}
