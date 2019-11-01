package jpa.ultil.logic;

import jpa.exception.MySQLNotSupportException;
import jpa.query_data.model.Relationship;
import jpa.resource.model.CO;
import jpa.resource.model.ColumnResource;

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
        throw new MySQLNotSupportException();
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
    public String convertSQLFormat(Class clazz,Object value,int ids) throws MySQLNotSupportException {
        if(clazz.equals(String.class)){
            if(value == null) return "null";
            return "'"+value+"'";
        }
        if(clazz.equals(Long.class)||clazz.equals(long.class)||
                clazz.equals(Float.class)||clazz.equals(float.class)||
                clazz.equals(Short.class)||clazz.equals(short.class)||
                clazz.equals(Integer.class)||clazz.equals(int.class)||
                clazz.equals(Double.class)||clazz.equals(double.class)

        ){
            if(value == null) return "0"; else return value+"";
        }
        if(clazz.equals(Date.class)||clazz.equals(Time.class)||clazz.equals(Timestamp.class)){
            if(value == null) return "null";
            return "'"+value.toString()+"'";

        }
        if(ids < 0 ) throw new MySQLNotSupportException(clazz+" is not supported, please change to other type");
        ColumnResource columnResourceTmp = CO.id.get(ids);
        if(columnResourceTmp.getRelationship() != null){
            Relationship relationshipTmp = columnResourceTmp.getRelationship();
            columnResourceTmp=CO.id.get(relationshipTmp.getReFr());
            try {
                columnResourceTmp.getField().setAccessible(true);

                return convertSQLFormat(columnResourceTmp.getField().getType(),(value == null)?value:columnResourceTmp.getField().get(value),-1);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
        throw new MySQLNotSupportException(clazz+" is not supported, please change to other type");
    }
}
