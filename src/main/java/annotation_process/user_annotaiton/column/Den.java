package annotation_process.user_annotaiton.column;

import annotation_process.user_annotaiton.register_annotation.RegisterProcess;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The {@link Den} annotation indicates a field which is auto-increment.
 * @author Huy Ngo Gia
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@RegisterProcess(process_class = DenProcess.class)
public @interface Den {

}