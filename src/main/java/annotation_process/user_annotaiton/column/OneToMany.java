package annotation_process.user_annotaiton.column;

import annotation_process.user_annotaiton.register_annotation.RegisterProcess;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@RegisterProcess(process_class = OneToManyProcess.class)
public @interface OneToMany {
    int ref_fr();
    int ref_to();
}
