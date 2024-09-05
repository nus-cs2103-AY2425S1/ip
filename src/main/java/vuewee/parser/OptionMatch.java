package vuewee.parser;

/**
 * Represents a match result for an option in a parser.
 *
 * @param <T> the type of the match result
 */
public class OptionMatch<T> {
    private final int index;
    private final T match;

    /**
     * Constructs a new OptionMatch object with the specified index and match.
     *
     * @param index the index of the match
     * @param match the match result
     */
    public OptionMatch(int index, T match) {
        this.index = index;
        this.match = match;
    }

    /**
     * Returns the index of the match.
     *
     * @return the index of the match
     */
    public int getIndex() {
        return this.index;
    }

    /**
     * Returns the match result.
     *
     * @return the match result
     */
    public T getMatch() {
        return this.match;
    }
}
