package jpa.resource.model;

import java.util.HashMap;
import jpa.resource.model.ColumnResource;
import jpa.resource.model.TableResource;
import java.util.Map;import static jpa.resource.model.CO.*;
public class TA {
        final static public int ROLE = 2147483646;
     final static public int CLASSROOM = 2147483645;
     final static public int DEMO = 2147483644;
     final static public int STUDENT = 2147483643;
    public static Map<Integer, TableResource> id = new HashMap<Integer,TableResource>();
static{
id.put(ROLE,new TableResource(jpa.example.Role.class,"Role", new int[]{ROLE_id,ROLE_name}));
 id.put(CLASSROOM,new TableResource(jpa.example.ClassRoom.class,"class_room", new int[]{CLASSROOM_id,CLASSROOM_name,CLASSROOM_numberOfSeats}));
 id.put(DEMO,new TableResource(jpa.example.Demo.class,"Demo", new int[]{DEMO_id}));
 id.put(STUDENT,new TableResource(jpa.example.Student.class,"student", new int[]{STUDENT_id,STUDENT_name,STUDENT_birdDate,STUDENT_point,STUDENT_studentCode,STUDENT_classRoom,STUDENT_role}));
    }}
