package jpa.inter;

import jpa.exception.MySQLNotSupportException;
import jpa.resource.model.ColumnResource;

import java.lang.reflect.Field;

/**
 * {@inheritDoc}
 * The {@link CRUDColumnAnnotation} interface contain the common methods for processing CRUD of column
 */
public interface CRUDColumnAnnotation  extends CRUDAnnotation {

    /**
     * The method used to create a column in table
     * @param form a form used for creating a column in table
     * @return a form that is filled information
     */
    public String create(String form) throws MySQLNotSupportException;

    /**
     * The method sets field annotated by current @annotation
     * @param field field annotated by current @annotation
     */
    public void setAnnotatedField(Field field);
    public void setUpResource(ColumnResource columnResource);

}
