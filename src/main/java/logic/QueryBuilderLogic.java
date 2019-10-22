package logic;

import model.QueryBuider;

import java.util.regex.Matcher;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The {@link QueryBuilderLogic} class contains the methods to process {@link model.QueryBuider} instance
 */
public class QueryBuilderLogic {
    /**
     * The {@link QueryBuilderLogic#processQueryBuilderForm(String, QueryBuider)} method is main method to add a value of attribute in given form
     * @param form form of query
     * @return form processed
     */
    public String processQueryBuilderForm(String form, QueryBuider queryBuider){
        switch (queryBuider.getQueryBuilderType()){
            case ONE:
                form = form.replace("["+queryBuider.getKey()+"]",queryBuider.getValue());
                break;
            case MANY:
                form = form.replace("{"+queryBuider.getKey()+"}",queryBuider.getValue()+"{"+queryBuider.getKey()+"}");
                break;
            case COMMA:

                Pattern p = Pattern.compile("\\{"+queryBuider.getKey()+"\\<[ \\,\\.\\:\\;\\@!\\#\\$\\%\\^\\&\\*\\?]+\\>\\}");   // the pattern to search for
                Matcher m = p.matcher(form);
                if(!m.find()) break;
                String SIGN_FORM = form.substring(m.start(),m.end());
                String SIGN = SIGN_FORM.substring(SIGN_FORM.indexOf("<")+1,SIGN_FORM.lastIndexOf(">"));
                form = form.replace(SIGN_FORM,queryBuider.getValue()+SIGN+SIGN_FORM);
                break;
        }
        return form;
    }

    /**
     * The {@link QueryBuilderLogic#cleanQueryBuilderForm(String)} method uses to clean form to get complete query
     * @param form form of query
     * @return complete form
     */
    public String cleanQueryBuilderForm(String form){
        String regex = "(\\{[A-Z_]*\\})|" +
                "(\\{[A-Z_]*\\<[ \\,\\.\\:\\;\\@!\\#\\$\\%\\^\\&\\*\\?]+\\>\\})|" +
                "([ \\,\\.\\:\\;\\@!\\#\\$\\%\\^\\&\\*\\?]+\\{[A-Z_]*\\<[ \\,\\.\\:\\;\\@!\\#\\$\\%\\^\\&\\*\\?]+\\>\\})";
        return form.replaceAll(regex,"");
    }

}
