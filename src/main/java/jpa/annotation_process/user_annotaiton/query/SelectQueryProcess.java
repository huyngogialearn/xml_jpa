package jpa.annotation_process.user_annotaiton.query;

import jpa.exception.ParamaterException;
import jpa.inter.ExecuteAnnotation;
import jpa.query_data.logic.SelectData;
import jpa.query_data.logic.util.CusC;
import jpa.query_data.model.Filter;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;import jpa.resource.model.CO;


/**
 * The {@link SelectQueryProcess} contains the methods processing the {@link SelectQuery} annotation/
 * @author Huy Ngo Gia
 */
public class SelectQueryProcess<T> implements ExecuteAnnotation {
    @Override
    public List<T> process(Object proxy, Method method, Object[] args) {
        //result list
        List<T> results = new ArrayList<>();
        //@SelectQuery annotation
        //contain the data for creating the selecting query
        SelectQuery selectQuery = null;
        if(method.getAnnotation(SelectQuery.class) != null){
            selectQuery = method.getAnnotation(SelectQuery.class);
        }else{
            return null;
        }
        //get the class of inserted object List<T> -> get T
        ParameterizedType p = ((ParameterizedType)method.getGenericReturnType());
        Class<?> clazz = (Class<?>) p.getActualTypeArguments()[0];
        SelectData<T> selectData = new SelectData(clazz);
        //process limit
        if(selectQuery.limit()>0)
        selectData.setLimit(selectQuery.limit());
        //process order
        if(selectQuery.des() != null && selectQuery.des().length > 0)
        selectData.setOrderDes(selectQuery.des());
        if(selectQuery.inc() != null && selectQuery.inc().length > 0)
            selectData.setOrderDes(selectQuery.inc());
        //proces filter
        if(selectQuery.ignore() != null || selectQuery.include() != null)
            selectData.setFilter(new Filter(selectQuery.include(),selectQuery.ignore()));
        //process relationship
        if(selectQuery.fill_relationships() != null && selectQuery.fill_relationships().length>0)
            selectData.setRelationship(selectQuery.fill_relationships());
        //process where clause
        if(!selectQuery.where().isEmpty()){
            String where = selectQuery.where();
            int paramaterCount = where.length() - where.replace("?", "").length();
            if(paramaterCount != args.length) throw new ParamaterException(paramaterCount,args.length);
            CusC myC = new CusC(selectQuery.where(),args);
            selectData.setCondition(myC);
        }
        try {
            return selectData.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public boolean itMe(Method method) {
        if(method.getAnnotation(SelectQuery.class) != null) return true;
        return false;
    }
}
