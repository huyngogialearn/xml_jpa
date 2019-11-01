package jpa.annotation_process.user_annotaiton.column;

import jpa.e.QueryBuilderType;
import jpa.inter.CRUDColumnAnnotation;
import jpa.resource.model.ColumnResource;
import jpa.ultil.logic.QueryBuilderLogic;
import jpa.ultil.model.QueryBuider;

import java.lang.reflect.Field;

import static jpa.inter.SQLContrains.ATTRIBUTE_KEY;

/**
 * The {@link NullProcess} contains the methods processing the {@link Null} annotation/
 * @author Huy Ngo Gia
 */
public class NullProcess implements CRUDColumnAnnotation {

    /**
     * The data for processing the field.
     */
    private Null aNull ;

    /**
     * The field of this annotation.
     */
    private Field parentField;

    /**
     * Default value.
     */
    private static final String notNullValue = "NOT NULL";

    public NullProcess() {
    }

    public String create(String form) {
        //parent form can be: [COLUMN_NAME] [COLUMN_TYPE] {ATTRIBUTE< >} {OTHER_ATTRIBUTE<,>}
        //default's that a column value in table can be null -> not process
        if(aNull.is_null()) return form;
        QueryBuilderLogic queryBuilderLogic = new QueryBuilderLogic();
        //result -> [COLUMN_NAME] [COLUMN_TYPE] NOT NULL {ATTRIBUTE< >} {OTHER_ATTRIBUTE<,>}
        //replace the value into the parent form
        return queryBuilderLogic.processQueryBuilderForm(form,new QueryBuider(QueryBuilderType.COMMA,ATTRIBUTE_KEY,notNullValue));
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
    public void setUpResource(ColumnResource columnResource) {

    }

    @Override
    public void setAnnotation(Object[] annotations) {
        if(annotations.length != 0) {
            if (annotations[0] instanceof Null) {
                aNull = (Null) annotations[0];
            }
        }
    }
}