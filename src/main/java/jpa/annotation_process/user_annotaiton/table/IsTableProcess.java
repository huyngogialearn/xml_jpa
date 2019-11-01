package jpa.annotation_process.user_annotaiton.table;

import jpa.e.QueryBuilderType;
import jpa.inter.CRUDTableAnnotation;
import jpa.resource.model.TableResource;
import jpa.ultil.logic.QueryBuilderLogic;
import jpa.ultil.model.QueryBuider;
import static jpa.inter.SQLContrains.*;
public class IsTableProcess implements CRUDTableAnnotation {

    /**
     * The data for processing the field.
     */
    private IsTable isTableClass;

    /**
     * The class of this annotation.
     */
    private Class parentClazz;

    public IsTableProcess() {
    }

    public String create(String form) {
        //parent form can be: CREATE TABLE [TABLE_NAME]({COLUMN_CREATE_SUB_QUERY<,>})
        String nameTable = (isTableClass.name() == null || isTableClass.name().equals(""))?parentClazz.getSimpleName():isTableClass.name();
        QueryBuilderLogic queryBuilderLogic = new QueryBuilderLogic();
        //result -> CREATE TABLE example_table ({COLUMN_CREATE_SUB_QUERY<,>})
        //replace the value into the parent form
        return queryBuilderLogic.processQueryBuilderForm(form,new QueryBuider(QueryBuilderType.ONE,TABLE_NAME_KEY,nameTable));
    }

    public String delete(String form) {
        //parent form can be: DROP TABLE [TABLE_NAME]
        String nameTable = (isTableClass.name() == null || isTableClass.name().equals(""))?parentClazz.getSimpleName():isTableClass.name();
        QueryBuilderLogic queryBuilderLogic = new QueryBuilderLogic();
        //result -> DROP TABLE example_table
        // replace the value into the parent form
        return queryBuilderLogic.processQueryBuilderForm(form,new QueryBuider(QueryBuilderType.ONE,TABLE_NAME_KEY,nameTable));

    }

    @Override
    public void setClass(Class clazz) {
        parentClazz = clazz;
    }

    @Override
    public void setUpResource(TableResource tableResource) {
        String nameTable = (isTableClass.name() == null || isTableClass.name().equals(""))?parentClazz.getSimpleName():isTableClass.name();
        tableResource.setSqlName(nameTable);
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
