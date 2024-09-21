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
            createNewFile(file);
            return items;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                items.add(parseItem(line));
            }
        }

        return items;
    }

    /**
     * Saves the items to the file.
     *
     * @param items The list of items to be saved.
     * @throws IOException If an I/O error occurs.
     */
    public void save(List<Item> items) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Item item : items) {
                writer.write(item.toData());
            }
        }
    }

    /**
     * Parses a line from the file and converts it to an Item object.
     *
     * @param line The line to be parsed.
     * @return The corresponding Item object.
     * @throws IOException If the line contains an unknown item type.
     */
    private Item parseItem(String line) throws IOException {
        String[] data = line.split(" \\| ");
        String itemType = data[0];
        boolean isDone = data[1].equals("1");
        String name = data[2];

        switch (itemType) {
        case "T":
            return createToDoItem(isDone, name);
        case "D":
            return createDeadlineItem(isDone, name, data[3]);
        case "E":
            return createEventItem(isDone, name, data[3], data[4]);
        default:
            throw new IOException("Unknown item type in storage file.");
        }
    }

    /**
     * Creates a new file if it does not exist.
     *
     * @param file The file to be created.
     * @throws IOException If an I/O error occurs during file creation.
     */
    private void createNewFile(File file) throws IOException {
        file.getParentFile().mkdirs();
        file.createNewFile();
    }

    /**
     * Creates a ToDo item.
     *
     * @param isDone The done status of the item.
     * @param name   The name of the item.
     * @return The created ToDo item.
     */
    private ToDo createToDoItem(boolean isDone, String name) {
        ToDo todo = new ToDo(name);
        if (isDone) {
            todo.markAsDone();
        }
        return todo;
    }

    /**
     * Creates a Deadline item.
     *
     * @param isDone The done status of the item.
     * @param name   The name of the item.
     * @param by     The deadline of the item.
     * @return The created Deadline item.
     */
    private Deadline createDeadlineItem(boolean isDone, String name, String by) {
        Deadline deadline = new Deadline(name, by);
        if (isDone) {
            deadline.markAsDone();
        }
        return deadline;
    }

    /**
     * Creates an Event item.
     *
     * @param isDone The done status of the item.
     * @param name   The name of the item.
     * @param from   The start time of the event.
     * @param to     The end time of the event.
     * @return The created Event item.
     */
    private Event createEventItem(boolean isDone, String name, String from, String to) {
        Event event = new Event(name, from, to);
        if (isDone) {
            event.markAsDone();
        }
        return event;
    }
}
