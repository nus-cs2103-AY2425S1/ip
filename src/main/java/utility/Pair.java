package utility;

/**
 * A pair class to hold two objects of different types.
 *
 * @param <T> First object, of type T.
 * @param <U> Second object, of type U.
 */
public class Pair<T, U> {
    private final T first;
    private final U second;

    /**
     * Creates a {@code Pair} object.
     *
     * @param first First item it holds.
     * @param second Second item it holds.
     */
    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Gets the first item in the {@code Pair} object
     *
     * @return The first item.
     */
    public T getFirst() {
        return first;
    }

    /**
     * Gets the second item in the {@code Pair} object
     *
     * @return The second item.
     */
    public U getSecond() {
        return second;
    }
}
