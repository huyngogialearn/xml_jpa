package jpa.annotation_process.user_annotaiton.column;

import jpa.e.QueryBuilderType;
import jpa.inter.CRUDColumnAnnotation;
import jpa.resource.model.ColumnResource;
import jpa.ultil.logic.QueryBuilderLogic;
import jpa.ultil.model.QueryBuider;

import java.lang.reflect.Field;
import static jpa.inter.SQLContrains.*;

/**
 * The {@link KeyProcess} contains the methods processing the {@link Key} annotation/
 * @author Huy Ngo Gia
 */
public class KeyProcess implements CRUDColumnAnnotation {

    /**
     * The data for processing the field.
     */
    private Key key;

    /**
     * The field of this annotation.
     */
    private Field parentField;

    /**
     * Default value.
     */
    private static final String primaryKeyValue = "PRIMARY KEY";
    public KeyProcess() {
    }

    public String create(String form) {
        //parent form can be: [COLUMN_NAME] [COLUMN_TYPE] {ATTRIBUTE< >} {OTHER_ATTRIBUTE<,>}
        QueryBuilderLogic queryBuilderLogic = new QueryBuilderLogic();
        //result -> [COLUMN_NAME] [COLUMN_TYPE] PRIMARY KEY {ATTRIBUTE< >} {OTHER_ATTRIBUTE<,>}
        //replace the value into the parent form
        return queryBuilderLogic.processQueryBuilderForm(form,new QueryBuider(QueryBuilderType.COMMA,ATTRIBUTE_KEY,primaryKeyValue));
    }

    @Override
    public void setAnnotatedField(Field field) {
        this.parentField = field;
    }

    @Override
    public void setUpResource(ColumnResource columnResource) {

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