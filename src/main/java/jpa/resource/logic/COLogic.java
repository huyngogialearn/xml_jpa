package jpa.resource.logic;

import jpa.e.QueryBuilderType;
import jpa.exception.NotFoundResourceException;
import jpa.resource.model.CO;
import jpa.ultil.logic.QueryBuilderLogic;
import jpa.resource.model.ColumnResource;
import jpa.ultil.model.QueryBuider;

import java.util.ArrayList;
import java.util.List;import jpa.resource.model.CO;

/**
 * The {@link COLogic} contains the methods processing the {@link } resource
 */
public class COLogic {
    /**
     *
     * @param form
     * @param ids
     * @return
     */
    public static String[] getFormatedColumnResource(String form,int[] ids){
        if(ids == null) return null;
        List<String> results = new ArrayList<>();
        for (int id:ids){
            results.add(getFormatedColumnResource(form,id));
        }
        return results.toArray(new String[0]);
    }

    /**
     * Formatting the [table_name] and [coulumn_name]
     * @param form form of format
     * @param id id of coulmn/field
     * @return formatted value
     */
    public static String getFormatedColumnResource(String form,int id){
        String tableKey = "TA", colKey = "COL";
        ColumnResource columnResource;
        QueryBuilderLogic queryBuilderLogic = new QueryBuilderLogic();
        columnResource = CO.id.get(id);
        if(columnResource == null) throw new NotFoundResourceException(id);
        form = queryBuilderLogic.processQueryBuilderForm(form,new QueryBuider[]{
                new QueryBuider(QueryBuilderType.ONE,tableKey,columnResource.getTable().getSqlName()),
                new QueryBuider(QueryBuilderType.ONE,colKey,columnResource.getSqlName())
        });
        return form;
    }

}
