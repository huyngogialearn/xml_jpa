package annotation_process.user_annotaiton.column.NullProcessTest;

import DATA.SET1.ClassRoom;
import DATA.SET1.Role;
import DATA.SET1.Student;
import annotation_process.user_annotaiton.column.Den;
import annotation_process.user_annotaiton.column.DenProcess;
import annotation_process.user_annotaiton.column.Null;
import annotation_process.user_annotaiton.column.NullProcess;
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
        NullProcess nullProcess = new NullProcess();
        nullProcess.setAnnotation(new Object[]{Student.class.getDeclaredField("name").getAnnotation(Null.class)});

        NullProcess nullProcess2 = new NullProcess();
        nullProcess2.setAnnotation(new Object[]{Student.class.getDeclaredField("birdDate").getAnnotation(Null.class)});

        NullProcess nullProcess3 = new NullProcess();
        nullProcess3.setAnnotation(new Object[]{ClassRoom.class.getDeclaredField("name").getAnnotation(Null.class)});
        return Arrays.asList(new Object[][] {
                {
                    nullProcess, "[COLUMN_NAME] [COLUMN_TYPE] NOT NULL {ATTRIBUTE< >} {OTHER_ATTRIBUTE<,>},{OTHER_ATTRIBUTE<,>}"
                },{
                    nullProcess2, "[COLUMN_NAME] [COLUMN_TYPE] NOT NULL {ATTRIBUTE< >} {OTHER_ATTRIBUTE<,>}"
                },{
                    nullProcess3, "[COLUMN_NAME] [COLUMN_TYPE] {ATTRIBUTE< >} {OTHER_ATTRIBUTE<,>}"
                }
        });
    }
    @BeforeClass
    public static void initialize() {
        System.out.println("NullProcessTest is running...");
    }

    @Test
    public void create() throws MySQLNotSupportException {
        Assert.assertEquals(crudColumnAnnotation.create(COLUMN_CREATE_QUERY),rs);
    }
    @AfterClass
    public static void end(){
        System.out.println("NullProcessTest stoped");
    }
}
