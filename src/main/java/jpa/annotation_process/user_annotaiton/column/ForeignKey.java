package jpa.annotation_process.user_annotaiton.column;

import jpa.annotation_process.register_annotation.RegisterProcess;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The {@link ForeignKey} annotation indicates a field which is a foreign key.
 * @author Huy Ngo Gia
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@RegisterProcess(process_class = ForeignKeyProcess.class)
public @interface ForeignKey  {
        /**
         * @return the column referent to this column
         */
        int ref_fr();

        /**
         * @return the column is referented (called foreign key).
         */
        int name();
}
