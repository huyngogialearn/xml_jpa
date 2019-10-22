package inter;

import exception.MySQLNotSupportException;

import java.lang.reflect.Field;

public interface CRUDColumnAnnotation  extends CRUDAnnotation {
    public String create(String form) throws MySQLNotSupportException;
    public String delete(String form);
    public String update(String form);
    public void setAnnotatedField(Field field);

}
