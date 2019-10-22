package DATA.SET1;

import annotation_process.user_annotaiton.column.*;
import annotation_process.user_annotaiton.table.IsTable;

import java.util.List;

@IsTable(name="class_room")
public class ClassRoom {
    @Key
    @Den
    @IsColumn
    private int id;
    @Null(is_null = true)
    @IsColumn(length = 400)
    String name;
    @IsColumn
    private long numberOfSeats;
    private List<Student> students;

}
