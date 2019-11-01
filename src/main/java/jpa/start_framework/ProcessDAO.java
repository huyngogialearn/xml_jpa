package jpa.start_framework;

import jpa.Hello;
import jpa.annotation_process.register_annotation.RegisterQuery;
import jpa.annotation_process.user_annotaiton.query.Controller;
import jpa.annotation_process.user_annotaiton.query.DAO;
import jpa.exception.MySQLNotSupportException;
import jpa.inter.CRUDTableAnnotation;
import jpa.inter.ExecuteAnnotation;
import jpa.ultil.logic.AnnotationUltil;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;
public class ProcessDAO {

    public void execute(Class root) throws IOException, ClassNotFoundException, IllegalAccessException, MySQLNotSupportException {
        //processing tools
        AnnotationUltil annotationUltil = new AnnotationUltil();

        //dao classes
        //get all Class annotated DAO
        Class[] DAOClass = annotationUltil.scanAnnotations(DAO.class, root);
        //valid [@Select, @Update, @Delete or @Insert] classes
        List<Class> validDAOClass = new ArrayList<>();
        //controller classes
        Class[] controllerClasses;
        //class - its instance
        Map<Class,Object> DAOmap = new HashMap<>();
        boolean isValid;
        for (Class daoCl: DAOClass){
            isValid = false;
            //class is not interface
            if(!Modifier.isInterface(daoCl.getModifiers())) continue;
            for(Method method: daoCl.getDeclaredMethods()){
                for (Annotation annotation: method.getAnnotations()){
                    //valid [@Select, @Update, @Delete or @Insert] classes
                    //in the dao class contains the valid methods
                    if(annotation.annotationType().getAnnotation(RegisterQuery.class) != null){
                        validDAOClass.add(daoCl);
                        isValid = true;
                    }
                    if(isValid) break;
                }
                if(isValid) break;
            }
        }
        for (Class cl: validDAOClass){
            //puttung object
            DAOmap.put(cl,getInstance(cl));
        }
        //get all controler class
        controllerClasses = annotationUltil.scanAnnotations(Controller.class, root);
        for (Class conCl: controllerClasses){
            for (Field field:conCl.getDeclaredFields()){
                //if DAO variable is static
                if(Modifier.isStatic(field.getModifiers()) ){
                    Class clazz = field.getType();
                    //set value
                    field.set(field,DAOmap.get(clazz));
                }
            }
        }
    }
    public static Object getInstance(Class clazz){
        //create reflect for abtract method
        Object o = java.lang.reflect.Proxy.newProxyInstance(clazz.getClassLoader(),new java.lang.Class[]{clazz}, new java.lang.reflect.InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                ExecuteAnnotation executeAnnotation;
                RegisterQuery registerQuery;
                for (Annotation annotation:method.getAnnotations()){
                    if(annotation.annotationType().getAnnotation(RegisterQuery.class) != null){
                        registerQuery = annotation.annotationType().getAnnotation(RegisterQuery.class);
                        executeAnnotation = registerQuery.process_class().newInstance();
                        //let me process
                        if(executeAnnotation.itMe(method)){
                            //void type
                            if(method.getReturnType().equals(void.class)){
                                return null;
                            }else{
                                //List<>
                                return executeAnnotation.process(proxy,method,args);
                            }

                        }
                    }
                }
                return null;
            }
        });
        return o;
    }
}
