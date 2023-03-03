import Patterns.PatternOne;
import Patterns.PatternRecognizer;
import Patterns.PatternThree;
import Patterns.PatternTwo;
import WordTypes.Hypernym;
import WordTypes.Hyponym;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeMap;

/**
 * @author Daniel B.
 * this class is used to create and write a HypernymDatabase.
 */
public class HypernymDatabase {
    /**
     * this function builds a TreeMap database of hypernym-hyponym relations.
     * @param corpusDir - the directory of the corpus.
     * @param lemma - a lemma to search the corpus for. expected to be null if
     *              the user wants to search the corpus as a whole.
     * @return Treemap - the mapped relations.
     */
    public static TreeMap<Hypernym, TreeMap<String, Integer>>  buildDatabase(
            String corpusDir, String lemma) {
        TreeMap<Hypernym, TreeMap<String, Integer>> map = new TreeMap<>();
        PatternRecognizer p = new PatternRecognizer();
        //first, add all of the patterns to the PatternRecognizer.
        p.addPattern(PatternOne.getPatternOne());
        p.addPattern(PatternTwo.getPatternTwo());
        p.addPattern(PatternThree.getPatternThree());
        File directory = new File(corpusDir);
        File[] directoryListing = directory.listFiles();
        //for each file, recognize the hypernym-hyponym relations and add them to the map.
        for (File i : directoryListing) {
            p.recognize(corpusDir + "\\" + i.getName(), map, lemma);
        }
        return map;
    }

    /**
     * this function writes the mapped database in a new file.
     * @param map - the mapping of the hypernyms-hyponyms.
     * @param filename - the name of the file to write in.
     */
    public static void writeDatabase(TreeMap<Hypernym, TreeMap<String, Integer>> map, String filename) {
        OutputStreamWriter os = null;
        //try to open the file that is to be written into. if the writing failed catch the exception.
        try {
            os = new OutputStreamWriter(new FileOutputStream(filename));
            //only write the hypernyms that have more than 3 hyponyms.
            for (Hypernym i : map.keySet()) {
                if (i.getMap().size() < 3) {
                    continue;
                }
                os.write(i.getName() + ": ");
                /*
                before writing all of the hypernyms in the file, we need to sort them by their number
                of repetitions as hyponyms of the current hypernym.
                if the number of repetitions if equal, sort lexicographically.
                 */
                Object[] hyponymArr = i.getMap().keySet().toArray();
                //we will put the keys in a hyponym ArrayList.
                ArrayList<Hyponym> hyponymArrayList = new ArrayList<Hyponym>();
                for (Object k : hyponymArr) {
                    hyponymArrayList.add(new Hyponym((String) k, i.getMap().get(k)));
                }
                //next, we will sort them using a personalized comparator.
                hyponymArrayList.sort(new Comparator<Hyponym>() {
                    @Override
                    public int compare(Hyponym o1, Hyponym o2) {
                        if (o1.getNum() > o2.getNum()) {
                            return -1;
                        } else if (o1.getNum() < o2.getNum()) {
                            return 1;
                        }
                        return o1.getName().compareTo(o2.getName());
                    }
                });
                int counthyponyms = 1;
                //now, we will print the sorted list into the file.
                for (Hyponym j : hyponymArrayList) {
                    if (counthyponyms == 1) {
                        os.write(j.getName() + " " + "(" + j.getNum() + ")");
                        counthyponyms++;
                    } else {
                        os.write(", " + j.getName() + " " + "(" + j.getNum() + ")");
                    }
                }
                os.write("\n");
            }
        } catch (IOException e) {
            System.out.println("Something went wrong while writing!");
        } finally {
            if (os != null) { // Exception might have happened at constructor
                try {
                    os.close();
                } catch (IOException e) {
                    System.out.println("Failed closing the file!");
                }
            }
        }
    }
}
