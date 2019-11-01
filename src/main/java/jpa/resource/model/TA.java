package jpa.resource.model;

import java.util.HashMap;
import jpa.resource.model.ColumnResource;
import jpa.resource.model.TableResource;
import java.util.Map;import static jpa.resource.model.CO.*;
public class TA {
        final static public int ROLE = 2147483646;
     final static public int STUDENT = 2147483645;
    public static Map<Integer, TableResource> id = new HashMap<Integer,TableResource>();
static{
id.put(ROLE,new TableResource(com.company.Role.class,"Role", new int[]{ROLE_id,ROLE_name}));
 id.put(STUDENT,new TableResource(com.company.Student.class,"student", new int[]{STUDENT_id,STUDENT_name,STUDENT_role}));
    }}
