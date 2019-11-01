package jpa.example;

import jpa.annotation_process.user_annotaiton.column.*;
import jpa.annotation_process.user_annotaiton.table.IsTable;
import jpa.resource.model.CO;

import java.sql.Date;
@IsTable(name = "student")
public class Student {
    @Key
    @Den
    @IsColumn
    private Integer id;
    @Null(is_null = false)
    @IsColumn
    private String name;
    @Null(is_null = false)
    @IsColumn(name = "bird_date")
    private Date birdDate;
    @IsColumn
    private float point;
    @IsColumn(name = "student_code")
    private long studentCode;
    @ForeignKey(ref_fr = CO.CLASSROOM_id, name = CO.STUDENT_classRoom)
    @IsColumn(name="room_id")
    private ClassRoom classRoom;
    @ForeignKey(ref_fr = CO.ROLE_id, name = CO.STUDENT_role)
    @IsColumn(name = "role_id")
    private Role role;
    public Student() {
    }

    public Student(Integer id, String name, Date birdDate, float point, long studentCode, ClassRoom classRoom, Role role) {
        this.id = id;
        this.name = name;
        this.birdDate = birdDate;
        this.point = point;
        this.studentCode = studentCode;
        this.classRoom = classRoom;
        this.role = role;
    }

    public Student(int id, String name, Date birdDate, float point, long studentCode) {
        this.id = id;
        this.name = name;
        this.birdDate = birdDate;
        this.point = point;
        this.studentCode = studentCode;
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

    public Date getBirdDate() {
        return birdDate;
    }

    public void setBirdDate(Date birdDate) {
        this.birdDate = birdDate;
    }

    public float getPoint() {
        return point;
    }

    public void setPoint(float point) {
        this.point = point;
    }

    public long getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(long studentCode) {
        this.studentCode = studentCode;
    }public ClassRoom getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(ClassRoom classRoom) {
        this.classRoom = classRoom;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
