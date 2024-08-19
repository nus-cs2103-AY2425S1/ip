import java.util.stream.IntStream;

public class Store<T> {
    private final T[] array;

    private int count = 0;

    private final String label;

    public Store(int length, String label) {
        Object[] array = new Object[length];
        @SuppressWarnings("unchecked")
        T[] temp = (T[]) array;
        this.array = temp;
        this.label = label;
    }

    public void add(T item) {
        if (count >= array.length) {
            return;
        }
        array[count++] = item;
    }

    public T get(int index) {
        // index starts from 1
        if (index <= 0 || index > count) {
            return null;
        }
        return this.array[index - 1];
    }

    @Override
    public String toString() {
        return IntStream.range(0, count)
                .mapToObj(i -> (i + 1) + ". " + array[i])
                .reduce(label + "\n",
                        (x, acc) -> x + acc + "\n");
    }
}
