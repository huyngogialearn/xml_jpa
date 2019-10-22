package inter;

public interface CRUDTableAnnotation extends CRUDAnnotation {
    public String create(String form);
    public String delete(String form);
    public void setClass(Class clazz);
}
