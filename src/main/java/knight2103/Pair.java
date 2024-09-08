package knight2103;

// Solution inspired by CS2030 (AY23/24 Sem 2) course materials
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
        return firstItem;
    }

    public V getSecondItem() {
        return secondItem;
    }
}