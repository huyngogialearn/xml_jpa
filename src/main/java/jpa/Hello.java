package jpa;

import jdk.jshell.spi.ExecutionControl;
import jpa.annotation_process.user_annotaiton.database.Database;
import jpa.e.DatabaseStrategy;
import jpa.e.SQLType;
import jpa.exception.FramworkSettingException;
import jpa.inter.Annotation;
import jpa.resource.model.SQLConfigurationResource;
import jpa.start_framework.ProcessDAO;
import jpa.start_framework.ProcessDatabaseStrategy;

import java.io.IOException;
import java.sql.SQLException;

@Database(url = "jdbc:mysql://localhost:3306/",name = "test2",username = "root",password = "password",strategy = DatabaseStrategy.CREATE, type = SQLType.MySQL)
public class Hello {
    public static SQLConfigurationResource database = new SQLConfigurationResource("jdbc:mysql://localhost:3306/","test2","root","password", SQLType.MySQL, DatabaseStrategy.CREATE);

    public static void run(Class root){
        //TODO process connection to database
        if(root.getAnnotation(Database.class) == null) throw new FramworkSettingException("Request set up connection by @Database");
        Database database = (Database) root.getAnnotation(Database.class);
        Hello.database  = new SQLConfigurationResource(database.url(),database.name(),database.username(),database.password(),database.type(),database.strategy());
        //TODO process strategy
        ProcessDatabaseStrategy databaseStrategy = new ProcessDatabaseStrategy();
        if(database.strategy() == DatabaseStrategy.CREATE){
            try {
                databaseStrategy.createStrategy(root);
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }else{
            throw new UnsupportedOperationException();
        }
        //TODO process DAO
        ProcessDAO processDAO = new ProcessDAO();
        try {
            processDAO.execute(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
