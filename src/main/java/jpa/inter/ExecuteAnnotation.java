package jpa.inter;

import java.lang.reflect.Method;
import java.util.List;import jpa.resource.model.CO;

/**
 * The {@link ExecuteAnnotation} contains the methods for processing query @Annotation.
 */
public interface ExecuteAnnotation<T> extends Annotation {
    /**
     * Process the annotation and return a suitable results
     * @param proxy ???
     * @param method methods
     * @param args paramaters
     * @return Void if the @annotation is update, delete and insert, List<T> if the @annotation is select
     */
    public List<T> process(Object proxy, Method method, Object[] args);

    /**
     * Let me process this method -> return true
     * @param method needed process method
     * @return false: no I don't process it | true: let me
     */
    public boolean itMe(Method method);
}
