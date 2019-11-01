package jpa.query_data.logic;

import jpa.Hello;
import jpa.exception.NotFoundResourceException;
import jpa.query_data.logic.sub_select_process.*;
import jpa.query_data.logic.util.C;
import jpa.query_data.model.Select;
import jpa.resource.logic.SQLConfigurationLogic;
import jpa.resource.logic.TALogic;
import jpa.resource.model.CO;
import jpa.resource.model.ColumnResource;
import jpa.resource.model.TableResource;
import jpa.ultil.logic.IntArrayUtil;
import jpa.ultil.logic.QueryBuilderLogic;
import jpa.query_data.model.Filter;
import jpa.query_data.model.Order;
import jpa.query_data.model.Relationship;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import static jpa.inter.SQLContrains.SELECT_QUERY;

/**
 * The {@link SelectData} class contains the methods for selecting the data in database
 * @param <T> the model of result
 */
public class SelectData<T extends Object> {

    /**
     * the model of result
     */
    private Class clazz;

    /**
     * Filter for selecting
     */
    private Filter filter;

    /**
     * Number of selected data row  (TOP in select)
     */
    private int limit;

    /**
     * Order of the selected data row (Order By in select)
     */
    private Order order;

    /**
     * Foreign key relation is included
     */
    private Map<Integer,Relationship> relationship = null;

    /**
     * Where clause in select
     */
    private C condition = null;

    public SelectData(Class clazz) {
        this.clazz = clazz;
    }

    public void setFilter(Filter filter){
        this.filter = filter;
    }

    public void setLimit(int number){
        limit = number;
    }

    public void setOrderDes(int[] ids){
        if(order == null) order = new Order();
        order.setOrderDes(ids);
    }

    public void setOrderInc(int[] ids){
        if(order == null) order = new Order();
        order.setOrderInc(ids);
    }

    public void setRelationship(int[] relationship){
        for (int i=0;i<relationship.length;i++){
            if(this.relationship == null) this.relationship = new HashMap<>();
            this.relationship.put(relationship[i], CO.id.get(relationship[i]).getRelationship());
        }
    }

    public void setCondition(C where){
        this.condition = where;
    }

    public List<T> execute() throws SQLException, ClassNotFoundException,  IllegalAccessException, InstantiationException {
        List<T> Ts = new ArrayList<>();
        String form = SELECT_QUERY;
        QueryBuilderLogic queryBuilderLogic = new QueryBuilderLogic();
        //process limit
        if(limit > 0){
            LimitLogic limitLogic = new LimitLogic();
            form = limitLogic.process(form,limit);
        }
        //process order
        if(order != null){
            OrderLogic orderLogic = new OrderLogic();
            form = orderLogic.process(form,order);
        }
        //process relationship
        if(relationship != null){
            RelationshipLogic relationshipLogic = new RelationshipLogic();
            for (Relationship re :relationship.values())
            form = relationshipLogic.process(form,re);
        }
        //process condition
        if(condition != null){
            WhereLogic whereLogic = new WhereLogic();
            form = whereLogic.process(form,condition);
        }
        //process from
        if(clazz != null){
            FromLogic fromLogic = new FromLogic();
            form = fromLogic.process(form,clazz);
        }
        //get all column names of model table
        TableResource mainTableResource = TALogic.getTableResourceByClass(clazz);
        if(mainTableResource == null) throw new NotFoundResourceException(clazz);

        //all columns selected in query
        List<Integer> modelIds = IntArrayUtil.converArrayToList(mainTableResource.getColumns());
        //Integer -> column id| Relationship of that column
        Map<Integer, Relationship> relationshipColResource = new HashMap<>();
        //for process select
        SelectLogic selectLogic = new SelectLogic();
        //variable tmp
        ColumnResource columnTmp;
        int[] columnIdsTmp ;
        Relationship relTmp;
        //ids of foreign key field
        List<Integer> addTmp = new ArrayList<>();

        for (int i = modelIds.size()-1;i>=0;i--){
            columnTmp = CO.id.get(modelIds.get(i));
            if (columnTmp == null) throw new NotFoundResourceException(modelIds.get(i));
            //column is foreign key
            if(columnTmp.getRelationship() != null){
                if(relationship != null)
                    for (Map.Entry<Integer, Relationship> entry : relationship.entrySet()) {
                        relTmp = columnTmp.getRelationship();
                        //find relationship object based on foreign key
                        if(modelIds.get(i) == entry.getKey()){
                            columnIdsTmp = CO.id.get(relTmp.getReFr()).getTable().getColumns();
//                            addTmp.addAll(IntArrayUtil.converArrayToList(columnIdsTmp));
                            for (int id: columnIdsTmp) {
                                if(CO.id.get(id).getRelationship()==null) addTmp.add(id);
                                relationshipColResource.put(id, relTmp);
                            }
                        }
                }
                //not select the value of foreign key column
                modelIds.remove(i);
            }
        }
        //if value = null -> main object
        for (int id: modelIds){
            relationshipColResource.put(id,null);
        }
        //join two list
        modelIds.addAll(addTmp);
        //process filter
        if(filter != null){
            FilterLogic filterLogic = new FilterLogic();
            modelIds = IntArrayUtil.converArrayToList(filterLogic.process(IntArrayUtil.converListToArray(modelIds),filter));
        }
        //conver to Select object to process
        Select[] modeSelects = selectLogic.getSelects(IntArrayUtil.converListToArray(modelIds));
        //process select clause
        form = selectLogic.process(form,modeSelects);
        //clear query
        form = queryBuilderLogic.cleanQueryBuilderForm(form);
        System.out.println(form);
        //get connection
        Connection connection = SQLConfigurationLogic.getConnection(Hello.database);
        if(connection != null){
            //execute the query
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(form);
            Select selectTmp;
            //value of field, object tmp, main object
            Object valueTmp, objectTmp = null,resultObject;
            //contain all object: main object, foreign key object
            Map<Relationship,Object> allObject;
            while (rs.next()){
                allObject = new HashMap<>();
                for(int i = 0; i< modeSelects.length;i++) {
                    selectTmp = modeSelects[i];
                    columnTmp = CO.id.get(selectTmp.getId());
                    valueTmp =  rs.getObject(selectTmp.getSqlAsName(),columnTmp.getField().getType());
                    relTmp = relationshipColResource.get(selectTmp.getId());
                    //if the object of foreign key still is not created
                    if(allObject.get(relTmp) == null){
                        //insert into list [Relationship][Object]
                        allObject.put(relationshipColResource.get(selectTmp.getId()),selectTmp.getClazz().newInstance());
                    }
                    //get the object of foreign key
                    objectTmp = allObject.get(relationshipColResource.get(selectTmp.getId()));
                    columnTmp.getField().setAccessible(true);
                    columnTmp.getField().set(objectTmp,valueTmp);
                }
                //get main object
                resultObject = allObject.get(null);
                if(relationship != null)
                for (Map.Entry<Integer, Relationship> entry : relationship.entrySet()) {
                    relTmp = entry.getValue();
                    columnTmp = CO.id.get(relTmp.getReTo());
                    if(allObject.get(relTmp)!= null){
                        //get the foreign key object
                        objectTmp = allObject.get(relTmp);
                        columnTmp.getField().setAccessible(true);
                        columnTmp.getField().set(resultObject,objectTmp);
                    }
                }
                System.out.println("Object: "+resultObject);
                Ts.add((T)resultObject);
            }
        }
        return Ts;
    }
}
