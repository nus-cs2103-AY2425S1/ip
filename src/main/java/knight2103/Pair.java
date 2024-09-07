package knight2103;

// inspired by CS2030
public class Pair<T, V> {
    private final T firstItem;
    private final V secondItem;

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