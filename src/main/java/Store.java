import java.util.stream.IntStream;
import command.Command;

public class Store<T> {
    private final T[] array;

    private int count = 0;

    private class AddToStore extends Command {
        private final T item;

        public AddToStore(T item) {
            super("added: " + item);
            this.item = item;
        }

        @Override
        public void execute() {
            Store.this.add(item);
            System.out.println(this);
        }
    }

    private class Display extends Command {
        public Display() {
            super(Store.this.toString());
        }

        @Override
        public void execute() {
            System.out.println(this);
        }
    }

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

    public Command createAddToStore(T item) {
        return new AddToStore(item);
    }

    public Command createDisplay() {
        return new Display();
    }

    @Override
    public String toString() {
        return IntStream.range(0, count)
                .mapToObj(i -> i + "." + array[i])
                .reduce("", (x, acc) -> x + acc + "\n");
    }
}
