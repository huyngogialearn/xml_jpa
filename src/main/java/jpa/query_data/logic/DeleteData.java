package jpa.query_data.logic;

import jpa.Hello;
import jpa.e.QueryBuilderType;
import jpa.exception.NotFoundResourceException;
import jpa.query_data.logic.util.C;
import jpa.resource.logic.SQLConfigurationLogic;
import jpa.resource.logic.TALogic;
import jpa.resource.model.TableResource;
import jpa.ultil.logic.QueryBuilderLogic;
import jpa.ultil.model.QueryBuider;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static jpa.inter.SQLContrains.*;

/**
 * The {@link DeleteData} class contains the methods for deleting the data into table
 * @param <T> the model deleting
 */
public class DeleteData<T> {
    /**
     * The class of model
     */
    private Class clazz;

    public DeleteData(Class clazz) {
        this.clazz = clazz;
    }

    /**
     * The method deleting data into table
     * @param where condition to search data in table
     * @throws NotFoundResourceException not found needed jpa.resource of the given paramaters
     */
    public void delete(C where) throws SQLException, ClassNotFoundException {
        QueryBuilderLogic queryBuilderLogic = new QueryBuilderLogic();
        TableResource tableResource = TALogic.getTableResourceByClass(clazz);
        //not found jpa.resource
        if(tableResource == null) throw new NotFoundResourceException(clazz);
        //result -> DELETE FROM table WHERE table.id = '1';
        //parent form can be: DELETE FROM [TABLE_NAME] WHERE [CONDITION];
        String parentForm = DATA_DELETE_QUERY;
        //result -> DELETE FROM table WHERE [CONDITION];
        //replace table name into parent form
        parentForm = queryBuilderLogic.processQueryBuilderForm(parentForm,new QueryBuider(QueryBuilderType.ONE,TABLE_NAME_KEY,tableResource.getSqlName()));
        //result -> DELETE FROM tabl WHERE table.id = '1';
        //replace condition into parent form
        parentForm = queryBuilderLogic.processQueryBuilderForm(parentForm,new QueryBuider(QueryBuilderType.ONE,CONDITION_KEY,where.getCondition()));
        parentForm = queryBuilderLogic.cleanQueryBuilderForm(parentForm);
        Connection connection = SQLConfigurationLogic.getConnection(Hello.database);
        if(connection != null) {
            Statement stmt = connection.createStatement();
            boolean rs = stmt.execute(parentForm);
        }
    }
}
