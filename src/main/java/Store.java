import java.io.*;
import java.util.stream.IntStream;
import java.util.ArrayList;

public class Store<T extends Serializable> {
    private final ArrayList<T> list;

    private final String label;

    private final String ioPath;

    public Store(String label, String ioPath) {
        this.label = label;
        this.ioPath = ioPath;

        File file = new File(ioPath);
        ArrayList<T> list = new ArrayList<>();

        try {
            if (file.exists() || (file.getParentFile().mkdirs() && file.createNewFile())) {
                // if file already exists or is successfully created

                FileInputStream fileStream = new FileInputStream(file);
                ObjectInputStream in = new ObjectInputStream(fileStream);

                Object serializedObject = in.readObject();
                if (serializedObject instanceof ArrayList<?>) {
                    // Array list can only be accessed by methods of Store so safe to type cast
                    @SuppressWarnings("unchecked")
                    ArrayList<T> temp = (ArrayList<T>) serializedObject;
                    list = temp;
                }
            }
        } catch (ClassNotFoundException | IOException e) {
            System.out.println("Something went wrong when reading file: " + e.getMessage());
        } finally {
            this.list = list;
        }
    }

    public void add(T item) throws StoreException {
        this.list.add(item);
        saveToFile();
    }

    public T get(int index) throws StoreException {
        // index starts from 1
        try {
            return this.list.get(index - 1);
        } catch (IndexOutOfBoundsException exception) {
            throw new StoreException("No such item");
        }
    }

    public T remove(int index) throws StoreException {
        try {
            T removedItem = this.list.remove(index - 1);
            saveToFile();
            return removedItem;
        } catch (IndexOutOfBoundsException exception) {
            throw new StoreException("No such item");
        }
    }

    public void set(int index, T newItem) throws StoreException {
        try {
            this.list.set(index - 1, newItem);
            saveToFile();
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

    public void saveToFile() throws StoreException {
        try {
            FileOutputStream file = new FileOutputStream(this.ioPath);
            ObjectOutputStream out = new ObjectOutputStream(file);

            out.writeObject(this.list);

            // clean up
            out.close();
            file.close();
        } catch (FileNotFoundException e) {
            throw new StoreException(
                    String.format(
                            "File (%s) not found: %s", this.ioPath, e.getMessage()
                    ));
        } catch (IOException e) {
            throw new StoreException(
                    String.format(
                            "Something went wrong when saving to file (%s): %s",
                            this.ioPath, e.getMessage()
                    ));
        }
    }
}
