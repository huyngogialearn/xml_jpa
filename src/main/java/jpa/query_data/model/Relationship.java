package jpa.query_data.model;

/**
 * The {@link Relationship} contains the data for a relationship in database
 */
public class Relationship {
    /**
     * Referent from field/column
     */
    private int reFr;
    /**
     * Foreign key
     */
    private int reTo;

    public Relationship(int reFr, int reTo) {
        this.reFr = reFr;
        this.reTo = reTo;
    }

    public int getReFr() {
        return reFr;
    }

    public void setReFr(int reFr) {
        this.reFr = reFr;
    }

    public int getReTo() {
        return reTo;
    }

    public void setReTo(int reTo) {
        this.reTo = reTo;
    }
}
