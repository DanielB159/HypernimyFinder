package Patterns;

/**
 * @author Daniel B.
 * this class uses the Singleton design pattern. It is only needed to be instantiated once.
 */
public final class PatternOne extends HearstPattern {
    private static PatternOne singleInstance = null;

    /**
     * this is the constructor function for the pattern.
     */
    private PatternOne() { }
    /**
     * this is the singleton getter for this pattern.
     * @return Patterns.PatternThree - the singleton Pattern.
     */
    public static PatternOne getPatternOne() {
        if (singleInstance == null) {
            PatternOne instance =  new PatternOne();
            singleInstance = instance;
        }
        return singleInstance;
    }
    @Override
    public String getRegex() {
        return "<np>[^<]*</np> ?,? (such as|including|especially) <np>[^<]*</np>"
                + "( ?,? <np>[^<]*</np>)* ?,? ?(( ?and| ?or) <np>[^<]*</np>)?";
    }

    @Override
    public int getHypernymIndex() {
        return 1;
    }
}
