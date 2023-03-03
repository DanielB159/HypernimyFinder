import WordTypes.Hypernym;

import java.util.TreeMap;

/**
 * @author Daniel B.
 * this class is used to create a file database of hypernym-hyponym relations.
 */
public class CreateHypernymDatabase {
    /**
     * the main method.
     * @param args - an input string. the first argument is a path to the directory of a corpus, the second
     *             argument is the path to the output file.
     */
    public static void main(String[] args) {
        TreeMap<Hypernym, TreeMap<String, Integer>> map = HypernymDatabase.buildDatabase(args[1], null);
        HypernymDatabase.writeDatabase(map, args[2] + "\\" + "hypernym_db.txt");
    }
}
