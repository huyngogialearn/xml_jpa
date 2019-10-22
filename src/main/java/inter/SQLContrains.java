package inter;

public interface SQLContrains {
    static final String DB_CREATE_QUERY ="CREATE DATABASE [DATABASE_NAME]";
    static final String DB_DROP_QUERY ="DROP DATABASE [DATABASE_NAME]";
    static final String TABLE_CREATE_QUERY ="CREATE TABLE [TABLE_NAME]({COLUMNS<,>})";
    static final String TABLE_DROP_QUERY ="DROP TABLE [TABLE_NAME]";
    static final String COLUMN_DROP_QUERY ="DROP TABLE [TABLE_NAME]";
    static final String COLUMN_CREATE_QUERY = "[COLUMN_NAME] [COLUMN_TYPE] {ATTRIBUTE< >} {OTHER_ATTRIBUTE<,>}";
    static final String FOREIGN_KEY_QUERY = ",FOREIGN KEY ([FOREIGN_KEY_NAME]) REFERENCES [REFERENCE_TO_NAME]";
    static final String ROW_DELETE_QUERY ="DELETE FROM table_name WHERE condition;";
    static final String ROW_UPDATE_QUERY ="UPDATE table_name\n" +
                                            "SET column1 = value1, column2 = value2, ...\n" +
                                            "WHERE condition;";
    static final String ROW_INSERT_QUERY ="INSERT INTO table_name (column1, column2, column3, ...)\n" +
                                            "VALUES (value1, value2, value3, ...);";
    static final String SELECT_DROP_QUERY ="DROP TABLE [TABLE_NAME]";
}
