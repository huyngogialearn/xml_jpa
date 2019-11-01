package jpa.ultil.logic;

import jpa.exception.MySQLNotSupportException;

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
    }
    public String convertSQLTypeToJava(Class clazz,String  length) throws MySQLNotSupportException {
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
    }
    public String convertSQLFormat(Class clazz,Object value) throws MySQLNotSupportException {
        System.out.println(clazz);
        if(clazz.equals(String.class)){
            if(value == null) return "";
            return "'"+value+"'";
        }
        if(clazz.equals(Long.class)||clazz.equals(long.class)||
                clazz.equals(Float.class)||clazz.equals(float.class)||
                clazz.equals(Short.class)||clazz.equals(short.class)||
                clazz.equals(Integer.class)||clazz.equals(int.class)||
                clazz.equals(Double.class)||clazz.equals(double.class)

        ){
            if(value == null) return "0";
        }
        if(clazz.equals(Date.class)||clazz.equals(Time.class)||clazz.equals(Timestamp.class)){
            if(value == null) return "null";
            return "'"+value.toString()+"'";

        }
        return value+"";
//        throw new MySQLNotSupportException(clazz+" is not supported, please change to other type");
    }
}
