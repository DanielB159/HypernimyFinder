package WordTypes;

/**
 * @author Daniel B.
 * this class is used to compile hypernyms of a certain lemma, and the times that the lemma is counted as the
 * hyponym of this hypernym.
 */
public class LemaRelation implements Comparable {
    private Hypernym hyper;
    private int numOfRelations;

    /**
     * this is the constructor for a WordTypes.LemaRelation.
     * @param hyper
     * @param numOfRelations
     */
    public LemaRelation(Hypernym hyper, int numOfRelations) {
        this.hyper = hyper;
        this.numOfRelations = numOfRelations;
    }

    /**
     * this is the get function for the WordTypes.Hypernym.
     * @return - the hypernym.
     */
    public Hypernym getHyper() {
        return hyper;
    }

    /**
     * this is the get function for num of relations.
     * @return int - the number of relations.
     */
    public int getNumOfRelations() {
        return numOfRelations;
    }

    @Override
    public int compareTo(Object o) {
        LemaRelation lema = (LemaRelation) o;
        if (this.numOfRelations < lema.numOfRelations) {
            return 1;
        } else if (this.numOfRelations > lema.numOfRelations) {
            return -1;
        } else {
            return this.hyper.compareTo(lema.hyper);
        }
    }
}
