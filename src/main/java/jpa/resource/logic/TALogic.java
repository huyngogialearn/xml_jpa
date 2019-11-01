package jpa.resource.logic;

import jpa.resource.model.TA;
import jpa.resource.model.TableResource;

/**
 * The {@link TALogic} contains the methods processing the {@link TA} resource
 */
public class TALogic {
    /**
     * Find the table resource by class
     * @param clazz criteria for finding
     * @return table result
     */
    public static TableResource getTableResourceByClass(Class clazz){
        for (TableResource value : TA.id.values()) {
            if (value.getTable().equals(clazz)) {
                return value;
            }
        }
        return null;
    }
}
