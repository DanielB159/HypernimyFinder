package Patterns;

/**
 * @author Daniel B.
 * this class uses the Singleton design pattern. It is only needed to be instantiated once.
 */
public final class PatternTwo extends HearstPattern {
    private static PatternTwo singleInstance = null;
    /**
     * this is the constructor function for the pattern.
     */
    private PatternTwo() { }
    /**
     * this is the singleton getter for this pattern.
     * @return Patterns.PatternThree - the singleton Pattern.
     */
    public static PatternTwo getPatternTwo() {
        if (singleInstance == null) {
            PatternTwo instance =  new PatternTwo();
            singleInstance = instance;
        }
        return singleInstance;
    }

    @Override
    public String getRegex() {
        return "such <np>[^<]*</np> as <np>[^<]*</np>"
                + "( ?,? <np>[^<]*</np>)* ?,? ?(( ?and| ?or) <np>[^<]*</np>)?";
    }
    @Override
    public int getHypernymIndex() {
        return 1;
    }
}
