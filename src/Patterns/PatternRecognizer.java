package Patterns;

import WordTypes.Hypernym;

import java.util.Locale;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;
/**
 * @author Daniel B.
 */
public class PatternRecognizer {
    private ArrayList<HearstPattern> hearstPatternList;
    public static final String PARTITION  = "<np>[^<]*</np>";
    public static final String FLAG1 = "such";
    public static final String FLAG2 = "especially";
    public static final String FLAG3 = "which";
    public static final String FLAG4 = "including";
    /**
     * this is the constructor function for the pattern.
     */
    public PatternRecognizer() {
        this.hearstPatternList = new ArrayList<HearstPattern>();
    }

    /**
     * this function adds the Patterns.HearstPattern to the current pattern list.
     * @param p
     */
    public void addPattern(HearstPattern p) {
        hearstPatternList.add(p);
    }

    /**
     *
     * @param filename
     * @param hypoMap
     * @param lemma - a lemma to search the corpus for. expected to be null if
     *              the user wants to search the corpus as a whole.
     */
    public void recognize(String filename, TreeMap<Hypernym, TreeMap<String, Integer>> hypoMap, String lemma) {
        Pattern partition = Pattern.compile(PARTITION);
        BufferedReader stream = null;
        //try to open and read a file. if failed reading, will catch the exception.
        try {
            stream = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
            String line;
            while ((line = stream.readLine()) != null) {
                line = line.toLowerCase(Locale.ROOT);
                /*
                if the user has requested a certain lemma, check if it is in the read line.
                if the lemma is not in the read line, skip this line.
                */
                if (lemma != null) {
                    if (!line.contains(lemma)) {
                        continue;
                    }
                }
                //for every line, we will go over the line with every Patterns.HearstPattern.
                for (HearstPattern i : hearstPatternList) {
                    //if the line does not contain any of the flags, skip it.
                    if (!(line.contains(FLAG1) || line.contains(FLAG2) || line.contains(FLAG3)
                            || line.contains(FLAG4))) {
                        continue;
                    }
                    Pattern p = i.getPattern();
                    Matcher m = p.matcher(line);
                    while (m.find()) {
                        String found = m.group(0);
                        Matcher mPartition = partition.matcher(found);
                        int countFound = 1;
                        Hypernym currentHypernym = new Hypernym("");
                        while (mPartition.find()) {
                            String wordFound = mPartition.group(0);
                            String s1 = (wordFound.substring(4, wordFound.length() - 5));
                            //if this is the third pattern, the hypernym is in the second noun-phrase.
                            if (i.getHypernymIndex() == 2) {
                                //the current string is the hyponym.
                                String hypo = s1;
                                //find the next noun phrase, the hypernym.
                                mPartition.find();
                                currentHypernym = new Hypernym(
                                        mPartition.group(0).substring(4, mPartition.group(0).length() - 5));
                                //put the hyponym and hypernym in the map.
                                if (!hypoMap.containsKey(currentHypernym)) { //if the hypernym isn't in the map.
                                    hypoMap.put(currentHypernym, currentHypernym.getMap());
                                    hypoMap.get(currentHypernym).put(hypo, 1);
                                } else {
                                    //if the hyponym isn't in the map.
                                    if (!hypoMap.get(currentHypernym).containsKey(hypo)) {
                                        hypoMap.get(currentHypernym).put(hypo, 1);
                                    } else { //if the hyponym is in the map.
                                        int current = hypoMap.get(currentHypernym).get(hypo);
                                        hypoMap.get(currentHypernym).replace(hypo, current + 1);
                                    }
                                }
                            } else if (countFound == i.getHypernymIndex()) {
                                //create a new hypernym
                                Hypernym hyper1 = new Hypernym(s1);
                                currentHypernym = hyper1;
                                //if the map does not contain this hypernym, add it to the treemap.
                                if (!hypoMap.containsKey(hyper1)) {
                                    hypoMap.put(hyper1, hyper1.getMap());
                                }
                                countFound++;
                            } else {
                                //if the hyponym isn't in the hyponym map.
                                if (!hypoMap.get(currentHypernym).containsKey(s1)) {
                                    hypoMap.get(currentHypernym).put(s1, 1);
                                } else { //if the hyponym is in the hyponym map.
                                    int current = hypoMap.get(currentHypernym).get(s1);
                                    hypoMap.get(currentHypernym).replace(s1, current + 1);
                                }
                                countFound++;
                            }
                        }
                    }
                }
            }
            stream.close();
        } catch (IOException e) {
            System.out.println("Error while reading!");
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    System.out.println("failed closing the stream!");
                }
            }
        }
    }


}
