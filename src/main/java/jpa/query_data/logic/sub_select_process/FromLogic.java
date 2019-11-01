package jpa.query_data.logic.sub_select_process;

import jpa.e.QueryBuilderType;
import jpa.exception.NotFoundResourceException;
import jpa.resource.logic.TALogic;
import jpa.resource.model.TableResource;
import jpa.ultil.logic.QueryBuilderLogic;
import jpa.ultil.model.QueryBuider;

import static jpa.inter.SQLContrains.*;

/**
 * The {@link FromLogic} contains the methods identifying the table used
 * @author Huy Ngo Gia
 */
public class FromLogic {

    /**
     * The methods processing clazz of model for identifying the table used
     * @param parentForm parent form selecting data
     * @param clazz clazz of model
     * @return the form is filled information
     */
    public String process(String parentForm, Class clazz){
        //get sql table nam from clazz by TA resourcce
        TableResource tableResource = TALogic.getTableResourceByClass(clazz);
        //not found jpa.resource in jpa.resource
        if(tableResource == null){
            throw new NotFoundResourceException(clazz);
        }
        String SQLTableName = tableResource.getSqlName();
        //parent form can be: ....* FROM {TABLE_NAME<,>} {RELATIONSHIP< >} [WHERE] ....
        QueryBuilderLogic queryBuilderLogic = new QueryBuilderLogic();
        //result -> ....* FROM student,{TABLE_NAME<,>} {RELATIONSHIP< >} [WHERE] ....
        //replace the value into the parent form
        return queryBuilderLogic.processQueryBuilderForm(parentForm,new QueryBuider(QueryBuilderType.COMMA,TABLE_NAME_KEY,SQLTableName));
    }
}
