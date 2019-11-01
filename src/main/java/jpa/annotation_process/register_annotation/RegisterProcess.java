package jpa.annotation_process.register_annotation;

import jpa.inter.Annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The {@link RegisterProcess} annotation indicates a @annotation that is processed by a class
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface RegisterProcess {
    /**
     * @return a class will process this @annotation
     */
    Class<? extends Annotation> process_class();
}
