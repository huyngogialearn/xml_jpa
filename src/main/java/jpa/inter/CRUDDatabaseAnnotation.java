package jpa.inter;

/**
 * {@inheritDoc}
 * The {@link CRUDDatabaseAnnotation} interface contain the common methods for processing CRUD of database
 */
public interface CRUDDatabaseAnnotation extends CRUDAnnotation {

    /**
     * The method used to create a database
     * @param form a form used for creating a database
     * @return a form that is filled information
     */
    public String create(String form);

    /**
     * The method used to drop a database
     * @param form a form used for dropping a database
     * @return a form that is filled information
     */
    public String drop(String form);
}
