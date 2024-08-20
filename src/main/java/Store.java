import java.util.stream.IntStream;
import java.util.ArrayList;

public class Store<T> {
    private final ArrayList<T> list;

    private final String label;

    public Store(String label) {
        this.label = label;
        this.list = new ArrayList<>();
    }

    public void add(T item) {
        this.list.add(item);
    }

    public T get(int index) throws StoreException {
        // index starts from 1
        try {
            return this.list.get(index - 1);
        } catch (IndexOutOfBoundsException exception) {
            throw new StoreException("No such item");
        }
    }

    public T remove(int index) throws StoreException{
        try {
            return this.list.remove(index - 1);
        } catch (IndexOutOfBoundsException exception) {
            throw new StoreException("No such item");
        }
    }

    public int getCount() {
        return this.list.size();
    }

    @Override
    public String toString() {
        return IntStream.range(0, list.size())
                .mapToObj(i -> (i + 1) + ". " + list.get(i))
                .reduce(label + "\n",
                        (acc, x) -> acc + x + "\n");
    }
}
