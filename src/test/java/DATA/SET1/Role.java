package DATA.SET1;

import annotation_process.user_annotaiton.column.Den;
import annotation_process.user_annotaiton.column.IsColumn;
import annotation_process.user_annotaiton.column.Key;
import annotation_process.user_annotaiton.table.IsTable;

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
}
