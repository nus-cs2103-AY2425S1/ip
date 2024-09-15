package rudolf.storage;

import rudolf.task.Deadline;
import rudolf.task.Event;
import rudolf.task.Task;
import rudolf.task.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles the loading and saving of tasks to a file in the Rudolf application.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path to the file where tasks will be stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the specified file.
     * If the file does not exist, it creates a new file and returns an empty task list.
     * If the file exists, it reads the tasks from the file and returns them as a list.
     *
     * @return A list of tasks loaded from the file.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public List<Task> load() throws IOException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
            return tasks;
        }

        List<String> lines = Files.readAllLines(Paths.get(filePath));
        for (String line : lines) {
            String[] parts = line.split(" \\| ");
            boolean isDone = parts[1].equals("1");
            try {
                switch (parts[0]) {
                case "T":
                    tasks.add(new ToDo(parts[2], isDone));
                    break;
                case "D":
                    tasks.add(new Deadline(parts[2], parts[3], isDone));
                    break;
                case "E":
                    tasks.add(new Event(parts[2], parts[3], parts[4], isDone));
                    break;
                default:
                    System.out.println("Warning: Corrupted task found in file. Skipping line.");
                }
            } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
                System.out.println("Warning: Corrupted task data. Skipping line.");
            }
        }
        return tasks;
    }

    /**
     * Saves the list of tasks to the file specified by the file path.
     * Each task is written to the file in a specific format.
     *
     * @param tasks The list of tasks to be saved to the file.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void save(List<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task task : tasks) {
            fw.write(task.toFileString() + System.lineSeparator());
        }
        fw.close();
    }
}

