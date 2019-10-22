package annotation_process.user_annotaiton.column;

import e.QueryBuilderType;
import inter.CRUDColumnAnnotation;
import logic.QueryBuilderLogic;
import model.QueryBuider;

import java.lang.reflect.Field;

public class DenProcess implements CRUDColumnAnnotation {
    private Den den;


    public String create(String form) {
        //The Key and Value of @Den
        String key = "ATTRIBUTE";
        //If SQLServer
        //If MySQL
        String value = "AUTO_INCREMENT";
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
        if(annotations.length == 1){
            if (annotations[0] instanceof Den) {
                den = (Den) annotations[0];
            }
        }
    }
}
