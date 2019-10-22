package annotation_process.user_annotaiton.database;

import annotation_process.user_annotaiton.column.Null;
import e.QueryBuilderType;
import inter.CRUDDatabaseAnnotation;
import logic.QueryBuilderLogic;
import model.QueryBuider;

public class DatabaseProcess implements CRUDDatabaseAnnotation {
    private Database database;

    public DatabaseProcess() {
    }

    public String create(String form) {
        //The Key and Value of @Database
        String key = "DATABASE_NAME";
        String value = database.name();
        //Create QueryBuider from the key and the value
        QueryBuider queryBuider = new QueryBuider(QueryBuilderType.ONE,key,value);
        //Process form
        QueryBuilderLogic queryBuilderLogic = new QueryBuilderLogic();
        return queryBuilderLogic.processQueryBuilderForm(form,queryBuider);
    }

    public String drop(String form) {
        //The Key and Value of @Database
        String key = "DATABASE_NAME";
        String value = database.name();
        //Create QueryBuider from the key and the value
        QueryBuider queryBuider = new QueryBuider(QueryBuilderType.ONE,key,value);
        //Process form
        QueryBuilderLogic queryBuilderLogic = new QueryBuilderLogic();
        return queryBuilderLogic.processQueryBuilderForm(form,queryBuider);
    }



    @Override
    public void setAnnotation(Object[] annotations) {
        if(annotations.length != 0) {
            if (annotations[0] instanceof Database) {
                database = (Database) annotations[0];
            }
        }
    }
}
