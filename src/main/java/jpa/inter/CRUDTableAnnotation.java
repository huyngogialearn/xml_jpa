package jpa.inter;

import jpa.resource.model.TableResource;

/**
 * {@inheritDoc}
 * The {@link CRUDTableAnnotation} interface contain the common methods for processing CRUD of table
 */
public interface CRUDTableAnnotation extends CRUDAnnotation {

    /**
     * The method used to create a table
     * @param form a form used for creating a table
     * @return a form that is filled information
     */
    public String create(String form);

    /**
     * The method used to drop a table
     * @param form a form used for dropping a table
     * @return a form that is filled information
     */
    public String delete(String form);

    /**
     * The method sets class annotated by current @annotation
     * @param clazz class annotated by current @annotation
     */
    public void setClass(Class clazz);
    public void setUpResource(TableResource tableResource);

}
