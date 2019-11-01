package jpa.annotation_process.user_annotaiton.query;

import jpa.exception.NotFoundResourceException;
import jpa.exception.ParamaterException;
import jpa.inter.ExecuteAnnotation;
import jpa.query_data.logic.DeleteData;
import jpa.query_data.logic.util.CusC;
import jpa.resource.logic.TALogic;

import java.lang.reflect.Method;
import java.util.List;

/**
 * The {@link DeleteProcess} contains the methods processing the {@link Delete} annotation/
 * @author Huy Ngo Gia
 */
public class DeleteProcess<Void> implements ExecuteAnnotation {

    @Override
    public List<Void> process(Object proxy, Method method, Object[] args) {
        //@delete annotation
        //contain the data for creating the deleting query
        Delete delete = null;
        //if the method is annotated by @Delete
        if(method.getAnnotation(Delete.class) != null){
            delete = method.getAnnotation(Delete.class);
        }else{
            return null;
        }
        //process where clause
        String where = delete.where();
        //number of ?
        int paramaterCount = where.length() - where.replace("?", "").length();
        if(paramaterCount != args.length) throw new ParamaterException(paramaterCount,args.length);
        //TODO solve this
        Class clazz =this.getClass();
        if(TALogic.getTableResourceByClass(clazz) == null) throw new NotFoundResourceException(clazz);
        //process where clause
        CusC my = new CusC(delete.where(),args);
        DeleteData deleteData = new DeleteData(clazz);
        try {
            deleteData.delete(my);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean itMe(Method method) {
        if(method.getAnnotation(Delete.class) != null) return true;
        return false;
    }
}
