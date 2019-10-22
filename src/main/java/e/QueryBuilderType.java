package e;
/**
 * The {@link hello_jpa.enums.QueryBuilderType} enum contains elements mapping with follow roles:
 * <table>
 *     <ul><b><:m>[KEY]<:m> : only 1 value</b></ul>
 *     <ul><b><~:>[KEY]<:~> : many values</b></ul>
 *     <ul><b><,:>[KEY]<:,> : many values breaked by comma</b></ul>
 * </table>
 * @see hello_jpa.inf.QueryForm
 * @see hello_jpa.models.QueryBuider
 * @author Huy Ngo Gia
 */
public enum QueryBuilderType {
    /**
     * <:m>[KEY]<:m> : only 1 value that is replaced
     */
    ONE,
    /**
     * <~:>[KEY]<:~> : many values that are replaced
     */
    MANY,
    /**
     * <,:>[KEY]<:,> : many values breaked by comma and replaced
     */
    COMMA,
}
