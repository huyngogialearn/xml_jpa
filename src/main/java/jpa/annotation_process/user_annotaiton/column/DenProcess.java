package jpa.annotation_process.user_annotaiton.column;

import jpa.e.QueryBuilderType;
import jpa.inter.CRUDColumnAnnotation;
import jpa.resource.model.ColumnResource;
import jpa.ultil.logic.QueryBuilderLogic;
import jpa.ultil.model.QueryBuider;

import java.lang.reflect.Field;

import static jpa.inter.SQLContrains.ATTRIBUTE_KEY;

/**
 * The {@link DenProcess} contains the methods processing the {@link Den} annotation/
 * @author Huy Ngo Gia
 */
public class DenProcess implements CRUDColumnAnnotation {
    /**
     * The data for processing the field.
     */
    private Den den;

    /**
     * Default value used in My SQL.
     */
    private static final String MySQLCreateValue = "AUTO_INCREMENT";

    /**
     * The field of this annotation.
     */
    private Field field;

    public String create(String form) {
        //parent form can be: [COLUMN_NAME] [COLUMN_TYPE] {ATTRIBUTE< >} {OTHER_ATTRIBUTE<,>}
        QueryBuilderLogic queryBuilderLogic = new QueryBuilderLogic();
        //result -> [COLUMN_NAME] [COLUMN_TYPE] AUTO_INCREMENT {ATTRIBUTE< >} {OTHER_ATTRIBUTE<,>}
        //replace the value into the parent form
        return queryBuilderLogic.processQueryBuilderForm(form,new QueryBuider(QueryBuilderType.COMMA,ATTRIBUTE_KEY,MySQLCreateValue));
    }

    @Override
    public void setAnnotatedField(Field field) {
        this.field = field;
    }

    @Override
    public void setUpResource(ColumnResource columnResource) {

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
