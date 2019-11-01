package jpa.query_data.logic.sub_select_process;

import jpa.e.QueryBuilderType;
import jpa.exception.NotFoundResourceException;
import jpa.resource.logic.COLogic;
import jpa.resource.model.CO;
import jpa.resource.model.ColumnResource;
import jpa.ultil.logic.QueryBuilderLogic;
import jpa.ultil.model.QueryBuider;
import jpa.query_data.model.Relationship;

import static jpa.inter.SQLContrains.*;

/**
 *
 */
public class RelationshipLogic {
    public String  process(String parentForm,Relationship relationship){
        //sub-form can be: JOIN [FOREIGN_KEY_TABLE] ON [FOREIGN_KEY_TABLE_COLUMN] = [REFERENCE_TABLE_COLUMN]
        //result -> Join table1 ON table1.id = table2.id
        String form = RELATIONSHIP_SUB_QUERY;
        QueryBuilderLogic queryBuilderLogic = new QueryBuilderLogic();
        //get -> table1
        //get the foreign key table name
        String foreignKeyTableName = COLogic.getFormatedColumnResource("[TA]",relationship.getReFr());
        //get -> table1.id
        //get the foreign key column name with table1.id format
        String foreignKeyColumnName = COLogic.getFormatedColumnResource("[TA].[COL]",relationship.getReFr());
        //get -> table2.id
        //get the referen column name with table2.id format
        String referenceComlumnName = COLogic.getFormatedColumnResource("[TA].[COL]",relationship.getReTo());
        //replace the values into the sub-form
        form = queryBuilderLogic.processQueryBuilderForm(form,new QueryBuider[]{
                new QueryBuider(QueryBuilderType.ONE,FOREIGN_KEY_TABLE_KEY,foreignKeyTableName),
                new QueryBuider(QueryBuilderType.ONE,FOREIGN_KEY_TABLE_COLUMN_KEY,foreignKeyColumnName),
                new QueryBuider(QueryBuilderType.ONE,REFERENCE_TABLE_COLUMN_KEY,referenceComlumnName)
        });
        //replace the value into the parent-form
        //result -> ... * FROM {TABLE_NAME<,>}, Join table1 ON table1.id = table2.id {RELATIONSHIP< >} [WHERE]...
        return queryBuilderLogic.processQueryBuilderForm(parentForm,new QueryBuider(QueryBuilderType.COMMA,RELATIONSHIP_KEY,form));
    }
    public int[] getResource(int foreignKeyId){
        ColumnResource columnResource = CO.id.get(foreignKeyId);
        if(columnResource == null) throw new NotFoundResourceException(foreignKeyId);
        if(columnResource.getRelationship() == null) return null;
        ColumnResource referenceColumnResource = CO.id.get(columnResource.getRelationship().getReFr());
        return referenceColumnResource.getTable().getColumns();
    }
}
