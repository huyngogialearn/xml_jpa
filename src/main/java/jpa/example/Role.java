package jpa.example;

import jpa.annotation_process.user_annotaiton.column.Den;
import jpa.annotation_process.user_annotaiton.column.IsColumn;
import jpa.annotation_process.user_annotaiton.column.Key;
import jpa.annotation_process.user_annotaiton.table.IsTable;

import java.util.List;
@IsTable(name="Role")
public class Role {
    @Key
    @Den
    @IsColumn
    private int id;
    @IsColumn
    private String name;
    private List<Student> students;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
