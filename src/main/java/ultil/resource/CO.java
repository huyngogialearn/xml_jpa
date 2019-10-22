package ultil.resource;

import model.ColumnResource;
import model.TableResource;
import ultil.resource.example.Student;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CO {
    final static public int student_id = 14;

    final static public int student_name = 1;
    final static public int student_bird_date = 1;
    final static public int student_point = 2;
    final static public int student_student_code = 3;
    final static public int student = 8;
    final static public int student_classRoom = 10;

    final static public int class_room_id = 4;
    final static public int class_room_name = 5;
    final static public int class_room_numberOfSeats= 6;
    final static public int class_room = 7;
    final static public int class_room_students = 9;

    final static public int role_students = 12;
    final static public int student_role = 13;
    final static public int role_id = 14;
    final static public int role_name = 15;
    final static public int student_role_id = -1;
    final static public int student_room_id = -2;


    public static Map<Integer, ColumnResource> id = new HashMap<Integer,ColumnResource>();
    static{
        try {
            id.put(student_room_id, new ColumnResource(new TableResource(null,"student"),Student.class.getDeclaredField("classRoom"),"room_id","int",null));
            id.put(student_role_id,new ColumnResource(new TableResource(null,"student"),Student.class.getDeclaredField("role"),"role_id","int",null));
            id.put(student_id,new ColumnResource(new TableResource(null,"student"),Student.class.getDeclaredField("id"),"id","int",null));
            id.put(class_room_id,new ColumnResource(new TableResource(null,"class_room"),Student.class.getDeclaredField("id"),"id","int",null));
            id.put(role_id,new ColumnResource(new TableResource(null,"Role"),Student.class.getDeclaredField("id"),"id","int",null));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

    }

}
