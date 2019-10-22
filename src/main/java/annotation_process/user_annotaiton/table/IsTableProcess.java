package annotation_process.user_annotaiton.table;

import e.QueryBuilderType;
import inter.CRUDTableAnnotation;
import logic.QueryBuilderLogic;
import model.QueryBuider;

public class IsTableProcess implements CRUDTableAnnotation {

    IsTable isTableClass;
    Class parentClazz;

    public IsTableProcess() {
    }

    public String create(String form) {
        //The Key and Value of @Database
        String key = "TABLE_NAME";
        String value = (isTableClass.name() == null || isTableClass.name().equals(""))?parentClazz.getSimpleName():isTableClass.name();
        //Create QueryBuider from the key and the value
        QueryBuider queryBuider = new QueryBuider(QueryBuilderType.ONE,key,value);
        //Process form
        QueryBuilderLogic queryBuilderLogic = new QueryBuilderLogic();
        return queryBuilderLogic.processQueryBuilderForm(form,queryBuider);
    }

    public String delete(String form) {
        //The Key and Value of @Database
        String key = "TABLE_NAME";
        String value = (isTableClass.name() == null || isTableClass.name().equals(""))?parentClazz.getSimpleName():isTableClass.name();
        //Create QueryBuider from the key and the value
        QueryBuider queryBuider = new QueryBuider(QueryBuilderType.ONE,key,value);
        //Process form
        QueryBuilderLogic queryBuilderLogic = new QueryBuilderLogic();
        return queryBuilderLogic.processQueryBuilderForm(form,queryBuider);

    }

    @Override
    public void setClass(Class clazz) {
        parentClazz = clazz;
    }



    @Override
    public void setAnnotation(Object[] annotations) {
        if(annotations.length != 0) {
            if (annotations[0] instanceof IsTable) {
                isTableClass = (IsTable) annotations[0];

            }
        }
    }
}
