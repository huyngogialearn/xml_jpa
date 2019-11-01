package jpa.e;

import jpa.ultil.logic.QueryBuilderLogic;
import jpa.ultil.model.QueryBuider;

/**
 * The {@link QueryBuilderLogic} enum indicates what type of replacement that {@link QueryBuilderLogic} should be executed
 * @see QueryBuider
 * @see QueryBuilderLogic
 */
public enum QueryBuilderType {
    /**
     * [KEY] : only 1 value that is replaced
     */
    ONE,

    /**
     * {KEY} : many values that are replaced
     */
    MANY,

    /**
     * {KEY<Sign>}: many values broken by {Sign} and replaced
     */
    COMMA,

}
