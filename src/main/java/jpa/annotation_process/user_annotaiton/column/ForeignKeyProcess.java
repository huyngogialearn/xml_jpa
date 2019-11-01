package jpa.annotation_process.user_annotaiton.column;

import jpa.e.QueryBuilderType;
import jpa.inter.CRUDColumnAnnotation;
import jpa.query_data.model.Relationship;
import jpa.resource.logic.COLogic;
import jpa.resource.model.ColumnResource;
import jpa.ultil.logic.QueryBuilderLogic;
import jpa.ultil.model.QueryBuider;

import java.lang.reflect.Field;

import static jpa.inter.SQLContrains.*;

/**
 * The {@link ForeignKeyProcess} contains the methods processing the {@link ForeignKey} annotation.
 * @author Huy Ngo Gia
 */
public class ForeignKeyProcess implements CRUDColumnAnnotation {

    /**
     * The data for processing the field.
     */
    private ForeignKey foreignKey;

    /**
     * The field of this annotation.
     */
    private Field field;


    public String create(String form) {
        //result -> ,FOREIGN KEY (room_id) REFERENCES class_room(id),{OTHER_ATTRIBUTE<,>}
        //form for create a foreign key
        String foreignKeyQueryForm = FOREIGN_KEY_SUB_QUERY;
        //create -> room_id
        //get the formatted foreign key name
        String foreignKeyColName = COLogic.getFormatedColumnResource("[COL]",foreignKey.name());
        //create -> class_room(id)
        //get the formatted column name that referent to foreign key
        String referenceColName = COLogic.getFormatedColumnResource("[TA]([COL])",foreignKey.ref_fr());
        QueryBuilderLogic queryBuilderLogic = new QueryBuilderLogic();
        //replace -> room_id and class_room(id)
        //replace the values into form
        foreignKeyQueryForm = queryBuilderLogic.processQueryBuilderForm(foreignKeyQueryForm,new QueryBuider[]{
                new QueryBuider(QueryBuilderType.ONE,FOREIGN_KEY_COLUMN_NAME_KEY,foreignKeyColName),
                new QueryBuider(QueryBuilderType.ONE,REFERENCE_COLUMN_NAME_KEY,referenceColName)
        });
        //parent form can be: CREATE TABLE Example{...., FOREIGN KEY ....}
        //replace the result into the parent form and return
        return queryBuilderLogic.processQueryBuilderForm(form,new QueryBuider(QueryBuilderType.COMMA,OTHER_ATTRIBUTE_KEY,foreignKeyQueryForm));

    }


    @Override
    public void setAnnotatedField(Field field) {
        this.field = field;
    }

    @Override
    public void setUpResource(ColumnResource columnResource) {
        columnResource.setRelationship(new Relationship(foreignKey.ref_fr(),foreignKey.name()));
    }

    @Override
    public void setAnnotation(Object[] annotations) {
        if(annotations.length != 0){
            if (annotations[0] instanceof ForeignKey){
                foreignKey =(ForeignKey) annotations[0];
            }
        }
    }
}