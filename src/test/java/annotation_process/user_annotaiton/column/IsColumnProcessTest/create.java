package annotation_process.user_annotaiton.column.IsColumnProcessTest;

import DATA.SET1.ClassRoom;
import DATA.SET1.Role;
import DATA.SET1.Student;
import annotation_process.user_annotaiton.column.IsColumn;
import annotation_process.user_annotaiton.column.IsColumnProcess;
import exception.MySQLNotSupportException;
import inter.CRUDColumnAnnotation;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static inter.SQLContrains.COLUMN_CREATE_QUERY;

@RunWith(Parameterized.class)
public class create {
    private CRUDColumnAnnotation crudColumnAnnotation;
    private String rs;
    public create(CRUDColumnAnnotation crudColumnAnnotation, String rs){
        this.crudColumnAnnotation = crudColumnAnnotation;
        this.rs = rs;
    }
    @Parameterized.Parameters
    public static Collection paramater() throws NoSuchFieldException {
        IsColumnProcess isColumnProcess = new IsColumnProcess();
        isColumnProcess.setAnnotatedField(Student.class.getDeclaredField("id"));
        isColumnProcess.setAnnotation(new Object[]{Student.class.getDeclaredField("id").getAnnotation(IsColumn.class)});

        IsColumnProcess isColumnProcess2 = new IsColumnProcess();
        isColumnProcess2.setAnnotatedField(Student.class.getDeclaredField("birdDate"));
        isColumnProcess2.setAnnotation(new Object[]{Student.class.getDeclaredField("birdDate").getAnnotation(IsColumn.class)});

        IsColumnProcess isColumnProcess3 = new IsColumnProcess();
        isColumnProcess3.setAnnotatedField(Student.class.getDeclaredField("point"));
        isColumnProcess3.setAnnotation(new Object[]{Student.class.getDeclaredField("point").getAnnotation(IsColumn.class)});

        IsColumnProcess isColumnProcess4 = new IsColumnProcess();
        isColumnProcess4.setAnnotatedField(Student.class.getDeclaredField("studentCode"));
        isColumnProcess4.setAnnotation(new Object[]{Student.class.getDeclaredField("studentCode").getAnnotation(IsColumn.class)});

        IsColumnProcess isColumnProcess5 = new IsColumnProcess();
        isColumnProcess5.setAnnotatedField(Student.class.getDeclaredField("name"));
        isColumnProcess5.setAnnotation(new Object[]{Student.class.getDeclaredField("name").getAnnotation(IsColumn.class)});

        IsColumnProcess isColumnProcess6 = new IsColumnProcess();
        isColumnProcess6.setAnnotatedField(ClassRoom.class.getDeclaredField("id"));
        isColumnProcess6.setAnnotation(new Object[]{ClassRoom.class.getDeclaredField("id").getAnnotation(IsColumn.class)});

        IsColumnProcess isColumnProcess7 = new IsColumnProcess();
        isColumnProcess7.setAnnotatedField(ClassRoom.class.getDeclaredField("name"));
        isColumnProcess7.setAnnotation(new Object[]{ClassRoom.class.getDeclaredField("name").getAnnotation(IsColumn.class)});

        IsColumnProcess isColumnProcess8 = new IsColumnProcess();
        isColumnProcess8.setAnnotatedField(ClassRoom.class.getDeclaredField("numberOfSeats"));
        isColumnProcess8.setAnnotation(new Object[]{ClassRoom.class.getDeclaredField("numberOfSeats").getAnnotation(IsColumn.class)});

        IsColumnProcess isColumnProcess9 = new IsColumnProcess();
        isColumnProcess9.setAnnotatedField(Role.class.getDeclaredField("id"));
        isColumnProcess9.setAnnotation(new Object[]{Role.class.getDeclaredField("id").getAnnotation(IsColumn.class)});

        IsColumnProcess isColumnProcess10 = new IsColumnProcess();
        isColumnProcess10.setAnnotatedField(Role.class.getDeclaredField("name"));
        isColumnProcess10.setAnnotation(new Object[]{Role.class.getDeclaredField("name").getAnnotation(IsColumn.class)});
        return Arrays.asList(new Object[][] {
                {
                    isColumnProcess, "id int {ATTRIBUTE< >} {OTHER_ATTRIBUTE<,>}"
                },{
                    isColumnProcess2, "bird_date date {ATTRIBUTE< >} {OTHER_ATTRIBUTE<,>}"
                },{
                    isColumnProcess3, "point float {ATTRIBUTE< >} {OTHER_ATTRIBUTE<,>}"
                },{
                    isColumnProcess4, "student_code long {ATTRIBUTE< >} {OTHER_ATTRIBUTE<,>}"
                },{
                    isColumnProcess5, "name varchar(255) {ATTRIBUTE< >} {OTHER_ATTRIBUTE<,>}"
                },{
                    isColumnProcess6, "id int {ATTRIBUTE< >} {OTHER_ATTRIBUTE<,>}"
                },{
                    isColumnProcess7, "name varchar(400) {ATTRIBUTE< >} {OTHER_ATTRIBUTE<,>}"
                },{
                    isColumnProcess8, "numberOfSeats long {ATTRIBUTE< >} {OTHER_ATTRIBUTE<,>}"
                },{
                    isColumnProcess9, "id int {ATTRIBUTE< >} {OTHER_ATTRIBUTE<,>}"
                },{
                isColumnProcess10, "name varchar(255) {ATTRIBUTE< >} {OTHER_ATTRIBUTE<,>}"
                }
        });
    }
    @BeforeClass
    public static void initialize() {
        System.out.println("IsColumnProcessTest is running...");
    }

    @Test
    public void create() throws MySQLNotSupportException {
        Assert.assertEquals(crudColumnAnnotation.create(COLUMN_CREATE_QUERY),rs);
    }
    @AfterClass
    public static void end(){
        System.out.println("IsColumnProcessTest stoped");
    }
}
