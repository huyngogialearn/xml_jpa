package jpa.query_data.logic.sub_select_process;

import jpa.e.QueryBuilderType;
import jpa.ultil.logic.QueryBuilderLogic;
import jpa.ultil.model.QueryBuider;
import static jpa.inter.SQLContrains.*;
/**
 * The {@link LimitLogic} contains the methods processing limiting the number of data that will be gotten
 * @author Huy Ngo Gia
 */
public class LimitLogic {

    /**
     * The methods processing limiting the number of data that will be gotten
     * @param parentForm parent form selecting data
     * @param limit the number of data that will be selected
     * @return the form is filled information
     */
    public String process(String parentForm,int limit){
        //parent form can be: LIMIT [LIMIT_NUMBER]
        String form = MYSQL_TOP_SUB_QUERY;
        QueryBuilderLogic ex = new QueryBuilderLogic();
        //result -> LIMIT 1
        //replace the value into the parent form
        form = ex.processQueryBuilderForm(form,new QueryBuider(QueryBuilderType.ONE,LIMIT_NUMBER_KEY,limit+""));
        return ex.processQueryBuilderForm(parentForm,new QueryBuider(QueryBuilderType.ONE,MYSQL_TOP_KEY,form));
    }
}
