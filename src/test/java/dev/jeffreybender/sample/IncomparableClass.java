package dev.jeffreybender.sample;

/**
 *
 * @author jbender
 */
public class IncomparableClass {

    private int rank;

    public IncomparableClass(int rank) {
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "IncomparableClass [rank=" + rank + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + rank;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        IncomparableClass other = (IncomparableClass) obj;
        if (rank != other.rank)
            return false;
        return true;
    }

}
