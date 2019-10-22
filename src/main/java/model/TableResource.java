package model;

public class TableResource {
    private Class table;
    private String sqlName;

    public TableResource(Class table, String sqlName) {
        this.table = table;
        this.sqlName = sqlName;
    }

    public Class getTable() {
        return table;
    }

    public void setTable(Class table) {
        this.table = table;
    }

    public String getSqlName() {
        return sqlName;
    }

    public void setSqlName(String sqlName) {
        this.sqlName = sqlName;
    }
}
