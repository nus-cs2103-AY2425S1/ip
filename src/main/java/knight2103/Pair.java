package knight2103;

// Solution below inspired by CS2030 (AY23/24 Sem 2) course materials
/**
 * Stores two items of different types within one object.
 * @param <T> The type of first item to be stored.
 * @param <V> The type of second item to be stored.
 */
public class Pair<T, V> {
    private final T firstItem;
    private final V secondItem;

    /**
     * Constructs a Pair object that contains two items of different types.
     * @param firstItem The first item of a certain type.
     * @param secondItem The second item of another certain type.
     */
    public Pair(T firstItem, V secondItem) {
        this.firstItem = firstItem;
        this.secondItem = secondItem;
    }

    public T getFirstItem() {
        return this.firstItem;
    }

    public V getSecondItem() {
        return this.secondItem;
    }

    @Override
    public String toString() {
        return String.format("(%s, %s)", this.firstItem, this.secondItem);
    }
}