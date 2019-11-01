package jpa.query_data.model;

/**
 * The {@link Select} contains the data illustrating a selected column in select query
 */
public class Select {
    /**
     * jpa.resource id
     */
    private int id;
    /**
     * sql name
     */
    private String sqlName;
    /**
     * rename of sql name
     */
    private String sqlAsName;
    /**
     * sql table name
     */
    private String sqltableName;
    /**
     * classify with java name [table_name]0[column_name]
     */
    private String SIGN = "0";
    /**
     * java type
     */
    private Class clazz ;
    /**
     * belong a relationship
     */
    private int foreignKeyId;


    public Select(int id, String sqlName ,String sqltableName,Class clazz) {
        this.id = id;
        this.sqlName = sqlName;
        this.sqlAsName = sqltableName+SIGN+sqlName;
        this.sqltableName = sqltableName;
        this.clazz = clazz;
    }
    public Select(int id, String sqlName ,String sqltableName,Class clazz, int foreignKeyId) {
        this.id = id;
        this.sqlName = sqlName;
        this.sqlAsName = sqltableName+SIGN+sqlName;
        this.sqltableName = sqltableName;
        this.clazz = clazz;
        this.foreignKeyId = foreignKeyId;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSqlName() {
        return sqlName;
    }

    public void setSqlName(String sqlName) {
        this.sqlName = sqlName;
    }

    public String getSqlAsName() {
        return sqlAsName;
    }

    public void setSqlAsName(String sqlAsName) {
        this.sqlAsName = sqlAsName;
    }
}
