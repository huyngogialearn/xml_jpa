package jpa.start_framework;

import jpa.annotation_process.register_annotation.RegisterProcess;
import jpa.annotation_process.user_annotaiton.column.IsColumn;
import jpa.annotation_process.user_annotaiton.table.IsTable;
import jpa.e.QueryBuilderType;
import jpa.inter.CRUDColumnAnnotation;
import jpa.inter.CRUDTableAnnotation;
import jpa.resource.model.ColumnResource;
import jpa.resource.model.TableResource;
import jpa.ultil.logic.AnnotationUltil;
import jpa.ultil.logic.QueryBuilderLogic;
import jpa.ultil.model.QueryBuider;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class GenerateResource {
    Class root;

    public GenerateResource(Class root) {
        this.root = root;
    }

    private final String CO_FORM =
            "package [PACKAGE_NAME];\n" +
                    "import java.util.HashMap;\n" +
                    "import jpa.resource.model.TableResource;\n" +
                    "import java.util.Map;\n" +
                    "import jpa.resource.model.ColumnResource;\n" +
                    "public class CO {\n" +
                    "{VARIABLES< >}" +
                    "public static Map<Integer, ColumnResource> id = new HashMap<Integer,ColumnResource>();\n" +
                    "static{\n" +
                    "        try {\n" +
                    "            {INIT_COLUMN< >}\n" +
                    "            } catch (Exception e) {\n" +
                    "            e.printStackTrace();\n" +
                    "        }\n" +
                    "\n" +
                    "    }" +
                    "}\n";
    private final String INIT_COLUMN_SUB_QUERY = "id.put([VARIABLE_NAME],new ColumnResource(TA.id.get(TA.[TABLE_NAME]),[CLASS_NAME].class.getDeclaredField(\"[FIELD_NAME]\"),\"[SQL_NAME]\",\"[SQL_TYPE]\",[RELATIONSHIP]));";
    private final String TABLE_NAME_KEY = "TABLE_NAME";
    private final String CLASSS_NAME_KEY = "CLASS_NAME";
    private final String FIELD_NAME_KEY = "FIELD_NAME";
    private final String SQL_NAME_KEY = "SQL_NAME";
    private final String INIT_COLUMN_KEY = "INIT_COLUMN";
    private final String SQL_TYPE_KEY = "SQL_TYPE";
    private final String RELATIONSHIP_KEY = "RELATIONSHIP";
    private final String RELATIONSHIP_SUB_FORM = "new jpa.query_data.model.Relationship([REFERENT_COLUMN],[FOREIGN_KEY])";
    private final String FOREIGN_KEY_KEY = "FOREIGN_KEY";
    private final String REFERENT_COLUMN_KEY = "REFERENT_COLUMN";


    private final String TA_FORM =
            "package [PACKAGE_NAME];\n" +
                    "\n" +
                    "import java.util.HashMap;\n" +
                    "import jpa.resource.model.ColumnResource;\n" +
                    "import jpa.resource.model.TableResource;\n" +
                    "import java.util.Map;" +
                    "import static jpa.resource.model.CO.*;\n" +
                    "public class TA {\n" +
                    "    {VARIABLES< >}" +
                    "    public static Map<Integer, TableResource> id = new HashMap<Integer,TableResource>();\n" +
                    "static{\n" +
                    "{INIT_TABLE_SUB_QUERY< >}" +
                    "    }" +
                    "}\n";
    private final String VARIABLE_KEY = "VARIABLES";
    private final String VARIABLE_NAME_KEY = "VARIABLE_NAME";
    private final String VARIABLE_VALUE_KEY = "VARIABLE_VALUE";
    private final String VARIABLE_SUB_FORM = "    final static public int [VARIABLE_NAME] = [VARIABLE_VALUE];\n";
    private final String INIT_TABLE_SUB_QUERY = "id.put([VARIABLE_NAME],new TableResource([CLASS_NAME].class,\"[TABLE_NAME]\", new int[]{{VARIABLE_COLUMN_NAME<,>}}));";
    private final String INIT_TABLE_KEY = "INIT_TABLE_SUB_QUERY";
    private final String VARIABLE_COLUMN_NAME_KEY = "VARIABLE_COLUMN_NAME";
    private final String PACKAGE_NAME_KEY = "PACKAGE_NAME";

    public String getResourceCO(String packageName) throws InstantiationException, IllegalAccessException, IOException, ClassNotFoundException {
        //processing tools
        QueryBuilderLogic queryBuilderLogic = new QueryBuilderLogic();
        AnnotationUltil annotationUltil = new AnnotationUltil();

        //tmp variable
        ColumnResource columnResourceTmp;

        //name of the column, name of the table, relationship string ( jpa.query_data.model.Relationship([FOREIGN_KEY],[REFERENT_COLUMN])), parent form
        String nameColumn, tableName, relationshipValue, variableSubForm, parentCOForm = CO_FORM;

        //list of variable | [[VARIABLE_NAME] = [VARIABLE_VALUE];]
        List<String> variableSubForms = new ArrayList<>();

        //list of new resource | id.put([VARIABLE_NAME],new ColumnResource(TA.id.get(TA.[TABLE_NAME]),[CLASS_NAME].class.getDeclaredField("[FIELD_NAME]"),"[SQL_NAME]","[SQL_TYPE]",[RELATIONSHIP]));
        List<String> initColumns = new ArrayList<>();
        ;

        //models
        Class[] tableAnnotaiton = null;

        //ids of column
        int idColumn = 0;

        //get all model
        tableAnnotaiton = annotationUltil.scanAnnotations(IsTable.class, root);
        for (Class aClass : tableAnnotaiton) {

            //lowert to UPPERT | table1 -> TABLE1
            tableName = aClass.getSimpleName().toUpperCase();
            for (Field field : aClass.getDeclaredFields()) {
                if (field.getAnnotation(IsColumn.class) != null) {
                    //create Column resrouce
                    columnResourceTmp = getColumn(idColumn++, aClass, field);
                    //process {VARIABLES< >}
                    nameColumn = aClass.getSimpleName().toUpperCase() + "_" + field.getName();
                    variableSubForm = VARIABLE_SUB_FORM;
                    variableSubForm = queryBuilderLogic.processQueryBuilderForm(variableSubForm, new QueryBuider[]{
                            new QueryBuider(QueryBuilderType.ONE, VARIABLE_NAME_KEY, nameColumn),
                            new QueryBuider(QueryBuilderType.ONE, VARIABLE_VALUE_KEY, idColumn + ""),
                    });
                    variableSubForms.add(variableSubForm + "");
                    //  process [RELATIONSHIP]
                    if (columnResourceTmp.getRelationship() == null)
                        relationshipValue = "null";
                    else
                        relationshipValue = queryBuilderLogic.processQueryBuilderForm(RELATIONSHIP_SUB_FORM, new QueryBuider[]{
                                new QueryBuider(QueryBuilderType.ONE, REFERENT_COLUMN_KEY, columnResourceTmp.getRelationship().getReFr() + ""),
                                new QueryBuider(QueryBuilderType.ONE, FOREIGN_KEY_KEY, columnResourceTmp.getRelationship().getReTo() + ""),

                        });
                    //proccess {INIT_COLUMN< >}
                    initColumns.add(queryBuilderLogic.processQueryBuilderForm(INIT_COLUMN_SUB_QUERY, new QueryBuider[]{
                            new QueryBuider(QueryBuilderType.ONE, VARIABLE_NAME_KEY, nameColumn),
                            new QueryBuider(QueryBuilderType.ONE, TABLE_NAME_KEY, tableName),
                            new QueryBuider(QueryBuilderType.ONE, CLASSS_NAME_KEY, columnResourceTmp.getTable().getTable().getName()),
                            new QueryBuider(QueryBuilderType.ONE, FIELD_NAME_KEY, columnResourceTmp.getField().getName()),
                            new QueryBuider(QueryBuilderType.ONE, SQL_NAME_KEY, columnResourceTmp.getSqlName()),
                            new QueryBuider(QueryBuilderType.ONE, SQL_TYPE_KEY, columnResourceTmp.getSqlType()),
                            new QueryBuider(QueryBuilderType.ONE, RELATIONSHIP_KEY, relationshipValue)
                    }));
                }
            }
        }

        //process public class CO {\n" +
        //                    "{VARIABLES< >}" +
        //                    "    public static
        for (String idColumnNameColumn : variableSubForms) {
            parentCOForm = queryBuilderLogic.processQueryBuilderForm(parentCOForm, new QueryBuider(QueryBuilderType.COMMA, VARIABLE_KEY, idColumnNameColumn));
        }
        //process  try {\n" +
        //                    "            {INIT_COLUMN< >}\n" +
        //                    "            } catch (Exception e)
        for (String initCol : initColumns) {
            parentCOForm = queryBuilderLogic.processQueryBuilderForm(parentCOForm, new QueryBuider(QueryBuilderType.COMMA, INIT_COLUMN_KEY, initCol + "\n"));
        }
        parentCOForm = queryBuilderLogic.processQueryBuilderForm(parentCOForm, new QueryBuider(QueryBuilderType.ONE, PACKAGE_NAME_KEY, packageName));
        return queryBuilderLogic.cleanQueryBuilderForm(parentCOForm);

    }

    public String getResourceTA(String packageName) throws InstantiationException, IllegalAccessException, IOException, ClassNotFoundException {
        //processing tools
        AnnotationUltil annotationUltil = new AnnotationUltil();
        QueryBuilderLogic queryBuilderLogic = new QueryBuilderLogic();

        //tmp variable
        TableResource tableResourceTmp;

        //name of the column, name of the table, sub-form VARIABLE_SUB_FORM, sub-form INIT_TABLE_SUB_QUERY
        String nameColumn, tableName, variableSubForm, initTableSubForm, parentTAForm = TA_FORM;

        //list of variableSubForm | inal static public int [VARIABLE_NAME] = [VARIABLE_VALUE];
        List<String> initTable = new ArrayList<>();
        ;

        //list of |id.put([VARIABLE_NAME],new TableResource([CLASS_NAME].class,"[TABLE_NAME]", new int[]{{VARIABLE_COLUMN_NAME<,>}}))
        List<String> idColumnNameTable = new ArrayList<>();
        //get all model
        Class[] tableAnnotaiton = annotationUltil.scanAnnotations(IsTable.class, root);
        int idColumn = Integer.MAX_VALUE;
        for (Class aClass : tableAnnotaiton) {
            //get name of table
            tableName = aClass.getSimpleName().toUpperCase();
            //get resource of table
            tableResourceTmp = getTableResource(idColumn--, aClass);

            //process   final static public int [VARIABLE_NAME] = [VARIABLE_VALUE]
            variableSubForm = VARIABLE_SUB_FORM;
            variableSubForm = queryBuilderLogic.processQueryBuilderForm(variableSubForm, new QueryBuider[]{
                    new QueryBuider(QueryBuilderType.ONE, VARIABLE_NAME_KEY, tableName),
                    new QueryBuider(QueryBuilderType.ONE, VARIABLE_VALUE_KEY, idColumn + ""),
            });
            initTableSubForm = INIT_TABLE_SUB_QUERY;
            initTable.add(variableSubForm);
            for (Field field : aClass.getDeclaredFields()) {
                if (field.getAnnotation(IsColumn.class) != null) {
                    //name of column
                    nameColumn = aClass.getSimpleName().toUpperCase() + "_" + field.getName();
                    initTableSubForm = queryBuilderLogic.processQueryBuilderForm(initTableSubForm, new QueryBuider(QueryBuilderType.COMMA, VARIABLE_COLUMN_NAME_KEY, nameColumn));
                }
            }
            initTableSubForm = queryBuilderLogic.processQueryBuilderForm(initTableSubForm, new QueryBuider[]{
                    new QueryBuider(QueryBuilderType.ONE, VARIABLE_NAME_KEY, tableName),
                    new QueryBuider(QueryBuilderType.ONE, CLASSS_NAME_KEY, tableResourceTmp.getTable().getName()),
                    new QueryBuider(QueryBuilderType.ONE, TABLE_NAME_KEY, tableResourceTmp.getSqlName()),
            });
            idColumnNameTable.add(queryBuilderLogic.cleanQueryBuilderForm(initTableSubForm));
        }
        //process p
        for (String idColumnNameColumn : initTable) {
            parentTAForm = queryBuilderLogic.processQueryBuilderForm(parentTAForm, new QueryBuider(QueryBuilderType.COMMA, VARIABLE_KEY, idColumnNameColumn));
        }
        for (String idCol : idColumnNameTable) {
            parentTAForm = queryBuilderLogic.processQueryBuilderForm(parentTAForm, new QueryBuider(QueryBuilderType.COMMA, INIT_TABLE_KEY, idCol + "\n"));
        }
        parentTAForm = queryBuilderLogic.processQueryBuilderForm(parentTAForm, new QueryBuider(QueryBuilderType.ONE, PACKAGE_NAME_KEY, packageName));
        return queryBuilderLogic.cleanQueryBuilderForm(parentTAForm);

    }

    private TableResource getTableResource(int id, Class aClass) throws IllegalAccessException, InstantiationException {
        Class<CRUDTableAnnotation> crudTableAnnotationClass = null;
        CRUDTableAnnotation crudTableAnnotation;
        RegisterProcess registerProcess;
        TableResource tableResource = new TableResource(aClass);
        for (Annotation annotation : aClass.getAnnotations()) {
            if (annotation.annotationType().getAnnotation(RegisterProcess.class) != null) {
                registerProcess = annotation.annotationType().getAnnotation(RegisterProcess.class);
                crudTableAnnotationClass = (Class<CRUDTableAnnotation>) registerProcess.process_class();
                crudTableAnnotation = crudTableAnnotationClass.newInstance();
                crudTableAnnotation.setClass(aClass);
                crudTableAnnotation.setAnnotation(new Object[]{annotation});
                crudTableAnnotation.setUpResource(tableResource);
            }
        }
        return tableResource;
    }

    private ColumnResource getColumn(int id, Class tableClass, Field field) throws IllegalAccessException, InstantiationException {
        Class<CRUDColumnAnnotation> crudColumnAnnotationClass = null;
        CRUDColumnAnnotation crudColumnAnnotation;
        RegisterProcess registerProcess;
        ColumnResource columnResource = new ColumnResource(field);
        columnResource.setTable(new TableResource(tableClass));


        for (Annotation annotation : field.getAnnotations()) {
            if (annotation.annotationType().getAnnotation(RegisterProcess.class) != null) {
                registerProcess = annotation.annotationType().getAnnotation(RegisterProcess.class);
                crudColumnAnnotationClass = (Class<CRUDColumnAnnotation>) registerProcess.process_class();
                crudColumnAnnotation = crudColumnAnnotationClass.newInstance();
                crudColumnAnnotation.setAnnotatedField(field);
                crudColumnAnnotation.setAnnotation(new Object[]{annotation});
                crudColumnAnnotation.setUpResource(columnResource);
            }
        }
        return columnResource;
    }

    public void write(String CO, String TA) throws IOException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        final String dir = System.getProperty("user.dir");
        FileWriter fw = new FileWriter("/Users/macbookpro/Documents/Project/Java/XML_JPA/xml_jpa" + "/src/main/java/jpa/resource/model/CO.java", false);
        fw.write(CO);
        fw.close();
        fw = new FileWriter("/Users/macbookpro/Documents/Project/Java/XML_JPA/xml_jpa" + "/src/main/java/jpa/resource/model/TA.java", false);
        fw.write(TA);
        fw.close();
    }

    public void write() throws IOException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        String COClass = getResourceCO("jpa.resource.model");
        String TAClass = getResourceTA("jpa.resource.model");
        write(COClass, TAClass);
    }

    public void clear() throws IOException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        QueryBuilderLogic queryBuilderLogic = new QueryBuilderLogic();
        String COClass = queryBuilderLogic.processQueryBuilderForm(CO_FORM,new QueryBuider(QueryBuilderType.ONE,PACKAGE_NAME_KEY,"jpa.resource.model"));
        String TAClass = queryBuilderLogic.processQueryBuilderForm(TA_FORM,new QueryBuider(QueryBuilderType.ONE,PACKAGE_NAME_KEY,"jpa.resource.model"));
        COClass = queryBuilderLogic.cleanQueryBuilderForm(CO_FORM);
        TAClass = queryBuilderLogic.cleanQueryBuilderForm(TA_FORM);
        write(COClass, TAClass);

    }
}
