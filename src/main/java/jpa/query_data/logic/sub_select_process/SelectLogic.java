package jpa.query_data.logic.sub_select_process;

import jpa.e.QueryBuilderType;
import jpa.exception.NotFoundResourceException;
import jpa.query_data.model.Select;
import jpa.resource.logic.COLogic;
import jpa.resource.model.CO;
import jpa.resource.model.ColumnResource;
import jpa.ultil.logic.QueryBuilderLogic;
import jpa.ultil.model.QueryBuider;

import static jpa.inter.SQLContrains.*;

public class SelectLogic {
    public String process(String form,Select[] selects){
        String subForm ;
        //conver to Select
        ColumnResource columnResource;
        QueryBuilderLogic queryBuilderLogic = new QueryBuilderLogic();
        QueryBuider[] queryBuiders = new QueryBuider[selects.length];
        for (int i = 0; i < selects.length ; i++){
            subForm = SELECTED_COLUMN_SUB_QUERY;
            subForm = queryBuilderLogic.processQueryBuilderForm(subForm,new QueryBuider[]{
                    new QueryBuider(QueryBuilderType.ONE,COLUMN_NAME_KEY,COLogic.getFormatedColumnResource("[TA].[COL]",selects[i].getId())),
                    new QueryBuider(QueryBuilderType.ONE,COMLUMN_AS_NAME_KEY,selects[i].getSqlAsName()),
            });
            queryBuiders[i] = new QueryBuider(QueryBuilderType.COMMA,SELECTED_COLUMN_KEY,subForm+"" );
        }
        return queryBuilderLogic.processQueryBuilderForm(form,queryBuiders);
    }
    public Select[] getSelects(int[] ids){
        Select[] selects = new Select[ids.length];
        ColumnResource columnResource ;
        for (int i = 0 ; i< ids.length;i++){
            columnResource = CO.id.get(ids[i]);
            if(columnResource == null) throw new NotFoundResourceException(ids[i]);
            selects[i] = new Select(ids[i],columnResource.getSqlName(),columnResource.getTable().getSqlName(),columnResource.getTable().getTable());
        }
        return selects;
    }
}
