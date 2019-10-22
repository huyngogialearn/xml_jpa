package annotation_process.user_annotaiton.column.ManyToOneProcessTest;

import DATA.SET1.Student;
import annotation_process.user_annotaiton.column.ManyToOne;
import annotation_process.user_annotaiton.column.ManyToOneProcess;
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
        ManyToOneProcess manyToOneProcess = new ManyToOneProcess();
        manyToOneProcess.setAnnotation(new Object[]{Student.class.getDeclaredField("classRoom").getAnnotation(ManyToOne.class)});

        ManyToOneProcess manyToOneProcess2 = new ManyToOneProcess();
        manyToOneProcess2.setAnnotation(new Object[]{Student.class.getDeclaredField("role").getAnnotation(ManyToOne.class)});

        return Arrays.asList(new Object[][] {
                {
                    manyToOneProcess, "[COLUMN_NAME] [COLUMN_TYPE] {ATTRIBUTE< >} ,FOREIGN KEY (room_id) REFERENCES class_room(id),{OTHER_ATTRIBUTE<,>}"
                },{
                    manyToOneProcess2, "[COLUMN_NAME] [COLUMN_TYPE] {ATTRIBUTE< >} ,FOREIGN KEY (role_id) REFERENCES Role(id),{OTHER_ATTRIBUTE<,>}"
                }
        });
    }
    @BeforeClass
    public static void initialize() {
    }

    @Test
    public void create() throws MySQLNotSupportException {
        Assert.assertEquals(crudColumnAnnotation.create(COLUMN_CREATE_QUERY),rs);
    }
    @AfterClass
    public static void end(){
    }
}
