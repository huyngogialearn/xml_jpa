package jpa.query_data.logic;

import jpa.Hello;
import jpa.e.QueryBuilderType;
import jpa.exception.NotFoundResourceException;
import jpa.query_data.logic.sub_select_process.FilterLogic;
import jpa.resource.logic.SQLConfigurationLogic;
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
import java.util.ArrayList;
import java.util.List;import jpa.resource.model.CO;

import static jpa.inter.SQLContrains.*;

/**
 * The {@link InsertData} class contains the methods for inserting the data into table
 * @param <T> the model inserted
 */
public class InsertData<T> {
    /**
     * The class of model
     */
    Class t;

    public InsertData(Class t){
        this.t = t;
    }

    /**
     * The method inserting data into table
     * @param object data inserted into table
     * @throws IllegalAccessException not found field in the given object
     * @throws NotFoundResourceException not found needed jpa.resource of the given paramaters
     */
    public void insert(T object) throws IllegalAccessException, SQLException, ClassNotFoundException {
        insert(object,null);
    }

    /**
     * The method inserting data into table
     * @param object data inserted into table
     * @param filter filter for data selected
     * @throws IllegalAccessException not found field in the given object
     * @throws NotFoundResourceException not found needed jpa.resource of the given paramaters
     */
    public void insert(T object, Filter filter) throws IllegalAccessException, SQLException, ClassNotFoundException {
        //columnIds = [1, 2, 3 ,4]
        //all column in table
        TableResource tableResource = TALogic.getTableResourceByClass(t);
        //not found jpa.resource
        if(tableResource == null) throw new NotFoundResourceException(t);
        int[] columnIds = tableResource.getColumns();
        //columnIds = [1, 3, 4] maybe
        //identify the column ids chosen
        if(filter != null){
            FilterLogic filterLogic = new FilterLogic();
            columnIds = filterLogic.process(columnIds,filter);
        }
        //result -> INSERT INTO table1 (`column1`,`column3`,`column4`) VALUES (1,3,4)
        //paren form can be: INSERT INTO [TABLE_NAME] ({COLUMN_NAME_KEY<,>}) VALUES ({COLUMN_VALUE<,>})
        String form = DATA_INSERT_QUERY;
        //[column1, column3, column4]
        //list of column names
        List<String> columnNames = new ArrayList<>();
        //[1, 3, 4]
        //list of column values
        List<String> columnValues = new ArrayList<>();
        QueryBuilderLogic queryBuilderLogic = new QueryBuilderLogic();
        //to conver column value type to sql type
        TypeConvert typeConvert = new TypeConvert();
        ColumnResource resource;
        for (int i = 0; i< columnIds.length ; i++){
            resource = CO.id.get(columnIds[i]);
            //not found jpa.resource
            if(resource == null) throw new NotFoundResourceException(columnIds[i]);
            //coulmn is a foreign key
            if(resource.getRelationship() != null) continue;;
            //if field is private modifier
            resource.getField().setAccessible(true);
            //format column -> `column1`
            columnNames.add(String.format("`%s`",resource.getSqlName()));
            //conver column value type to sql type
            columnValues.add(typeConvert.convertSQLFormat(resource.getField().getType(),resource.getField().get(object)));
        }
        //result -> INSERT INTO [TABLE_NAME] (`colmn1`, `column3`, `column4`,{COLUMN_NAME_KEY<,>}) VALUES ({COLUMN_VALUE<,>})
        //replace into parent-form
        form = queryBuilderLogic.processQueryBuilderForm(form,queryBuilderLogic.getQueryBuider(QueryBuilderType.COMMA,COLUMN_NAME_KEY_KEY,columnNames.toArray(new String[0])));
        //result -> INSERT INTO [TABLE_NAME] (`colmn1`, `column3`, `column4`,{COLUMN_NAME_KEY<,>}) VALUES (1, 3, 4,{COLUMN_VALUE<,>})
        form = queryBuilderLogic.processQueryBuilderForm(form,queryBuilderLogic.getQueryBuider(QueryBuilderType.COMMA,COLUMN_VALUE_KEY,columnValues.toArray(new String[0])));
        //result -> INSERT INTO table (`colmn1`, `column3`, `column4`,{COLUMN_NAME_KEY<,>}) VALUES (1, 3, 4{COLUMN_VALUE<,>})
        //process table name
        form = queryBuilderLogic.processQueryBuilderForm(form,new QueryBuider(QueryBuilderType.ONE,TABLE_NAME_KEY,String.format("`%s`",tableResource.getSqlName())));
        System.out.println(queryBuilderLogic.cleanQueryBuilderForm(form));
        form= queryBuilderLogic.cleanQueryBuilderForm(form);
        System.out.println(form);
        Connection connection = SQLConfigurationLogic.getConnection(Hello.database);
        if(connection != null) {
            Statement stmt = connection.createStatement();
            boolean rs = stmt.execute(form);
        }
    }
}
