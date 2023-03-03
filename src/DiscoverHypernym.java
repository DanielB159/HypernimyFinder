
import WordTypes.Hypernym;
import WordTypes.LemaRelation;

import java.io.IOException;
import java.rmi.UnexpectedException;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Locale;

/**
 * @author Daniel B.
 * this class is used in order to discover hypernym's based on a given hyponym.
 */
public class DiscoverHypernym {
    /**
     * the main method.
     * @param args - expected two strings:
     *             1. the absolute path to the directory of the corpus.
     *             2. a lemma.
     */
    public static void main(String[] args) throws IOException {
        //first, check weather the user has indeed put at least two arguments.
        if (args.length < 2) {
            throw new UnexpectedException("Expected 2 input parameters: 1. dir of corpus. 2. a lemma.");
        }
        ArrayList<LemaRelation> l1 = new ArrayList<LemaRelation>();
        String lemma = args[1];
        if (args.length > 2) { //check weather the lemma is more than one word.
            for (int i = 2; i < args.length; i++) {
                lemma += " " + args[i];
            }
        }
        lemma = lemma.toLowerCase(Locale.ROOT);
        //first, construct a database of hypernym-hyponym relations.
        TreeMap<Hypernym, TreeMap<String, Integer>> map = HypernymDatabase.buildDatabase(args[0], lemma);
        int count = 0;
        for (Hypernym i : map.keySet()) {
            if (i.getMap().containsKey(lemma)) {
                l1.add(new LemaRelation(i, i.getMap().get(lemma)));
                count++;
            }
        }
        if (count == 0) {
            System.out.println("The lemma doesn't appear in the corpus.");
            return;
        } else {
            l1.sort(new Comparator<LemaRelation>() {
                @Override
                public int compare(LemaRelation o1, LemaRelation o2) {
                    return o1.compareTo(o2);
                }
            });
            for (LemaRelation i : l1) {
                System.out.println(i.getHyper().getName() + ": (" + i.getNumOfRelations() + ")");
            }
        }
    }


}
