package slave;

/**
 * This class contains a pair of values of any types T and U
 *
 * @param <T>
 * @param <U>
 */
public class Pair<T, U> {
    private T t;
    private U u;

    /**
     * creates a pair containing values t of type T and u of type U
     *
     * @param t
     * @param u
     */
    public Pair(T t, U u) {
        this.t = t;
        this.u = u;
    }

    public T getFirst() {
        return this.t;
    }

    public U getSecond() {
        return this.u;
    }

    @Override
    public String toString() {
        return "[" + t + "," + u + "]";
    }

}
