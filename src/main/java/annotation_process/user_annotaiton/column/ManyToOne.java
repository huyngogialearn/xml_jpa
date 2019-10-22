package annotation_process.user_annotaiton.column;

import annotation_process.user_annotaiton.register_annotation.RegisterProcess;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The {@link ManyToOne} annotation indicates a field which have relation to other field in database
 * @author Huy Ngo Gia
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@RegisterProcess(process_class = ManyToOneProcess.class)
public @interface ManyToOne  {
        int ref_fr();
        int name();
}
