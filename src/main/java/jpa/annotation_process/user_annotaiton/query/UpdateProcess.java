package jpa.annotation_process.user_annotaiton.query;

import jpa.exception.NotFoundResourceException;
import jpa.exception.ParamaterException;
import jpa.inter.ExecuteAnnotation;
import jpa.query_data.logic.UpdateData;
import jpa.query_data.logic.util.CusC;
import jpa.query_data.model.Filter;
import jpa.resource.logic.TALogic;

import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.List;import jpa.resource.model.CO;

/**
 * The {@link UpdateProcess} contains the methods processing the {@link Update} annotation
 * @author Huy Ngo Gia
 */
public class UpdateProcess<Void> implements ExecuteAnnotation {
    @Override
    public List<Void> process(Object proxy, Method method, Object[] args) {

        //@delete annotation
        //contain the data for creating the deleting query
        Update update = null;
        if(method.getAnnotation(Update.class) != null){
            update = method.getAnnotation(Update.class);
        }else{
            return null;
        }
        //process where clause
        String where = update.where();
        //number of ?
        int paramaterCount = where.length() - where.replace("?", "").length();
        if(paramaterCount+1 != args.length) throw new ParamaterException(paramaterCount,args.length);
        //get clazz
        Class clazz = args[0].getClass();
        if(TALogic.getTableResourceByClass(clazz) == null) throw new NotFoundResourceException(clazz);
        //contain paramaters for where clause
        Object[] argsTmp = new Object[args.length-1];
        for (int i = 0 ; i< args.length-1; i++){
            argsTmp[i] = args[i+1];
        }
        CusC my = new CusC(update.where(),argsTmp);
        UpdateData updateData = new UpdateData(clazz);
        try {
            if ((update.ignore() == null && update.include() == null)) {
                updateData.update(args[0], my);
            } else {
                //process filter
                updateData.update(args[0], new Filter(update.include(), update.ignore()), my);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public boolean itMe(Method method) {
        if(method.getAnnotation(Update.class) != null) return true;
        return false;
    }
}
