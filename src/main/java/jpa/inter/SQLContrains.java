package jpa.inter;

public interface SQLContrains {

    /**
     * The query creating a database.
     */
    static final String DB_CREATE_QUERY ="CREATE DATABASE [DATABASE_NAME]";

    /**
     * Name of a database.
     */
    static final String DATABASE_NAME_KEY = "DATABASE_NAME";

    /**
     * The query dropping a database.
     */
    static final String DB_DROP_QUERY ="DROP DATABASE [DATABASE_NAME]";

    /**
     * The query creating a table.
     */
    static final String TABLE_CREATE_QUERY ="CREATE TABLE [TABLE_NAME]({COLUMN_CREATE_SUB_QUERY<,>});";

    /**
     * The query disabling foreign key checks
     */
    static final String DISABLE_FOREIGN_KEY_CHECKS ="SET FOREIGN_KEY_CHECKS = 0;";

    /**
     * The query enabling foreign key checks
     */
    static final String ENABLE_FOREIGN_KEY_CHECKS ="SET FOREIGN_KEY_CHECKS = 0;";

    /**
     * The query drop a table if exists
     */
    static final String DROP_TABLE_QUERY ="DROP TABLE IF EXISTS [TABLE_NAME];";

    /**
     * Name of a table.
     */
    static final String TABLE_NAME_KEY = "TABLE_NAME";

    /**
     * The query creating a column in a table.
     */
    static final String COLUMN_CREATE_SUB_QUERY_KEY = "COLUMN_CREATE_SUB_QUERY";

    /**
     * The sub-query creating a column in query creating a table.
     */
    static final String COLUMN_CREATE_SUB_QUERY = "[COLUMN_NAME] [COLUMN_TYPE] {ATTRIBUTE< >} {OTHER_ATTRIBUTE<,>}";

    /**
     * Name of columns in a table.
     */
    static final String COLUMN_NAME_KEY = "COLUMN_NAME";

    /**
     * Type (int, varchar, long, ...) of columns in a table.
     */
    static final String COLUMN_TYPE_KEY = "COLUMN_TYPE";

    /**
     * Attributes (Auto increate, primary key, ...) of columns in a table.
     */
    static final String ATTRIBUTE_KEY = "ATTRIBUTE";

    /**
     * Other attributes (foreign key, ...) of columns in a table.
     */
    static final String OTHER_ATTRIBUTE_KEY = "OTHER_ATTRIBUTE";

    /**
     * The sub-query creating a relationship (foreign key) of a column in query creating a table.
     */
    static final String FOREIGN_KEY_SUB_QUERY = ",FOREIGN KEY ([FOREIGN_KEY_COLUMN_NAME]) REFERENCES [REFERENCE_COLUMN_NAME]";

    /**
     * Name of foreign key column in a table.
     */
    static final String FOREIGN_KEY_COLUMN_NAME_KEY = "FOREIGN_KEY_COLUMN_NAME";

    /**
     * The column that referents to the foreign key column.
     */
    static final String REFERENCE_COLUMN_NAME_KEY = "REFERENCE_COLUMN_NAME";

    /**
     * The query dropping a table.
     */
    static final String TABLE_DROP_QUERY ="DROP TABLE [TABLE_NAME]";

    /**
     * Query inserting data.
     */
    static final String DATA_INSERT_QUERY ="INSERT INTO [TABLE_NAME] ({COLUMN_NAME_KEY<,>}) VALUES ({COLUMN_VALUE<,>});";

    /**
     * Name of a column in a table and often go with a value.
     */
    static final String COLUMN_NAME_KEY_KEY = "COLUMN_NAME_KEY";

    /**
     * Value of a column in a table and often go with a Name
     */
    static final String COLUMN_VALUE_KEY = "COLUMN_VALUE";

    /**
     * The query updating data.
     */
    static final String DATA_UPDATE_QUERY ="UPDATE [TABLE_NAME] SET {COLUMN_NAME_KEY_COLUMN_VALUE<,>} WHERE [CONDITION];";

