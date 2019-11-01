package jpa.resource.model;

import jpa.inter.CRUDColumnAnnotation;
import jpa.query_data.model.Relationship;

import java.lang.reflect.Field;
/**
 * The {@link ColumnResource} class contains all information of column (both sql and java).
 */
public class ColumnResource {

    /**
     * table resource of column
     */
    private TableResource table;

    /**
     * field of the column
     */
    private Field field;

    /**
     * name of the column in database
     */
    private String sqlName;

    /**
     * type of the column in database
     */
    private String sqlType;

    /**
     * relationship of the column
     */
    private Relationship relationship;

    public ColumnResource(TableResource table, Field field, String sqlName, String sqlType, Relationship relationship) {
        this.table = table;
        this.field = field;
        this.sqlName = sqlName;
        this.sqlType = sqlType;
        this.relationship = relationship;
    }

    public ColumnResource(Field field) {
        this.field = field;
    }

    public ColumnResource(TableResource table, Field field, String sqlName, String sqlType) {
        this.table = table;
        this.field = field;
        this.sqlName = sqlName;
        this.sqlType = sqlType;
    }

    public TableResource getTable() {
        return table;
    }

    public void setTable(TableResource table) {
        this.table = table;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public String getSqlName() {
        return sqlName;
    }

    public void setSqlName(String sqlName) {
        this.sqlName = sqlName;
    }

    public String getSqlType() {
        return sqlType;
    }

    public void setSqlType(String sqlType) {
        this.sqlType = sqlType;
    }


    public Relationship getRelationship() {
        return relationship;
    }

    public void setRelationship(Relationship relationship) {
        this.relationship = relationship;
    }

    @Override
    public String toString() {
        return "ColumnResource{" +
                "table=" + table +
                ", field=" + field +
                ", sqlName='" + sqlName + '\'' +
                ", sqlType='" + sqlType + '\'' +
                ", relationship=" + relationship +
                '}';
    }
}
