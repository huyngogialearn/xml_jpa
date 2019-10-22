package model;

import e.QueryBuilderType;

/**
 * The {@link QueryBuider} class contains data of attribute that follow the {@link hello_jpa.inf.QueryForm} rule
 * @author Huy Ngo Gia
 */
public class QueryBuider {
    /**
     * Indicate <:m>[KEY]<:m>,<~:>[KEY]<:~>,<,:>[KEY]<:,> (3 type of attributes)
     */
    private QueryBuilderType queryBuilderType = null;
    /**
     * Indicate [KEY]
     */
    private String key;
    /**
     * Indicate the value that is replaced to attribute
     */
    private String value;

    public QueryBuider(QueryBuilderType queryBuilderType, String key, String value) {
        this.queryBuilderType = queryBuilderType;
        this.key = key;
        this.value = value;
    }

    public QueryBuilderType getQueryBuilderType() {
        return queryBuilderType;
    }

    public void setQueryBuilderType(QueryBuilderType queryBuilderType) {
        this.queryBuilderType = queryBuilderType;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
