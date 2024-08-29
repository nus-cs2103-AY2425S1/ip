package sam;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The Storage class handles the loading and saving of items to a file.
 */
public class Storage {

    private String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The file path to load and save items.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads items from the file.
     *
     * @return The list of loaded items.
     * @throws IOException If an I/O error occurs.
     */
    public List<Item> load() throws IOException {
        List<Item> items = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
            return items;  // Return empty list if file didn't exist
        }

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(" \\| ");
            String itemType = data[0];
            boolean isDone = data[1].equals("1");
            String name = data[2];

            switch (itemType) {
            case "T":
                ToDo todo = new ToDo(name);
                if (isDone) {
                    todo.markAsDone();
                }
                items.add(todo);
                break;
            case "D":
                Deadline deadline = new Deadline(name, data[3]);
                if (isDone) {
                    deadline.markAsDone();
                }
                items.add(deadline);
                break;
            case "E":
                Event event = new Event(name, data[3], data[4]);
                if (isDone) {
                    event.markAsDone();
                }
                items.add(event);
                break;
            default:
                throw new IOException("Unknown item type in storage file.");
            }
        }
        reader.close();
        return items;
    }

    /**
     * Saves the items to the file.
     *
     * @param items The list of items to be saved.
     * @throws IOException If an I/O error occurs.
     */
    public void save(List<Item> items) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

        for (Item item : items) {
            writer.write(item.toData());
        }

        writer.close();
    }
}