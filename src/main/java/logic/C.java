package logic;

/**
 * The {@link C} class (CompareOperation) contains method to create a where clause instead of using a string that leads to mistakes in coding process
 * @author Huy Ngo Gia
 */
public class C {

    /**
     * Map to 'and' in sql query
     * @param CompareOperation clause that need to connect
     */
    public C and(C CompareOperation){
        return this;
    }

    /**
     * Map to 'or' in sql query
     * @param CompareOperation clause that need to connect
     */
    public C or(C CompareOperation){
        return this;
    }

    /**
     * The {@link C#m(int, String)} method (More).
     * Map to '>' in sql query
     * @param ColumnId id of column that need to compare
     * @param value compared value
     */
    public C m(int ColumnId, String value){
        return this;
    }

    /**
     * The {@link C#m_e(int, String)} method (More Equal).
     * Map to '>=' in sql query
     * @param ColumnId id of column that need to compare
     * @param value compared value
     */
    public C m_e(int ColumnId, String value){
        return this;
    }

    /**
     * The {@link C#l(int, String)} method (Less).
     * Map to '<' in sql query
     * @param ColumnId id of column that need to compare
     * @param value compared value
     */
    public C l(int ColumnId, String value){
        return this;
    }

    /**
     * The {@link C#l_e(int, String)} method (Less Equal).
     * Map to '<=' in sql query
     * @param ColumnId id of column that need to compare
     * @param value compared value
     */
    public C l_e(int ColumnId, String value){
        return this;
    }

    /**
     * The {@link C#e(int, String)} method (Equal).
     * Map to '=' in sql query
     * @param ColumnId id of column that need to compare
     * @param value compared value
     */
    public C e(int ColumnId, String value){
        return this;
    }

    /**
     * The {@link C#li(int, String)} method (Equal).
     * Map to 'like' in sql query
     * <p>'%' represents zero, one or multiple characters</p>
     * <p>'_' represents a single number or character</p>
     * @param ColumnId id of column that need to compare
     * @param ValueForm compared value
     */
    public C li(int ColumnId, String ValueForm){
        return this;
    }
}
