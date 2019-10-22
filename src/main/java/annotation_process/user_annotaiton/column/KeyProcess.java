package annotation_process.user_annotaiton.column;

import e.QueryBuilderType;
import inter.CRUDColumnAnnotation;
import logic.QueryBuilderLogic;
import model.QueryBuider;

import java.lang.reflect.Field;

public class KeyProcess implements CRUDColumnAnnotation {
    Key key;

    public KeyProcess() {
    }

    public String create(String form) {
        //The Key and Value of @KeyProcess
        String key = "ATTRIBUTE";
        String value = "PRIMARY KEY";
        //Create QueryBuider from the key and the value
        QueryBuider queryBuider = new QueryBuider(QueryBuilderType.COMMA,key,value);
        //Process form
        QueryBuilderLogic queryBuilderLogic = new QueryBuilderLogic();
        return queryBuilderLogic.processQueryBuilderForm(form,queryBuider);
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
        if(annotations.length != 0){
            if (annotations[0] instanceof Key){
                key =(Key) annotations[0];
            }
        }
    }

}