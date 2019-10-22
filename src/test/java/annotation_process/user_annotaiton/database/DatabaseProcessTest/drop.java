package annotation_process.user_annotaiton.database.DatabaseProcessTest;

import DATA.SET1.main;
import annotation_process.user_annotaiton.database.Database;
import annotation_process.user_annotaiton.database.DatabaseProcess;
import inter.CRUDDatabaseAnnotation;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static inter.SQLContrains.DB_CREATE_QUERY;
import static inter.SQLContrains.DB_DROP_QUERY;

@RunWith(Parameterized.class)
public class drop {
    private CRUDDatabaseAnnotation crudDatabaseAnnotation;
    private String rs;
    public drop(CRUDDatabaseAnnotation crudDatabaseAnnotation, String rs){
        this.crudDatabaseAnnotation = crudDatabaseAnnotation;
        this.rs = rs;
    }
    @Parameterized.Parameters
    public static Collection paramater() throws NoSuchFieldException {
        DatabaseProcess databaseProcess = new DatabaseProcess();
        databaseProcess.setAnnotation(new Object[]{main.class.getAnnotation(Database.class)});
        return Arrays.asList(new Object[][] {
                {
                        databaseProcess, "DROP DATABASE Demo1"
                }
        });
    }
    @BeforeClass
    public static void initialize() {
        System.out.println("DatabaseProcessTest:drop is running...");
    }

    @Test
    public void create() {
        Assert.assertEquals(crudDatabaseAnnotation.drop(DB_DROP_QUERY),rs);
    }
    @AfterClass
    public static void end(){
        System.out.println("DatabaseProcessTest:drop stoped");
    }
}
