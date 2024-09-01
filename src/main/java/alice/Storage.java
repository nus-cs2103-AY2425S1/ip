package alice;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles the saving and loading of tasks to and from a file.
 */
public class Storage {
    private final String path;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");

    /**
     * Constructs a Storage instance with the specified file path.
     * Creates necessary directories for the file path if they do not exist.
     *
     * @param path The file path to the storage location for tasks.
     */
    public Storage(String path) {
        this.path = path;
        File file = new File(path);
        file.getParentFile().mkdirs();
    }

    /**
     * Saves the tasks in the provided TaskList to the file.
     * Each task is written as a string representation on a new line.
     *
     * @param taskList The TaskList object containing tasks to be saved.
     */
    public void save(TaskList taskList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            for (Task task : taskList.getTask()) {
                writer.write(task.saveString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error when saving tasks" + e.getMessage());
        }
    }

    /**
     * Parses a string representation of a time and returns the corresponding LocalDateTime object.
     *
     * @param time The string representation of the time in "dd-MM-yyyy HHmm" format.
     * @return The LocalDateTime object representing the parsed time, or null if the format is incorrect.
     */
    public LocalDateTime setTime(String time) {
        try {
            return LocalDateTime.parse(time, formatter);
        } catch (Exception e) {
            System.out.println("Wrong format for the time: " + e.getMessage());
            return null;
        }
    }

    /**
     * Loads tasks from the file and returns them as an ArrayList.
     * Parses each line of the file and reconstructs tasks based on the saved format.
     *
     * @return An ArrayList of Task objects loaded from the file.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> list = new ArrayList<>();
        File file = new File(path);
        if (!file.exists()) {
            return list;
        }
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                String[] parts = line.split(" \\| ");
                if (parts.length < 3) {
                    System.out.println("Invalid record" + line);
                    continue;
                }
                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];

                switch (type) {
                case "T":
                    list.add(new Todo(description, isDone));
                    break;
                case "D":
                    list.add(new Deadline(description, setTime(parts[3]), isDone));
                    break;
                case "E":
                    list.add(new Event(description, setTime(parts[3]), setTime(parts[4]), isDone));
                    break;
                default:
                    System.out.println("Unknown task type" + type + line);
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No existing files, pls add new tasks :)");
        } catch (Exception e) {
            System.out.println("File is corrupted");
        }
        return list;
    }
}
