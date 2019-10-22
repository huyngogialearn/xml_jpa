package inter;

public interface CRUDDatabaseAnnotation extends CRUDAnnotation {
    public String create(String form);
    public String drop(String form);
}
