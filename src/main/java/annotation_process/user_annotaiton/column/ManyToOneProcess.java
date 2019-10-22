package annotation_process.user_annotaiton.column;

import e.QueryBuilderType;
import inter.CRUDColumnAnnotation;
import logic.QueryBuilderLogic;
import model.QueryBuider;
import ultil.resource.CO;

import java.lang.reflect.Field;

import static inter.SQLContrains.FOREIGN_KEY_QUERY;

public class ManyToOneProcess implements CRUDColumnAnnotation {
    private ManyToOne manyToOne;
    private Field parentField;
    public String create(String form) {
        String formTmp;
        //The Key and Value of @KeyProcess
        String key = "OTHER_ATTRIBUTE", foreKey = "FOREIGN_KEY_NAME", refKey="REFERENCE_TO_NAME";
        String value = "PRIMARY KEY", foreValue = CO.id.get(manyToOne.name()).getSqlName();
        String refValue =CO.id.get(manyToOne.ref_fr()).getTable().getSqlName()+"("+CO.id.get(manyToOne.ref_fr()).getSqlName()+")" ;
        //Create QueryBuider from the key and the value
        QueryBuider queryBuider = new QueryBuider(QueryBuilderType.ONE, foreKey,foreValue);
        //Process form
        QueryBuilderLogic queryBuilderLogic = new QueryBuilderLogic();
        formTmp = queryBuilderLogic.processQueryBuilderForm(FOREIGN_KEY_QUERY,queryBuider);
        queryBuider = new QueryBuider(QueryBuilderType.ONE,refKey,refValue);
        formTmp = queryBuilderLogic.processQueryBuilderForm(formTmp,queryBuider);
        queryBuider = new QueryBuider(QueryBuilderType.COMMA,key,formTmp);
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
        parentField = field;
    }



    @Override
    public void setAnnotation(Object[] annotations) {
        if(annotations.length != 0){
            if (annotations[0] instanceof ManyToOne){
                manyToOne =(ManyToOne) annotations[0];
            }
        }
    }
}