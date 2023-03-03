package WordTypes;

import java.util.TreeMap;
/**
 * @author Daniel B.
 * this class describes a certain hypernym and a Treemap of all its hyponyms.
 */
public class Hypernym implements Comparable {
    private TreeMap<String, Integer> map;
    private String name;

    /**
     * constructor.
     * @param name
     */
    public Hypernym(String name) {
        this.name = name;
        this.map = new TreeMap<String, Integer>();
    }

    /**
     * the get function.
     * @return map - the hypernym's treemap.
     */
    public TreeMap<String, Integer> getMap() {
        return map;
    }

    /**
     *
     * @param hyponym
     */
    public void addHyponym(String hyponym) {
        if (this.map.containsKey(hyponym)) {
            int current = this.map.get(hyponym);
            this.map.replace(hyponym, current + 1);
            return;
        }
        this.map.put(hyponym, 1);
    }

    @Override
    public int compareTo(Object o) {
        Hypernym hyper = (Hypernym) o;
        return this.name.compareTo(((Hypernym) o).name);
    }

    /**
     * the name get function.
     * @return String - the name of the hypernym.
     */
    public String getName() {
        return name;
    }
}
