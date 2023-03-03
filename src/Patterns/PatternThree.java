package Patterns;

/**
 * @author Daniel B.
 * this class uses the Singleton design pattern. It is only needed to be instantiated once.
 */
public final class PatternThree extends HearstPattern {
    private static PatternThree singleInstance = null;

    /**
     * this is the constructor function for the pattern.
     */
    private PatternThree() { }
    /**
     * this is the singleton getter for this pattern.
     * @return Patterns.PatternThree - the singleton Pattern.
     */
    public static PatternThree getPatternThree() {
        if (singleInstance == null) {
            PatternThree instance =  new PatternThree();
            singleInstance = instance;
        }
        return singleInstance;
    }

    @Override
    public String getRegex() {
        return "<np>[^<]*</np> ?,? ?which is ((an example |a kind |a class )?of )?<np>[^<]*</np>";
    }
    @Override
    public int getHypernymIndex() {
        return 2;
    }
}
