package jpa.example;

import jpa.annotation_process.user_annotaiton.column.*;
import jpa.annotation_process.user_annotaiton.table.IsTable;

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

    public ClassRoom(int id, String name, long numberOfSeats) {
        this.id = id;
        this.name = name;
        this.numberOfSeats = numberOfSeats;
    }

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

    public long getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(long numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }
}
