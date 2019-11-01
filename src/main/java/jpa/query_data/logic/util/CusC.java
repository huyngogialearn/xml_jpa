package jpa.query_data.logic.util;

import jpa.ultil.logic.TypeConvert;

public class CusC extends C {
    private String condition;
    private Object[] objects;


    public CusC(String condition, Object[] objects) {
        this.condition = condition;
        this.objects = objects;
    }

    /**
     * Set the condition by String
     * @return
     */
    @Override
    public String getCondition() {
        TypeConvert convert = new TypeConvert();
        for (Object object : objects){
            condition = condition.replace("?",convert.convertSQLFormat(object.getClass(),object,-1));
        }
        return condition;
    }
}
