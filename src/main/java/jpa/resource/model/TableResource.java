package jpa.resource.model;

import java.util.Arrays;

/**
 * The {@link TableResource} class contains all information of table (both sql and java).
 */
public class TableResource {
    /**
     * class of the table
     */
    private Class table;
    /**
     * name of the table in database
     */
    private String sqlName;
    /**
     * ids of columns/fields
     */
    private int[] columns;

    public TableResource(Class table, String sqlName, int[] columns) {
        this.table = table;
        this.sqlName = sqlName;
        this.columns = columns;
    }

    public int[] getColumns() {
        return columns;
    }

    public void setColumns(int[] columns) {
        this.columns = columns;
    }

    public TableResource(Class table, String sqlName) {
        this.table = table;
        this.sqlName = sqlName;
    }

    public TableResource(Class table) {
        this.table = table;
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

    @Override
    public String toString() {
        return "TableResource{" +
                "table=" + table +
                ", sqlName='" + sqlName + '\'' +
                ", columns=" + Arrays.toString(columns) +
                '}';
    }
}
