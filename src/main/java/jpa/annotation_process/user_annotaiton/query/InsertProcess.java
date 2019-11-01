package jpa.annotation_process.user_annotaiton.query;

import jpa.exception.NotFoundResourceException;
import jpa.exception.ParamaterException;
import jpa.inter.ExecuteAnnotation;
import jpa.query_data.logic.InsertData;
import jpa.query_data.model.Filter;
import jpa.resource.logic.TALogic;

import java.lang.reflect.Method;
import java.util.List;import jpa.resource.model.CO;

/**
 * The {@link InsertProcess} contains the methods processing the {@link Insert} annotation/
 * @author Huy Ngo Gia
 */
public class InsertProcess<Void> implements ExecuteAnnotation {
    @Override
    public List<Void> process(Object proxy, Method method, Object[] args) {
        //@Insert annotation
        //contain the data for creating the creating query
        Insert insert = null;
        //if the method is annotated by @Insert
        if(method.getAnnotation(Insert.class) != null){
            insert = method.getAnnotation(Insert.class);
        }else{
            return null;
        }
        //request only inseted object
        if(args.length != 1) throw new ParamaterException(1,args.length);
        //get the class of inserted object
        Class clazz =  args[0].getClass();
        if(TALogic.getTableResourceByClass(clazz) == null) throw new NotFoundResourceException(clazz);
        //process query
        InsertData insertData = new InsertData(clazz);
        try {
            //process filter
            if((insert.ignore()==null && insert.include() == null)){
                insertData.insert(args[0]);
            }else{
                insertData.insert(args[0],new Filter(insert.include(),insert.ignore()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public boolean itMe(Method method) {
        if(method.getAnnotation(Insert.class) != null) return true;
        return false;
    }
}
