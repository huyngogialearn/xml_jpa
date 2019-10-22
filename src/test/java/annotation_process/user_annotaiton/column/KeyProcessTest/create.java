package annotation_process.user_annotaiton.column.KeyProcessTest;

import DATA.SET1.ClassRoom;
import DATA.SET1.Role;
import DATA.SET1.Student;
import annotation_process.user_annotaiton.column.Key;
import annotation_process.user_annotaiton.column.KeyProcess;
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
        KeyProcess keyProcess = new KeyProcess();
        keyProcess.setAnnotation(new Object[]{Student.class.getDeclaredField("id").getAnnotation(Key.class)});

        KeyProcess keyProcess2 = new KeyProcess();
        keyProcess2.setAnnotation(new Object[]{ClassRoom.class.getDeclaredField("id").getAnnotation(Key.class)});

        KeyProcess keyProcess3 = new KeyProcess();
        keyProcess3.setAnnotation(new Object[]{Role.class.getDeclaredField("id").getAnnotation(Key.class)});
        return Arrays.asList(new Object[][] {
                {
                    keyProcess, "[COLUMN_NAME] [COLUMN_TYPE] PRIMARY KEY {ATTRIBUTE< >} {OTHER_ATTRIBUTE<,>}"
                },{
                    keyProcess2, "[COLUMN_NAME] [COLUMN_TYPE] PRIMARY KEY {ATTRIBUTE< >} {OTHER_ATTRIBUTE<,>}"
                },{
                    keyProcess3, "[COLUMN_NAME] [COLUMN_TYPE] PRIMARY KEY {ATTRIBUTE< >} {OTHER_ATTRIBUTE<,>}"
                }
        });
    }
    @BeforeClass
    public static void initialize() {
        System.out.println("KeyProcessTest is running...");
    }

    @Test
    public void create() throws MySQLNotSupportException {
        Assert.assertEquals(crudColumnAnnotation.create(COLUMN_CREATE_QUERY),rs);
    }
    @AfterClass
    public static void end(){
        System.out.println("KeyProcessTest stoped");
    }
}
