package yappingbot.ui;

/**
 * String builder helper for any functions requiring multiple lines added sequentially and then printed.
 */
public class MultilineStringBuilder extends Ui{
    private final StringBuilder sb = new StringBuilder();

    /**
     * Adds a new single String line to the internal buffer
     *
     * @param s String single-line text
     */
    public void addLine(String s) {
        Ui.quoteSinglelineText(s, sb);
    }

    /**
     * Prints the concatenated internally stored String
     */
    public void print() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return sb.toString();
    }
}
