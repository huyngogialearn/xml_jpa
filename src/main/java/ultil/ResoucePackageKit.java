package ultil;


import annotation_process.user_annotaiton.column.IsColumn;
import annotation_process.user_annotaiton.column.ManyToOne;
import annotation_process.user_annotaiton.table.IsTable;
import ultil.resource.example.Student;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class ResoucePackageKit {
    public static final String CLASS = "public class [CLASS_NAME] {\n" +
            "{FIELD< >}" +
            "\n}";
    public static final String FIELD = "[ATTRIBUTE] [TYPE] [NAME] [VALUE];\n";
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        AnnotationUltil annotationUltil = new AnnotationUltil();
        int count = 99999;
        for (Class cla:annotationUltil.scanAnnotations(IsTable.class, Student.class)){
                for(Field field:cla.getDeclaredFields()){
                    if(field.getAnnotation(IsColumn.class)!= null){
                        IsColumn isColumn = field.getAnnotation(IsColumn.class);

                        System.out.println("int id_"+field.getName()+" = "+count+";\n");
                    }else

                    if(field.getAnnotation(ManyToOne.class)!= null ){
                        System.out.println("int mo_"+field.getName()+" = "+count+";\n");
                    }else
                    if(field.getAnnotation(ManyToOne.class)!= null ) {
                        System.out.println("int om_"+field.getName()+" = "+count+";\n");
                    }
                    count--;
                }
        }
//        Student student = new Student(1,"huy",null,1l,1);
//        //TODO SET CLASS_NAME
//        //  querybuilder -? execute
//        QueryBuider queryBuider = new QueryBuider(QueryBuilderType.ONE,"CLASS_NAME",Student.class.getSimpleName());
//        QueryBuilderLogic queryBuilderLogic = new QueryBuilderLogic();
//        String classForm = queryBuilderLogic.processQueryBuilderForm(CLASS,queryBuider);
//        QueryBuider typeQB, nameQB, valueQB, modifierQB;
//        String formTmp = FIELD;
//        //TODO read field
//        for(Field field: Student.class.getDeclaredFields()){
//            formTmp = FIELD;
//
//            //       { STRINGBUIDER -> field
//            typeQB = new QueryBuider(QueryBuilderType.ONE,"TYPE",field.getType().getName());
//            nameQB = new QueryBuider(QueryBuilderType.ONE,"NAME",field.getName());
//            modifierQB = new QueryBuider(QueryBuilderType.ONE,"ATTRIBUTE",convertModifier(field.getModifiers()));
//            valueQB = new QueryBuider(QueryBuilderType.ONE,"VALUE","= 1");
//            //      { STRINGBUIDER -> Class
//            formTmp = queryBuilderLogic.processQueryBuilderForm(formTmp,typeQB);
//            formTmp = queryBuilderLogic.processQueryBuilderForm(formTmp,nameQB);
//            formTmp = queryBuilderLogic.processQueryBuilderForm(formTmp,modifierQB);
//            formTmp = queryBuilderLogic.processQueryBuilderForm(formTmp,valueQB);
//            queryBuider = new QueryBuider(QueryBuilderType.COMMA,"FIELD", formTmp);
//            classForm = queryBuilderLogic.processQueryBuilderForm(classForm,queryBuider);
//        }
//        System.out.println(queryBuilderLogic.cleanQueryBuilderForm(classForm));

    }
    public static String convertModifier(int modifier){
        switch (modifier){
            case Modifier.FINAL: return "final";
            case Modifier.PRIVATE: return "private";
            case Modifier.PUBLIC: return "public";
        }
        return "";
    }
}
