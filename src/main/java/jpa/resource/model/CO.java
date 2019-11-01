package jpa.resource.model;
import java.util.HashMap;
import jpa.resource.model.TableResource;
import java.util.Map;
import jpa.resource.model.ColumnResource;
public class CO {
    final static public int ROLE_id = 1;
     final static public int ROLE_name = 2;
     final static public int STUDENT_id = 3;
     final static public int STUDENT_name = 4;
     final static public int STUDENT_role = 5;
public static Map<Integer, ColumnResource> id = new HashMap<Integer,ColumnResource>();
static{
        try {
            id.put(ROLE_id,new ColumnResource(TA.id.get(TA.ROLE),com.company.Role.class.getDeclaredField("id"),"id","int",null));
 id.put(ROLE_name,new ColumnResource(TA.id.get(TA.ROLE),com.company.Role.class.getDeclaredField("name"),"name","varchar(255)",null));
 id.put(STUDENT_id,new ColumnResource(TA.id.get(TA.STUDENT),com.company.Student.class.getDeclaredField("id"),"id","int",null));
 id.put(STUDENT_name,new ColumnResource(TA.id.get(TA.STUDENT),com.company.Student.class.getDeclaredField("name"),"name","varchar(255)",null));
 id.put(STUDENT_role,new ColumnResource(TA.id.get(TA.STUDENT),com.company.Student.class.getDeclaredField("role"),"role","int",new jpa.query_data.model.Relationship(1,5)));

            } catch (Exception e) {
            e.printStackTrace();
        }

    }}
