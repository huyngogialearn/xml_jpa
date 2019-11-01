package jpa.query_data.logic.util;

import jpa.e.QueryBuilderType;
import jpa.resource.model.CO;
import jpa.resource.model.ColumnResource;
import jpa.ultil.logic.QueryBuilderLogic;
import jpa.ultil.model.QueryBuider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;import jpa.resource.model.CO;
import java.util.Map;

/**
 * The {@link C} class (CompareOperation) contains method to create a where clause instead of using a string that leads to mistakes in coding process
 * @author Huy Ngo Gia
 */
public class C {
    Map<Integer,String> values = new HashMap<Integer,String>();
    List<String> querys;
    boolean preCO = true;
    /**
     * Map to 'and' in sql query
     * @param CompareOperation clause that need to connect
     */
    public C and(C CompareOperation){
        if(preCO){
            preCO = false;
            querys.set(querys.size()-2,querys.get(querys.size()-2)+")");
        }
        addCompare("and");
            preCO = true;
        return CompareOperation;
    }

    /**
     * Map to 'or' in sql query
     * @param CompareOperation clause that need to connect
     */
    public C or(C CompareOperation){
        if(preCO){
            preCO = false;
            querys.set(querys.size()-2,querys.get(querys.size()-2)+")");
        }
        addCompare("or");
        preCO = true;
        return CompareOperation;
    }

    /**
     * The {@link C#m(int, String)} method (More).
     * Map to '>' in sql query
     * @param ColumnId id of column that need to compare
     * @param value compared value
     */
    public C m(int ColumnId, String value){
        commonCompare(ColumnId,value,">");

        return this;
    }

    /**
     * The {@link C#m_e(int, String)} method (More Equal).
     * Map to '>=' in sql query
     * @param ColumnId id of column that need to compare
     * @param value compared value
     */
    public C m_e(int ColumnId, String value){
        commonCompare(ColumnId,value,">=");

        return this;
    }

    /**
     * The {@link C#l(int, String)} method (Less).
     * Map to '<' in sql query
     * @param ColumnId id of column that need to compare
     * @param value compared value
     */
    public C l(int ColumnId, String value){
        commonCompare(ColumnId,value,"<");


        return this;
    }

    /**
     * The {@link C#l_e(int, String)} method (Less Equal).
     * Map to '<=' in sql query
     * @param ColumnId id of column that need to compare
     * @param value compared value
     */
    public C l_e(int ColumnId, String value){
        commonCompare(ColumnId,value,"<=");


        return this;
    }

    /**
     * The {@link C#e(int, String)} method (Equal).
     * Map to '=' in sql query
     * @param ColumnId id of column that need to compare
     * @param value compared value
     */
    public C e(int ColumnId, String value){
        commonCompare(ColumnId,value,"=");

        return this;
    }

    /**
     * The {@link C#li(int, String)} method (Equal).
     * Map to 'like' in sql query
     * <p>'%' represents zero, one or multiple characters</p>
     * <p>'_' represents a single number or character</p>
     * @param ColumnId id of column that need to compare
     * @param ValueForm compared value
     */
    public C li(int ColumnId, String ValueForm){
        commonCompare(ColumnId,ValueForm,"like");
        return this;
    }

    /**
     * The common method of And and Or method
     * @param c
     */
    private void addCompare(String c){
        preCO = true;
        if(querys.size()<=2) return;
        String query;
        for(int i = querys.size()-2 ;i>=0;i--){
            query = querys.get(i);
            if(query==null){
                querys.set(i," "+c+" ");
                return;
            }
        }
    }

    /**
     * The common method of eq, jpa.e, lt,...
     * @param key
     * @param value
     * @param sign
     */
    private void commonCompare(int key, String value, String sign){
        if(querys == null){
            querys = new ArrayList<>();
        }else{
            if(preCO == false){
                preCO = true;
                querys.set(querys.size()-2,"("+querys.get(querys.size()-2));
            }
        preCO = false;}
        values.put(key,value);
        querys.add("["+key+"] "+sign+" '"+value+"'");
        querys.add(null);
    }

    /**
     * Get the condition string for querying
     * @return
     */
    public String getCondition(){
        String rs ="";
        for (String s : querys){
            rs +=(s!= null)?s:"";
        }
        int key;
        String sqlName;
        ColumnResource columnResource;
        QueryBuilderLogic ex =new QueryBuilderLogic();
        QueryBuider qb;
        for(Map.Entry<Integer, String> entry : values.entrySet()) {
            key = entry.getKey();
            columnResource = CO.id.get(key);
            sqlName = columnResource.getTable().getSqlName()+"."+columnResource.getSqlName();
            qb = new QueryBuider(QueryBuilderType.ONE,key+"",sqlName);
            rs = ex.processQueryBuilderForm(rs,qb);
        }
        return rs;
    }
}
