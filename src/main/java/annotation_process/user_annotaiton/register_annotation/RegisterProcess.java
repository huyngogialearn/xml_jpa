package annotation_process.user_annotaiton.register_annotation;

import inter.Annotation;
import inter.CRUDAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface RegisterProcess {
    Class<? extends Annotation> process_class();
}
