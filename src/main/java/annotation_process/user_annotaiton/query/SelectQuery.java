package annotation_process.user_annotaiton.query;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface  SelectQuery {
    String where();
    int[] fill_relationships();
    int limit();
    int[] distincts();
    int[] des();
    int[] inc();
}
