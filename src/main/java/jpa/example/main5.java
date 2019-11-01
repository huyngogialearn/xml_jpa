package jpa.example;

import jpa.Hello;
import jpa.annotation_process.user_annotaiton.query.Controller;
import jpa.exception.MySQLNotSupportException;
import jpa.start_framework.GenerateResource;

import java.io.IOException;

@Controller
public class main5 {
    public static String CO_FORM =
            "package jpa.resource.model;\n"+
                    "import java.util.HashMap;\n" +
                    "import java.util.Map;\n"+
            "public class CO {\n" +
            "{VARIABLES< >}" +
            "    public static Map<Integer, ColumnResource> id = new HashMap<Integer,ColumnResource>();\n" +
            "\n" +
            "static{\n" +
                    "        try {\n" +
                    "            {INIT_COLUMN< >}\n" +
                    "            } catch (Exception e) {\n" +
                    "            e.printStackTrace();\n" +
                    "        }\n" +
                    "\n" +
                    "    }"+
            "}\n";
    public static String INIT_COLUMN_SUB_QUERY = "id.put([VARIABLE_NAME],new ColumnResource(TA.id.get(TA.[TABLE_NAME]),[CLASS_NAME].class.getDeclaredField(\"[FIELD_NAME]\"),\"[SQL_NAME]\",\"[SQL_TYPE]\",[RELATIONSHIP]));";
    public static String TABLE_NAME_KEY = "TABLE_NAME";
    public static String CLASSS_NAME_KEY = "CLASS_NAME";
    public static String FIELD_NAME_KEY = "FIELD_NAME";
    public static String SQL_NAME_KEY = "SQL_NAME";
    public static String INIT_COLUMN_KEY = "INIT_COLUMN";
    public static String SQL_TYPE_KEY = "SQL_TYPE";
    public static String RELATIONSHIP_KEY = "RELATIONSHIP";
    public static String RELATIONSHIP_SUB_FORM = "new jpa.query_data.model.Relationship([FOREIGN_KEY],[REFERENT_COLUMN])";
    public static String FOREIGN_KEY_KEY = "FOREIGN_KEY";
    public static String REFERENT_COLUMN_KEY = "REFERENT_COLUMN";

    public static String TA_FORM =
            "public class TA {\n" +
            "    {VARIABLES< >}" +
            "    public static Map<Integer, TableResource> id = new HashMap<Integer,TableResource>();\n" +
            "}\n";
    public static String VARIABLE_KEY = "VARIABLES";
    public static String VARIABLE_NAME_KEY = "VARIABLE_NAME";
    public static String VARIABLE_VALUE_KEY = "VARIABLE_VALUE";
    public static String VARIABLE_SUB_FORM = "    final static public int [VARIABLE_NAME] = [VARIABLE_VALUE];\n";




    public static void main(String[] args) throws IllegalAccessException, InstantiationException, MySQLNotSupportException, IOException, ClassNotFoundException {
        GenerateResource generateResource = new GenerateResource(Hello.class);
        generateResource.write();
//        Map<Integer,String> tableName = new HashMap<>();
//        Map<Integer,String> columnName = new HashMap<>();
//        List<String>  initColumns = new ArrayList<>();;
//        TALogic taLogic = new TALogic();
//        COLogic logic = new COLogic();
//        AnnotationUltil annotationUltil = new AnnotationUltil();
//        Class[] tableAnnotaiton = new Class[]{Student.class,Role.class, ClassRoom.class};
//        QueryBuilderLogic queryBuilderLogic = new QueryBuilderLogic();
//        int count = 0;
//        System.out.println(tableAnnotaiton.length);
//        for(Class aClass:tableAnnotaiton){
//            tableName.put(count,aClass.getSimpleName().toUpperCase());
//            taLogic.getTableResource(count++,aClass);
//            for (Field field: aClass.getDeclaredFields()){
//                if(field.getAnnotation(IsColumn.class) != null){
//                    columnName.put(count,aClass.getSimpleName().toUpperCase()+"_"+field.getName());
//                    String relationship;
//                    ColumnResource re =logic.getColumn(count++,aClass,field);
//                    if(re.getRelationship() == null)  relationship="null";else
//                        relationship=  queryBuilderLogic.processQueryBuilderForm(RELATIONSHIP_SUB_FORM,new QueryBuider[]{
//                                new QueryBuider(QueryBuilderType.ONE,REFERENT_COLUMN_KEY,re.getRelationship().getReFr()+""),
//                                new QueryBuider(QueryBuilderType.ONE,FOREIGN_KEY_KEY,re.getRelationship().getReTo()+""),
//
//                    });
//                    initColumns.add(queryBuilderLogic.processQueryBuilderForm(INIT_COLUMN_SUB_QUERY,new QueryBuider[]{
//                            new QueryBuider(QueryBuilderType.ONE,VARIABLE_NAME_KEY,aClass.getSimpleName().toUpperCase()+"_"+field.getName()),
//                            new QueryBuider(QueryBuilderType.ONE,TABLE_NAME_KEY,aClass.getSimpleName().toUpperCase()),
//                            new QueryBuider(QueryBuilderType.ONE,CLASSS_NAME_KEY,re.getTable().getTable().getName()),
//                            new QueryBuider(QueryBuilderType.ONE,FIELD_NAME_KEY,re.getField().getName()),
//                            new QueryBuider(QueryBuilderType.ONE,SQL_NAME_KEY,re.getSqlName()),
//                            new QueryBuider(QueryBuilderType.ONE,SQL_TYPE_KEY,re.getSqlType()),
//                            new QueryBuider(QueryBuilderType.ONE,RELATIONSHIP_KEY,relationship)
//                    }));
//                }
//            }
//        }
//        String variableName, variableValue, subForm, form = CO_FORM;
//        for(Map.Entry<Integer, String> entry : columnName.entrySet()) {
//            variableName = entry.getValue();
//            variableValue = entry.getKey()+"";
//            subForm = VARIABLE_SUB_FORM;
//            subForm = queryBuilderLogic.processQueryBuilderForm(subForm,new QueryBuider[]{
//                    new QueryBuider(QueryBuilderType.ONE,VARIABLE_NAME_KEY,variableName),
//                    new QueryBuider(QueryBuilderType.ONE,VARIABLE_VALUE_KEY,variableValue),
//            });
//            form = queryBuilderLogic.processQueryBuilderForm(form,new QueryBuider(QueryBuilderType.COMMA,VARIABLE_KEY,subForm));
//        }
//        for (String initCol: initColumns){
//            form = queryBuilderLogic.processQueryBuilderForm(form,new QueryBuider(QueryBuilderType.COMMA,INIT_COLUMN_KEY,initCol+"\n"));
//        }
//        try {
//            writeCO(queryBuilderLogic.cleanQueryBuilderForm(form));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println(queryBuilderLogic.cleanQueryBuilderForm(form));
    }
    public static void writeCO(String form) throws IOException {
        final String dir = System.getProperty("user.dir");
        System.out.println("current dir = " + dir);
//        FileWriter fw = new FileWriter(dir+"/src/main/java/jpa/resource/model/CO.java" ,true);
//        fw.write(form);
//        fw.close();


    }
}
