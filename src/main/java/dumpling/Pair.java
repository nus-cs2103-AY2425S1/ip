package dumpling;

/**
 * Helper Pair class
 * @param <S> Type of first element
 * @param <U> Type of second element
 */
public class Pair<S, U> {
    private S first;
    private U second;

    /**
     * Constructor of Pair
     * @param first First element
     * @param second Second element
     */
    public Pair(S first, U second) {
        this.first = first;
        this.second = second;
    }

    public S getFirst() {
        return this.first;
    }

    public U getSecond() {
        return this.second;
    }
}
