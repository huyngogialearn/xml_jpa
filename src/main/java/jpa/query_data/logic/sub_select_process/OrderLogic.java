package jpa.query_data.logic.sub_select_process;

import jpa.e.QueryBuilderType;
import jpa.resource.logic.COLogic;
import jpa.ultil.logic.QueryBuilderLogic;
import jpa.ultil.model.QueryBuider;
import jpa.query_data.model.Order;

import static jpa.inter.SQLContrains.*;
/**
 * The {@link OrderLogic} contains the methods processing {@link Order} class.
 * @author Huy Ngo Gia
 */
public class OrderLogic {
    /**
     * he methods processing the order of the data that will be gotten
     * @param parentForm parent form selecting data
     * @param order columns and their direction data
     * @return the form is filled information
     */
    public String process(String parentForm, Order order){
        //both directions is empty -> nothing to process
        if((order.getOrderDes() == null || order.getOrderDes().length == 0) && (order.getOrderInc() == null || order.getOrderInc().length == 0))
        return parentForm;
        //result -> ORDER table.col1, table.col2, DECS, table.col3, table.col4 ASC
        //parent form can be: ... {RELATIONSHIP< >} [WHERE] [ORDER] [MYSQL_TOP]
        String form = ORDER_SUB_QUERY;
        QueryBuilderLogic queryBuilderLogic = new QueryBuilderLogic();
        //get -> DECS || DECS
        //if there is a direction -> DES , two direction -> DES,
        String orderSign1 = (order.getOrderInc()==null || order.getOrderInc().length == 0)?"DESC":"DESC,";
        String orderSign2 = "ASC";
        //get -> [table.col1, table.col2] format
        String[] orderColumns1 = COLogic.getFormatedColumnResource("[TA].[COL]",order.getOrderDes());
        String[] orderColumns2 = COLogic.getFormatedColumnResource("[TA].[COL]",order.getOrderInc());
        //replace values into sub-form
        if(orderColumns1 != null){
            //DECS|DECS
            form = queryBuilderLogic.processQueryBuilderForm(form,new QueryBuider(QueryBuilderType.ONE,ORDER_SIGN1_KEY,orderSign1));
            //[table.col1 ,...]
            form = queryBuilderLogic.processQueryBuilderForm(form,queryBuilderLogic.getQueryBuider(QueryBuilderType.COMMA,ORDER_COLUMN1_KEY,orderColumns1));
        }
        if(orderColumns2 != null){
            //ASC
            form = queryBuilderLogic.processQueryBuilderForm(form,new QueryBuider(QueryBuilderType.ONE,ORDER_SIGN2_KEY,orderSign2));
            //[table.col3 ,...]
            form = queryBuilderLogic.processQueryBuilderForm(form,queryBuilderLogic.getQueryBuider(QueryBuilderType.COMMA,ORDER_COLUMN2_KEY,orderColumns2));

        }
        //replace values into parent-form
        //result -> [WHERE] ORDER table.col1, table.col2, DECS, table.col3, table.col4 ASC [MYSQL_TOP]
        return queryBuilderLogic.processQueryBuilderForm(parentForm,new QueryBuider(QueryBuilderType.ONE,ORDER_KEY,form));
    }
}
