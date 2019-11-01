package jpa.resource.model;
import java.util.HashMap;
import jpa.resource.model.TableResource;
import java.util.Map;
import jpa.resource.model.ColumnResource;
public class CO {
    final static public int ROLE_id = 1;
     final static public int ROLE_name = 2;
     final static public int CLASSROOM_id = 3;
     final static public int CLASSROOM_name = 4;
     final static public int CLASSROOM_numberOfSeats = 5;
     final static public int DEMO_id = 6;
     final static public int STUDENT_id = 7;
     final static public int STUDENT_name = 8;
     final static public int STUDENT_birdDate = 9;
     final static public int STUDENT_point = 10;
     final static public int STUDENT_studentCode = 11;
     final static public int STUDENT_classRoom = 12;
     final static public int STUDENT_role = 13;
public static Map<Integer, ColumnResource> id = new HashMap<Integer,ColumnResource>();
static{
        try {
            id.put(ROLE_id,new ColumnResource(TA.id.get(TA.ROLE),jpa.example.Role.class.getDeclaredField("id"),"id","int",null));
 id.put(ROLE_name,new ColumnResource(TA.id.get(TA.ROLE),jpa.example.Role.class.getDeclaredField("name"),"name","varchar(255)",null));
 id.put(CLASSROOM_id,new ColumnResource(TA.id.get(TA.CLASSROOM),jpa.example.ClassRoom.class.getDeclaredField("id"),"id","int",null));
 id.put(CLASSROOM_name,new ColumnResource(TA.id.get(TA.CLASSROOM),jpa.example.ClassRoom.class.getDeclaredField("name"),"name","varchar(400)",null));
 id.put(CLASSROOM_numberOfSeats,new ColumnResource(TA.id.get(TA.CLASSROOM),jpa.example.ClassRoom.class.getDeclaredField("numberOfSeats"),"numberOfSeats","long",null));
 id.put(DEMO_id,new ColumnResource(TA.id.get(TA.DEMO),jpa.example.Demo.class.getDeclaredField("id"),"id","int",null));
 id.put(STUDENT_id,new ColumnResource(TA.id.get(TA.STUDENT),jpa.example.Student.class.getDeclaredField("id"),"id","int",null));
 id.put(STUDENT_name,new ColumnResource(TA.id.get(TA.STUDENT),jpa.example.Student.class.getDeclaredField("name"),"name","varchar(255)",null));
 id.put(STUDENT_birdDate,new ColumnResource(TA.id.get(TA.STUDENT),jpa.example.Student.class.getDeclaredField("birdDate"),"bird_date","date",null));
 id.put(STUDENT_point,new ColumnResource(TA.id.get(TA.STUDENT),jpa.example.Student.class.getDeclaredField("point"),"point","float",null));
 id.put(STUDENT_studentCode,new ColumnResource(TA.id.get(TA.STUDENT),jpa.example.Student.class.getDeclaredField("studentCode"),"student_code","long",null));
 id.put(STUDENT_classRoom,new ColumnResource(TA.id.get(TA.STUDENT),jpa.example.Student.class.getDeclaredField("classRoom"),"room_id","int",new jpa.query_data.model.Relationship(12,3)));
 id.put(STUDENT_role,new ColumnResource(TA.id.get(TA.STUDENT),jpa.example.Student.class.getDeclaredField("role"),"role_id","int",new jpa.query_data.model.Relationship(13,1)));

            } catch (Exception e) {
            e.printStackTrace();
        }

    }}
