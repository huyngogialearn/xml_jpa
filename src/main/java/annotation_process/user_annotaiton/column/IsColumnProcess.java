package annotation_process.user_annotaiton.column;

import e.QueryBuilderType;
import exception.MySQLNotSupportException;
import inter.CRUDColumnAnnotation;
import logic.QueryBuilderLogic;
import logic.TypeConvert;
import model.QueryBuider;

import java.lang.reflect.Field;

public class IsColumnProcess implements CRUDColumnAnnotation {
    private IsColumn isColumn;
    private Field parentFieled;

    public IsColumnProcess() {
    }

    public String create(String form) throws MySQLNotSupportException {
        QueryBuider queryBuider ;
        QueryBuilderLogic queryBuilderLogic = new QueryBuilderLogic();
        String tmpForm;

        //Name of column
        String nameKey = "COLUMN_NAME";
        //if->default_name|identified_name
        String nameValue = (isColumn.name() == null || isColumn.name().equals(""))?parentFieled.getName():isColumn.name();
        //Process name;
        queryBuider = new QueryBuider(QueryBuilderType.ONE,nameKey,nameValue);
        tmpForm = queryBuilderLogic.processQueryBuilderForm(form,queryBuider);

        //Type of column
        TypeConvert convert = new TypeConvert();
        String typeKey = "COLUMN_TYPE";
        String typeValue = convert.convertJavaToSQLType(parentFieled.getType(),isColumn.length());
        //Process type
        queryBuider = new QueryBuider(QueryBuilderType.ONE,typeKey,typeValue);

        return queryBuilderLogic.processQueryBuilderForm(tmpForm,queryBuider);


    }

    public String delete(String form) {
        return null;
    }

    public String update(String form) {
        return null;
    }

    @Override
    public void setAnnotatedField(Field field) {
        parentFieled = field;
    }



    @Override
    public void setAnnotation(Object[] annotations) {
        if(annotations.length != 0){
            if (annotations[0] instanceof IsColumn){
                isColumn = (IsColumn) annotations[0];
            }
        }
    }
}