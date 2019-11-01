package jpa.query_data.model;

/**
 * The {@link Filter} class contains data for filtering query
 */
public class Filter {
    /**
     * Included fields/columns
     */
    private int[] includes;
    /**
     * Ignored fields/columns
     */
    private int[] ignores;

    public Filter(int[] includes, int[] ignores) {
        this.includes = includes;
        this.ignores = ignores;
    }

    public Filter() {
    }

    public int[] getIncludes() {
        return includes;
    }

    public void setIncludes(int[] includes) {
        this.includes = includes;
    }

    public int[] getIgnores() {
        return ignores;
    }

    public void setIgnores(int[] ignores) {
        this.ignores = ignores;
    }
}
