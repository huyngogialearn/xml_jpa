package annotation_process.user_annotaiton.column;

import inter.CRUDColumnAnnotation;

import java.lang.reflect.Field;

public class OneToManyProcess implements CRUDColumnAnnotation {

    public String create(String form) {
        return form;
    }

    public String delete(String form) {
        return null;
    }

    public String update(String form) {
        return null;
    }

    @Override
    public void setAnnotatedField(Field field) {

    }



    @Override
    public void setAnnotation(Object[] annotations) {

    }

}