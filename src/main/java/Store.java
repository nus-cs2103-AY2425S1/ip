import java.util.stream.IntStream;

public class Store<T> {
    private final T[] array;

    private int count = 0;

    public Store(int length) {
        Object[] array = new Object[length];
        @SuppressWarnings("unchecked")
        T[] temp = (T[]) array;
        this.array = temp;
    }

    public void add(T item) {
        if (count > 100) {
            return;
        }
        array[count++] = item;
    }

    @Override
    public String toString() {
        return IntStream.range(0, count)
                .mapToObj(i -> i + ". " + array[i])
                .reduce("", (x, acc) -> x + acc + "\n");
    }
}
