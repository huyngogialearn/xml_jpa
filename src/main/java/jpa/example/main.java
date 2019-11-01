package jpa.example;

import jpa.annotation_process.user_annotaiton.column.IsColumn;
import jpa.annotation_process.user_annotaiton.database.Database;
import jpa.annotation_process.register_annotation.RegisterProcess;
import jpa.annotation_process.user_annotaiton.table.IsTable;
import jpa.e.DatabaseStrategy;
import jpa.e.QueryBuilderType;
import jpa.e.SQLType;
import jpa.exception.MySQLNotSupportException;
import jpa.inter.CRUDColumnAnnotation;
import jpa.inter.CRUDTableAnnotation;
import jpa.ultil.logic.QueryBuilderLogic;
import jpa.ultil.model.QueryBuider;
import jpa.ultil.logic.AnnotationUltil;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;import jpa.resource.model.CO;

import static jpa.inter.SQLContrains.COLUMN_CREATE_SUB_QUERY;
import static jpa.inter.SQLContrains.TABLE_CREATE_QUERY;

@Database(name = "Demo1",url = "jdbc:mysql://localhost:3306/",username = "sa",password = "123456",strategy = DatabaseStrategy.CREATE, type = SQLType.MySQL)
public class main {
    public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException, MySQLNotSupportException {
        AnnotationUltil annotationUltil = new AnnotationUltil();
        Class[] classes = annotationUltil.scanAnnotations(IsTable.class,main.class);
        String tableForm, columnForm ;
        String tableNameKey = "TABLE_NAME";
        QueryBuilderLogic ex = new QueryBuilderLogic();
        QueryBuider qb ;
        List<Annotation> classTmp = new ArrayList<Annotation>();
        List<Annotation> classTmp2 = new ArrayList<Annotation>();

        for (Class clazz:classes){
            tableForm = TABLE_CREATE_QUERY;
            classTmp = new ArrayList<>();
            //TODO create table form\
            List<Class<CRUDTableAnnotation>> ann = getMyAnnotation(clazz,classTmp);
            for (int i =0; i< ann.size();i++){
                Class<CRUDTableAnnotation> crudDD= ann.get(i);
                CRUDTableAnnotation tmp = crudDD.newInstance();
                tmp.setAnnotation(new Object[]{classTmp.get(i)});
                tableForm = tmp.create(tableForm);
            }
            for(Field field: clazz.getDeclaredFields()){
                columnForm = COLUMN_CREATE_SUB_QUERY;
                //TODO creat column form
                if(field.getAnnotation(IsColumn.class) != null){
                    classTmp2 = new ArrayList<>();
                    List<Class<CRUDColumnAnnotation>> ann2 = getMyAnnotation2(field,classTmp2);
                    for (int i =0; i< ann2.size();i++){
                        Class<CRUDColumnAnnotation> crudc = ann2.get(i);
                        CRUDColumnAnnotation tmp2 = crudc.newInstance();
                        tmp2.setAnnotation(new Object[]{classTmp2.get(i)});
                        tmp2.setAnnotatedField(field);
                        columnForm = tmp2.create(columnForm);

                    }
                    qb = new QueryBuider(QueryBuilderType.COMMA,"COLUMN_CREATE_SUB_QUERY",columnForm);
                    tableForm = ex.processQueryBuilderForm(tableForm,qb);
                }}
        }
    }
    public static List<Class<CRUDTableAnnotation>> getMyAnnotation(Class clazz,List<Annotation> classTmp){
        List<Class<CRUDTableAnnotation>> obs = new ArrayList<Class<CRUDTableAnnotation>>();
        RegisterProcess tmp;
        for(Annotation ann: clazz.getAnnotations()){
            tmp= ann.annotationType().getAnnotation(RegisterProcess.class);
            if(tmp != null){
                classTmp.add(ann);
                obs.add((Class<CRUDTableAnnotation>)tmp.process_class());
            }
        }
        return obs;
    }
    public static List<Class<CRUDColumnAnnotation>> getMyAnnotation2(Field field, List<Annotation> classTmp){
        List<Class<CRUDColumnAnnotation>> obs = new ArrayList<Class<CRUDColumnAnnotation>>();
        RegisterProcess tmp;
        for(Annotation ann: field.getAnnotations()){
            tmp= ann.annotationType().getAnnotation(RegisterProcess.class);
            if(tmp != null){
                classTmp.add(ann);
                obs.add((Class<CRUDColumnAnnotation>)tmp.process_class());
            }
        }
        return obs;
    }

}
