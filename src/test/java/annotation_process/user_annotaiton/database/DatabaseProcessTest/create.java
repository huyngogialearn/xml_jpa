package annotation_process.user_annotaiton.database.DatabaseProcessTest;

import DATA.SET1.ClassRoom;
import DATA.SET1.Student;
import DATA.SET1.main;

import annotation_process.user_annotaiton.column.Null;
import annotation_process.user_annotaiton.column.NullProcess;
import annotation_process.user_annotaiton.database.Database;
import annotation_process.user_annotaiton.database.DatabaseProcess;
import inter.CRUDColumnAnnotation;
import inter.CRUDDatabaseAnnotation;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static inter.SQLContrains.COLUMN_CREATE_QUERY;
import static inter.SQLContrains.DB_CREATE_QUERY;

@RunWith(Parameterized.class)
public class create {
    private CRUDDatabaseAnnotation crudDatabaseAnnotation;
    private String rs;
    public create(CRUDDatabaseAnnotation crudDatabaseAnnotation, String rs){
        this.crudDatabaseAnnotation = crudDatabaseAnnotation;
        this.rs = rs;
    }
    @Parameterized.Parameters
    public static Collection paramater() throws NoSuchFieldException {
        DatabaseProcess databaseProcess = new DatabaseProcess();
        databaseProcess.setAnnotation(new Object[]{main.class.getAnnotation(Database.class)});
        return Arrays.asList(new Object[][] {
                {
                        databaseProcess, "CREATE DATABASE Demo1"
                }
        });
    }
    @BeforeClass
    public static void initialize() {
        System.out.println("DatabaseProcessTest:create is running...");
    }

    @Test
    public void create() {
        Assert.assertEquals(crudDatabaseAnnotation.create(DB_CREATE_QUERY),rs);
    }
    @AfterClass
    public static void end(){
        System.out.println("DatabaseProcessTest:create stoped...");

    }

}
