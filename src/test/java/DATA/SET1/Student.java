package DATA.SET1;

import annotation_process.user_annotaiton.column.*;
import annotation_process.user_annotaiton.table.IsTable;
import ultil.resource.CO;

import java.sql.Date;
@IsTable(name = "student")
public class Student {
    static public int student_name = 1;

    @Key
    @Den
    @IsColumn
    private int id;
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
    @ManyToOne(ref_fr = CO.class_room_id, name = CO.student_room_id)
    @IsColumn(name="role_id")
    private ClassRoom classRoom;
    @ManyToOne(ref_fr = CO.role_id, name = CO.student_role_id)
    @IsColumn(name = "room_id")
    private Role role;
    public Student() {
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
    }
}
