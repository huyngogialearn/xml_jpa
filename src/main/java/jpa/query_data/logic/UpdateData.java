package jpa.query_data.logic;

import jpa.Hello;
import jpa.e.QueryBuilderType;
import jpa.exception.NotFoundResourceException;
import jpa.query_data.logic.sub_select_process.FilterLogic;
import jpa.query_data.logic.util.C;
import jpa.resource.logic.SQLConfigurationLogic;
import jpa.resource.model.CO;
import jpa.ultil.logic.QueryBuilderLogic;
import jpa.resource.logic.TALogic;
import jpa.ultil.logic.TypeConvert;
import jpa.resource.model.ColumnResource;
import jpa.ultil.model.QueryBuider;
import jpa.resource.model.TableResource;
import jpa.query_data.model.Filter;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static jpa.inter.SQLContrains.*;

/**
 * The {@link UpdateData} class contains the methods for updating the data into table
 * @param <T> the model updating
 */
public class UpdateData<T> {

    /**
     * The class of model
     */
    private Class clazz;

    public UpdateData(Class clazz) {
        this.clazz = clazz;
    }

    /**
     * The method updating data into table
     * @param object data updating table
     * @param filter ilter for data selected
     * @param where condition to search data in table
     * @throws IllegalAccessException not found field in the given object
     * @throws NotFoundResourceException not found needed jpa.resource of the given paramaters
     */
    public void update(T object, Filter filter, C where) throws IllegalAccessException, SQLException, ClassNotFoundException {
        //columnIds = [1, 2, 3 ,4]
        //all column in table
        TableResource tableResource = TALogic.getTableResourceByClass(clazz);
        //not found jpa.resource
        if(tableResource == null) throw new NotFoundResourceException(clazz);
        int[] columnIds = tableResource.getColumns();
        //columnIds = [1, 3, 4] maybe
        //identify the column ids chosen
        if(filter != null){
            FilterLogic filterLogic = new FilterLogic();
            columnIds = filterLogic.process(columnIds,filter);
        }
        QueryBuilderLogic queryBuilderLogic = new QueryBuilderLogic();
        //result -> UPDATE table SET `id` = 2, `name` = 'update' WHERE table.id = '1';
        //parent form can be: UPDATE [TABLE_NAME] SET {COLUMN_NAME_KEY_COLUMN_VALUE<,>} WHERE [CONDITION];
        String parentForm = DATA_UPDATE_QUERY;
        String columnName, columnValue, subForm;
        //to conver column value type to sql type
        TypeConvert typeConvert = new TypeConvert();
        ColumnResource resource;
        for (int i = 0; i< columnIds.length;i++){
            //`id` = 2|`name` = 'update'
            //sub form can be: [COLUMN_NAME_KEY] = [COLUMN_VALUE]
            subForm = COLUMN_NAME_KEY_COLUMN_VALUE_SUB_QUERY;
            resource = CO.id.get(columnIds[i]);
            //not found jpa.resource
            if(resource == null) throw new NotFoundResourceException(columnIds[i]);
            //column is a foreign key
            if(resource.getRelationship() != null) continue;;
            //if field is private modifier
            resource.getField().setAccessible(true);
            //format column -> `column1`
            columnName = String.format("`%s`",resource.getSqlName());
            //convert column value type to sql type
            columnValue = typeConvert.convertSQLFormat(resource.getField().getType(),resource.getField().get(object));
            //result -> `id` = 2
            //replace the values into sub-form
            subForm = queryBuilderLogic.processQueryBuilderForm(subForm,new QueryBuider[]{
                    new QueryBuider(QueryBuilderType.ONE,COLUMN_NAME_KEY_KEY,columnName),
                    new QueryBuider(QueryBuilderType.ONE,COLUMN_VALUE_KEY,columnValue)
            });
            //result -> UPDATE [TABLE_NAME] SET `id` = 2,{COLUMN_NAME_KEY_COLUMN_VALUE<,>} WHERE [CONDITION];
            //replace the sub-form into parent-form
            parentForm = queryBuilderLogic.processQueryBuilderForm(parentForm,new QueryBuider(QueryBuilderType.COMMA,COLUMN_NAME_KEY_COLUMN_VALUE_KEY,subForm));
        }
        //result -> UPDATE table SET `id` = 2,{COLUMN_NAME_KEY_COLUMN_VALUE<,>} WHERE [CONDITION];
        //replace table name into parent form
        parentForm = queryBuilderLogic.processQueryBuilderForm(parentForm,new QueryBuider(QueryBuilderType.ONE,TABLE_NAME_KEY,String.format("`%s`",tableResource.getSqlName())));
        //result -> UPDATE table SET `id` = 2,{COLUMN_NAME_KEY_COLUMN_VALUE<,>} WHERE table.id = '1';
        //replace condition into parent form
        parentForm = queryBuilderLogic.processQueryBuilderForm(parentForm,new QueryBuider(QueryBuilderType.ONE,CONDITION_KEY,where.getCondition()));
        System.out.println(queryBuilderLogic.cleanQueryBuilderForm(parentForm));
        parentForm = queryBuilderLogic.cleanQueryBuilderForm(parentForm);
        System.out.println(parentForm);
        Connection connection = SQLConfigurationLogic.getConnection(Hello.database);
        if(connection != null) {
            Statement stmt = connection.createStatement();
            boolean rs = stmt.execute(parentForm);
        }
    }

    /**
     * The method updating data into table
     * @param object data updating table
     * @param where condition to search data in table
     * @throws IllegalAccessException not found field in the given object
     * @throws NotFoundResourceException not found needed jpa.resource of the given paramaters
     */
    public void update(T object,C where) throws IllegalAccessException, SQLException, ClassNotFoundException {
        update(object,null,where);
    }

}
