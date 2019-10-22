package annotation_process.user_annotaiton.table.IsTableProcessTest;

import DATA.SET1.ClassRoom;
import DATA.SET1.Role;
import DATA.SET1.Student;
import annotation_process.user_annotaiton.table.IsTable;
import annotation_process.user_annotaiton.table.IsTableProcess;
import inter.CRUDTableAnnotation;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static inter.SQLContrains.TABLE_CREATE_QUERY;
import static inter.SQLContrains.TABLE_DROP_QUERY;

@RunWith(Parameterized.class)
public class delete {
    private CRUDTableAnnotation crudTableAnnotation;
    private String rs;
    public delete(CRUDTableAnnotation crudTableAnnotation, String rs){
        this.crudTableAnnotation = crudTableAnnotation;
        this.rs = rs;
    }
    @Parameterized.Parameters
    public static Collection paramater() {
        IsTableProcess isTableProcess = new IsTableProcess();
        isTableProcess.setClass(Student.class);
        isTableProcess.setAnnotation(new Object[]{Student.class.getAnnotation(IsTable.class)});

        IsTableProcess isTableProcess2 = new IsTableProcess();
        isTableProcess2.setClass(ClassRoom.class);
        isTableProcess2.setAnnotation(new Object[]{ClassRoom.class.getAnnotation(IsTable.class)});

        IsTableProcess isTableProcess3 = new IsTableProcess();
        isTableProcess3.setClass(Role.class);
        isTableProcess3.setAnnotation(new Object[]{Role.class.getAnnotation(IsTable.class)});
        return Arrays.asList(new Object[][] {
                {
                    isTableProcess, "DROP TABLE student",
                },{
                    isTableProcess2, "DROP TABLE class_room",
                },{
                    isTableProcess3, "DROP TABLE Role",
                }
        });
    }
    @BeforeClass
    public static void initialize() {
    }

    @Test
    public void create() {
        Assert.assertEquals(crudTableAnnotation.delete(TABLE_DROP_QUERY),rs);
    }
    @AfterClass
    public static void end(){
    }
}
