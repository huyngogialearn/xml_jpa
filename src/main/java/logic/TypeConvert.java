package logic;

import exception.MySQLNotSupportException;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class TypeConvert {
    public String convertJavaToSQLType(Class clazz,int length) throws MySQLNotSupportException {
        if(clazz.equals(String.class)){
            return "varchar("+length+")";
        }
        if(clazz.equals(Long.class)||clazz.equals(long.class)){
            return "long";
        }
        if(clazz.equals(Float.class)||clazz.equals(float.class)){
            return "float";
        }
        if(clazz.equals(Short.class)||clazz.equals(short.class)){
            return "int";
        }
        if(clazz.equals(Integer.class)||clazz.equals(int.class)){
            return "int";
        }
        if(clazz.equals(Double.class)||clazz.equals(double.class)){
            return "double";
        }
        if(clazz.equals(Date.class)){
            return "date";
        }
        if(clazz.equals(Time.class)){
            return "time";
        }
        if(clazz.equals(Timestamp.class)){
            return "timestamp";
        }
        return "int";
//        throw new MySQLNotSupportException(clazz+" is not supported, please change to other type");
    }
}
