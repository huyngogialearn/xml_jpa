package logic.TypeConvertTest;

import exception.MySQLNotSupportException;
import logic.TypeConvert;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class convertJavaToSQLType {
    static TypeConvert typeConvert;
    Class clazz;
    int length;
    String rs;

    public convertJavaToSQLType(Class clazz, int length, String rs) {
        this.clazz = clazz;
        this.length = length;
        this.rs = rs;
    }

    @Parameterized.Parameters
    public static Collection paramater() {
        return Arrays.asList(new Object[][] {
                {String.class, 300, "varchar(300)"},
                {Long.class, 300, "long"},
                {long.class, 300, "long"},
                {Timestamp.class, 300, "timestamp"},
                {Short.class, 300, "int"},
                {Date.class, 300, "date"},
                {int.class, 300, "int"},
                {double.class, 300, "double"}
        });
    }
    @BeforeClass
    public static void initialize() {
        typeConvert = new TypeConvert();
    }

    @Test
    public void convertJavaToSQLType() throws MySQLNotSupportException {
        Assert.assertEquals(typeConvert.convertJavaToSQLType(clazz,length),rs);
    }
    @AfterClass
    public static void end(){
    }
}
