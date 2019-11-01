package jpa.annotation_process.register_annotation;

import jpa.inter.ExecuteAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The {@link RegisterQuery} annotation indicates a @annotation that is processed by a class
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface RegisterQuery {
    /**
     * @return a class will process this @annotation
     */
    Class<? extends ExecuteAnnotation> process_class();
}
