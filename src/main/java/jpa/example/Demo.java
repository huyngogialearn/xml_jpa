package jpa.example;

import jpa.annotation_process.user_annotaiton.column.IsColumn;
import jpa.annotation_process.user_annotaiton.column.Key;
import jpa.annotation_process.user_annotaiton.query.DAO;
import jpa.annotation_process.user_annotaiton.query.SelectQuery;

import java.util.List;

import jpa.annotation_process.user_annotaiton.table.IsTable;
import jpa.resource.model.CO;

@IsTable
public interface Demo {
    @Key
    @IsColumn
    int id = 0;
//    @SelectQuery(where = "id = ?")
//    public List<Student> findById(int id);
}
