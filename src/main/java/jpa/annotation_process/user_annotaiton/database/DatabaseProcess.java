package jpa.annotation_process.user_annotaiton.database;

import jpa.e.QueryBuilderType;
import jpa.inter.CRUDDatabaseAnnotation;
import jpa.resource.model.SQLConfigurationResource;
import jpa.ultil.logic.QueryBuilderLogic;
import jpa.ultil.model.QueryBuider;
import static jpa.inter.SQLContrains.*;

/**
 * The {@link DatabaseProcess} contains the methods processing the {@link Database} annotation/
 * @author Huy Ngo Gia
 */
public class DatabaseProcess implements CRUDDatabaseAnnotation {

    /**
     * The data for processing the field.
     */
    private Database database;

    public DatabaseProcess() {
    }

    public String create(String form) {
        //parent form can be: CREATE DATABASE [DATABASE_NAME]
        String databaseName = database.name();
        QueryBuilderLogic queryBuilderLogic = new QueryBuilderLogic();
        //result -> CREATE DATABASE example_database
        //replace the value into the parent form
        return queryBuilderLogic.processQueryBuilderForm(form,new QueryBuider(QueryBuilderType.ONE,DATABASE_NAME_KEY,databaseName));
    }

    public String drop(String form) {
        //parent form can be: DROP DATABASE [DATABASE_NAME]
        String databaseName = database.name();
        QueryBuilderLogic queryBuilderLogic = new QueryBuilderLogic();
        //result -> DROP DATABASE example_database
        //replace the value into the parent form
        return queryBuilderLogic.processQueryBuilderForm(form,new QueryBuider(QueryBuilderType.ONE,DATABASE_NAME_KEY,databaseName));

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