    /**
     * Condtion (where) to search in a query.
     */
    static final String CONDITION_KEY = "CONDITION";

    /**
     * Name and value of a column in a table and used in update a table.
     */
    static final String COLUMN_NAME_KEY_COLUMN_VALUE_KEY = "COLUMN_NAME_KEY_COLUMN_VALUE";

    /**
     * The sub-query setting a value for a column.
     */
    static final String COLUMN_NAME_KEY_COLUMN_VALUE_SUB_QUERY = "[COLUMN_NAME_KEY] = [COLUMN_VALUE]";

    /**
     * The query deleting data .
     */
    static final String DATA_DELETE_QUERY ="DELETE FROM [TABLE_NAME] WHERE [CONDITION];";

    /**
     * The query setting order of data.
     */
    static final String ORDER_KEY = "ORDER";

    /**
     * The sub-query setting order of data.
     */
    static final String ORDER_SUB_QUERY = "ORDER BY {ORDER_COLUMN1<,>} [ORDER_SIGN1] {ORDER_COLUMN2<,>} [ORDER_SIGN2]";

    /**
     * Name of a column to order data
     */
    static final String ORDER_COLUMN1_KEY = "ORDER_COLUMN1";

    /**
     * ASC/DESC in ORDER BY query.
     */
    static final String ORDER_SIGN1_KEY = "ORDER_SIGN1";

    /**
     * Name of a column to order data
     */
    static final String ORDER_COLUMN2_KEY = "ORDER_COLUMN2";

    /**
     * ASC/DESC in ORDER BY query.
     */
    static final String ORDER_SIGN2_KEY = "ORDER_SIGN2";

    /**
     * The query selecting data.
     */
    static final String SELECT_QUERY ="SELECT [SQLSERVER_TOP] {SELECTED_COLUMN<,>} FROM {TABLE_NAME<,>} {RELATIONSHIP< >} [WHERE] [ORDER] [MYSQL_TOP] ";

    /**
     * The name of column selected
     */
    static final String SELECTED_COLUMN_KEY = "SELECTED_COLUMN";

    /**
     * The sub-query setting column selected
     */
    static final String SELECTED_COLUMN_SUB_QUERY = "[COLUMN_NAME] AS [COMLUMN_AS_NAME]";

    /**
     * The rename of columns
     */
    static final String COMLUMN_AS_NAME_KEY = "COMLUMN_AS_NAME";


    /**
     * The query creating a column in a table.
     */
    static final String MYSQL_TOP_KEY = "MYSQL_TOP";
    /**
     * The sub-query setting number of data selected.
     */
    static final String MYSQL_TOP_SUB_QUERY = "LIMIT [LIMIT_NUMBER]";


    /**
     * Number of data selected
     */
    static final String LIMIT_NUMBER_KEY = "LIMIT_NUMBER";

    /**
     * The query setting a condition to search data.
     */
    static final String WHERE_KEY = "WHERE";

    /**
     * The sub-query setting a condition to search data.
     */
    static final String WHERE_SUB_QUERY = "WHERE [CONDITION]";

    /**
     * The query joining two table.
     */
    static final String RELATIONSHIP_KEY= "RELATIONSHIP";

    /**
     * The sub-query joining two table.
     */
    static final String RELATIONSHIP_SUB_QUERY = "JOIN [FOREIGN_KEY_TABLE] ON [FOREIGN_KEY_TABLE_COLUMN] = [REFERENCE_TABLE_COLUMN]";

    /**
     * The table name of the foreign key
     */
    static final String FOREIGN_KEY_TABLE_KEY = "FOREIGN_KEY_TABLE";

    /**
     * The column name of the foreign key
     */
    static final String FOREIGN_KEY_TABLE_COLUMN_KEY = "FOREIGN_KEY_TABLE_COLUMN";

    /**
     * The column name referencing to the foreign key
     */
    static final String REFERENCE_TABLE_COLUMN_KEY = "REFERENCE_TABLE_COLUMN";


}
