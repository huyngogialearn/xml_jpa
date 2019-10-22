package annotation_process.user_annotaiton.column.DenProcessTest;

import DATA.SET1.ClassRoom;
import DATA.SET1.Role;
import DATA.SET1.Student;
import annotation_process.user_annotaiton.column.Den;
import annotation_process.user_annotaiton.column.DenProcess;
import annotation_process.user_annotaiton.table.IsTable;
import annotation_process.user_annotaiton.table.IsTableProcess;
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

import static inter.SQLContrains.*;

@RunWith(Parameterized.class)
public class create {
    private CRUDColumnAnnotation crudColumnAnnotation;
    private String rs;
    private String tmpRs;
    public create(CRUDColumnAnnotation crudColumnAnnotation, String rs, String tmpRs){
        this.crudColumnAnnotation = crudColumnAnnotation;
        this.rs = rs;
        this.tmpRs = tmpRs;
    }
    @Parameterized.Parameters
    public static Collection paramater() throws NoSuchFieldException {
        DenProcess denProcess = new DenProcess();
        denProcess.setAnnotation(new Object[]{Student.class.getDeclaredField("id").getAnnotation(Den.class)});
        DenProcess denProcess2 = new DenProcess();
        denProcess.setAnnotation(new Object[]{ClassRoom.class.getDeclaredField("id").getAnnotation(Den.class)});
        DenProcess denProcess3 = new DenProcess();
        denProcess.setAnnotation(new Object[]{Role.class.getDeclaredField("id").getAnnotation(Den.class)});
        return Arrays.asList(new Object[][] {
                {
                        denProcess, "[COLUMN_NAME] [COLUMN_TYPE] AUTO_INCREMENT {ATTRIBUTE< >} {OTHER_ATTRIBUTE<,>}","[COLUMN_NAME] [COLUMN_TYPE] IDENTITY(1,1) {ATTRIBUTE< >} {OTHER_ATTRIBUTE<,>}"
                },{
                        denProcess2, "[COLUMN_NAME] [COLUMN_TYPE] AUTO_INCREMENT {ATTRIBUTE< >} {OTHER_ATTRIBUTE<,>}","[COLUMN_NAME] [COLUMN_TYPE] IDENTITY(1,1) {ATTRIBUTE< >} {OTHER_ATTRIBUTE<,>}"
                },{
                        denProcess3, "[COLUMN_NAME] [COLUMN_TYPE] AUTO_INCREMENT {ATTRIBUTE< >} {OTHER_ATTRIBUTE<,>}","[COLUMN_NAME] [COLUMN_TYPE] IDENTITY(1,1) {ATTRIBUTE< >} {OTHER_ATTRIBUTE<,>}"
                }
        });
    }
    @BeforeClass
    public static void initialize() {
        System.out.println("DenProcessTest is running");
    }

    @Test
    public void create() throws MySQLNotSupportException {
        String rs2 = crudColumnAnnotation.create(COLUMN_CREATE_QUERY);
        if(rs2.equals(rs)|| rs2.equals(tmpRs))
        Assert.assertTrue(true);else{
            Assert.assertTrue(false);
        }
    }
    @AfterClass
    public static void end(){
        System.out.println("DenProcessTest stoped");

    }
}
