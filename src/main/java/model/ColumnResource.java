package model;

import inter.CRUDColumnAnnotation;

import java.lang.reflect.Field;

public class ColumnResource {
    private TableResource table;
    private Field field;
    private String sqlName;
    private String sqlType;
    private CRUDColumnAnnotation[] annotations;

    public ColumnResource(TableResource table,Field field, String sqlName, String sqlType, CRUDColumnAnnotation[] annotations) {
        this.table = table;
        this.field = field;
        this.sqlName = sqlName;
        this.sqlType = sqlType;
        this.annotations = annotations;
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

    public CRUDColumnAnnotation[] getAnnotations() {
        return annotations;
    }

    public void setAnnotations(CRUDColumnAnnotation[] annotations) {
        this.annotations = annotations;
    }
}
