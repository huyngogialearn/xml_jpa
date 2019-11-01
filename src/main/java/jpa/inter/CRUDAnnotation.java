package jpa.inter;

/**
 * The {@link CRUDAnnotation} interface contain the common methods for processing CRUD of database model
 */
public interface CRUDAnnotation extends Annotation {
    /**
     * The method sets data for processing
     * @param annotations data for processing (normal type is @annotation)
     */
    public void setAnnotation(Object[] annotations);
}
