package gojou;

/**
 * Represents a generic pair of objects.
 *
 * @param <T> The type of the first object in the pair.
 * @param <S> The type of the second object in the pair.
 */
public class Pair<T, S> {
    private T first;
    private S second;

    /**
     * Constructs a new Pair with the specified objects.
     *
     * @param t The first object in the pair.
     * @param s The second object in the pair.
     */
    public Pair(T t, S s) {
        this.first = t;
        this.second = s;
    }

    /**
     * Returns the first object in the pair.
     *
     * @return The first object in the pair.
     */
    public T getFirst() {
        return this.first;
    }

    /**
     * Returns the second object in the pair.
     *
     * @return The second object in the pair.
     */
    public S getSecond() {
        return this.second;
    }
}
