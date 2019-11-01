package jpa.start_framework;

import jpa.Hello;
import jpa.annotation_process.register_annotation.RegisterProcess;
import jpa.annotation_process.user_annotaiton.column.IsColumn;
import jpa.annotation_process.user_annotaiton.column.IsColumnProcess;
import jpa.annotation_process.user_annotaiton.database.DatabaseProcess;
import jpa.annotation_process.user_annotaiton.query.DAO;
import jpa.annotation_process.user_annotaiton.query.FillDAO;
import jpa.annotation_process.user_annotaiton.query.SelectQuery;
import jpa.annotation_process.user_annotaiton.table.IsTable;
import jpa.annotation_process.user_annotaiton.table.IsTableProcess;
import jpa.e.QueryBuilderType;
import jpa.example.main;
import jpa.exception.MySQLNotSupportException;
import jpa.inter.CRUDAnnotation;
import jpa.inter.CRUDColumnAnnotation;
import jpa.inter.CRUDTableAnnotation;
import jpa.query_data.logic.SelectData;
import jpa.resource.logic.SQLConfigurationLogic;
import jpa.ultil.logic.AnnotationUltil;
import jpa.ultil.logic.QueryBuilderLogic;
import jpa.ultil.model.QueryBuider;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static jpa.inter.SQLContrains.*;

public class ProcessDatabaseStrategy {
    public void createStrategy(Class root) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException, MySQLNotSupportException, SQLException {
        //Processing Tools
        AnnotationUltil annotationUltil = new AnnotationUltil();
        QueryBuilderLogic queryBuilderLogic = new QueryBuilderLogic();

        //tmp variable
        Iterator<String> iteratorTmp;
        Statement stmtTmp;
        boolean isForeignError = false;

        RegisterProcess registerProcessTmp;

        CRUDTableAnnotation crudTableAnnotationTmp;
        Class<CRUDTableAnnotation> crudTableAnnotationClassTmp;

        Class<CRUDColumnAnnotation> crudColumnAnnotationClassTmp;
        CRUDColumnAnnotation crudColumnAnnotationTmp;


        //classed annotated @IsTable
        //TODO Get all IsTable.class
        Class[] isTableClasses = annotationUltil.scanAnnotations(IsTable.class, root);
        //CREATE TABLE [TABLE_NAME]({COLUMN_CREATE_SUB_QUERY<,>})
        //main form
        String createForm = null, createColForm = null, dropForm = null;
        List<String> createTableForms = new ArrayList<>();


        if (isTableClasses == null || isTableClasses.length == 0) {
            return;
        }
        createTableForms.add(DISABLE_FOREIGN_KEY_CHECKS);
        for (Class isTableClass : isTableClasses) {
            createForm = TABLE_CREATE_QUERY;
            dropForm = DROP_TABLE_QUERY;
            registerProcessTmp = isTableClass.getAnnotation(IsTable.class).annotationType().getAnnotation(RegisterProcess.class);
            crudTableAnnotationClassTmp = (Class<CRUDTableAnnotation>) registerProcessTmp.process_class();
            //create CRUDTableAnnotation object
            crudTableAnnotationTmp = crudTableAnnotationClassTmp.newInstance();
            //fill needed data
            crudTableAnnotationTmp.setClass(isTableClass);
            crudTableAnnotationTmp.setAnnotation(new Object[]{isTableClass.getAnnotation(IsTable.class)});
            //fill needed data into form that create table
            createForm = crudTableAnnotationTmp.create(createForm);
            //drop table
            dropForm = crudTableAnnotationTmp.delete(dropForm);
            for (Field isTaClField : isTableClass.getDeclaredFields()) {
                createColForm = COLUMN_CREATE_SUB_QUERY;
                for (Annotation isTaClFeAnnotation : isTaClField.getAnnotations()) {
                    //if field contains annotation annotated @RegisterProcess (annotation of framework to create column)
                    if (isTaClFeAnnotation.annotationType().getAnnotation(RegisterProcess.class) != null) {
                        registerProcessTmp = isTaClFeAnnotation.annotationType().getAnnotation(RegisterProcess.class);
                        //class implement CRUDColumnAnnotation
                        if (CRUDColumnAnnotation.class.isAssignableFrom(registerProcessTmp.process_class())) {
                            crudColumnAnnotationClassTmp = (Class<CRUDColumnAnnotation>) registerProcessTmp.process_class();
                            //create CrudColumnAnnotation object
                            crudColumnAnnotationTmp = crudColumnAnnotationClassTmp.newInstance();
                            //fill needed data
                            crudColumnAnnotationTmp.setAnnotatedField(isTaClField);
                            crudColumnAnnotationTmp.setAnnotation(new Object[]{isTaClFeAnnotation});
                            //fill needed data into form that create column
                            createColForm = crudColumnAnnotationTmp.create(createColForm);
                        }
                    }
                }
                createForm = queryBuilderLogic.processQueryBuilderForm(createForm, new QueryBuider(QueryBuilderType.COMMA, COLUMN_CREATE_SUB_QUERY_KEY, queryBuilderLogic.cleanQueryBuilderForm(createColForm)));
            }
            createTableForms.add(queryBuilderLogic.cleanQueryBuilderForm(dropForm));
            createTableForms.add(queryBuilderLogic.cleanQueryBuilderForm(createForm));
        }
        createTableForms.add(ENABLE_FOREIGN_KEY_CHECKS);

        //get connection
        Connection connection = SQLConfigurationLogic.getConnection(Hello.database);
        if (connection == null) return;
        connection.setAutoCommit(false);

        iteratorTmp = createTableForms.iterator();

        while (iteratorTmp.hasNext()) {
            try {
                //execute query to create table
                stmtTmp = connection.createStatement();
                stmtTmp.execute(iteratorTmp.next());
            } catch (Exception e) {
                e.printStackTrace();
                //sroll back
                connection.rollback();
                break;
            }
        }
        SQLConfigurationLogic.closeConnection();

    }
}
