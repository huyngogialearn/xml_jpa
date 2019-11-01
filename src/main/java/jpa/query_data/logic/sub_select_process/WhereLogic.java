package jpa.query_data.logic.sub_select_process;

import jpa.e.QueryBuilderType;
import jpa.query_data.logic.util.C;
import jpa.ultil.logic.QueryBuilderLogic;
import jpa.ultil.model.QueryBuider;

import static jpa.inter.SQLContrains.*;

/**
 * The {@link WhereLogic} contains the methods processing limiting the number of data that will be gotten
 * @author Huy Ngo Gia
 */
public class WhereLogic {

    /**
     * The methods processing condition to search data in table
     * @param parentForm parent form selecting data
     * @param condition condition searching data in table
     * @return the form is filled information
     */
    public String process(String parentForm, C condition){
        //parent form can be: WHERE [CONDITION]
        String form = WHERE_SUB_QUERY;
        QueryBuilderLogic ex = new QueryBuilderLogic();
        //result -> WHERE table1.id = table2.id
        //replace the value into the parent form
        form = ex.processQueryBuilderForm(form,new QueryBuider(QueryBuilderType.ONE,CONDITION_KEY,condition.getCondition()));
        return ex.processQueryBuilderForm(parentForm,new QueryBuider(QueryBuilderType.ONE,WHERE_KEY,form));
    }
}
