package Patterns;

import java.util.regex.Pattern;
/**
 * @author Daniel B.
 * this classes that implement this interface will be of different patterns of noun-phrases.
 */
public abstract class HearstPattern {
    /**
     * this function returns the regex hearst pattern.
     * @return - string - the regex.
     */
    public abstract String getRegex();

    /**
     * this function gets the pattern that compiles the class's regex.
     * @return pattern - the compiled regex.
     */
    public Pattern getPattern() {
        return Pattern.compile(this.getRegex());
    }

    /**
     * this function returns the noun phrase index that is the hypernym.
     * @return int - the index of the Hypernym in the pattern, out of all the words.
     */
    public abstract int getHypernymIndex();

}
