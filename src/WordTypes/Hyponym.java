package WordTypes;

/**
 * @author Daniel B.
 * this class describes a certain hyponym, and the times that it is counted as a hyponym of a certain hypernym.
 */
public class Hyponym implements Comparable {
    private String name;
    private int num;

    /**
     * this is the constructor function.
     * @param name
     * @param num
     */
    public Hyponym(String name, int num) {
        this.name = name;
        this.num = num;
    }

    /**
     * the name get function.
     * @return String - the name of the hyponym.
     */
    public String getName() {
        return name;
    }

    /**
     * the num get function.
     * @return - int - the number of times that the hyponym is counted as a hyponym of a certain hypernym.
     */
    public int getNum() {
        return num;
    }

    @Override
    public int compareTo(Object o) {
        Hyponym h = (Hyponym) o;
        if (h.num < this.num) {
            return -1;
        } else if (h.num > this.num) {
            return 1;
        }
        return h.name.compareTo(this.name);
    }
}
