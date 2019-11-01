package jpa.annotation_process.user_annotaiton.column;

import jpa.e.QueryBuilderType;
import jpa.exception.MySQLNotSupportException;
import jpa.inter.CRUDColumnAnnotation;
import jpa.resource.logic.COLogic;
import jpa.resource.model.ColumnResource;
import jpa.ultil.logic.QueryBuilderLogic;
import jpa.ultil.logic.TypeConvert;
import jpa.ultil.model.QueryBuider;

import java.lang.reflect.Field;
import static jpa.inter.SQLContrains.*;

/**
 * The {@link IsColumnProcess} contains the methods processing the {@link IsColumn} annotation/
 * @author Huy Ngo Gia
 */
public class IsColumnProcess implements CRUDColumnAnnotation {

    /**
     * The data for processing the field.
     */
    private IsColumn isColumn;

    /**
     * The field of this annotation.
     */
    private Field parentField;

    public IsColumnProcess() {
    }

    public String create(String form) throws MySQLNotSupportException {
        //result -> id int {ATTRIBUTE< >} {OTHER_ATTRIBUTE<,>}
        QueryBuilderLogic queryBuilderLogic = new QueryBuilderLogic();
        TypeConvert convert = new TypeConvert();
        //get -> id
        String columnName = (isColumn.name() == null || isColumn.name().equals("")) ? parentField.getName() : isColumn.name();
        //get -> int
        String columnType;
        columnType= convert.convertJavaToSQLType((isColumn.sqlType().equals(void.class))?parentField.getType():isColumn.sqlType(), isColumn.length());
        //replace the values into the parent form
        return form = queryBuilderLogic.processQueryBuilderForm(form, new QueryBuider[]{
                new QueryBuider(QueryBuilderType.ONE, COLUMN_NAME_KEY, columnName),
                new QueryBuider(QueryBuilderType.ONE, COLUMN_TYPE_KEY, columnType)
        });
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
    public void setUpResource(ColumnResource columnResource) {
        TypeConvert convert = new TypeConvert();
        columnResource.setSqlName((isColumn.name() == null || isColumn.name().equals("")) ? parentField.getName() : isColumn.name());
        columnResource.setSqlType(convert.convertJavaToSQLType((isColumn.sqlType().equals(void.class))?parentField.getType():isColumn.sqlType(), isColumn.length()));
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