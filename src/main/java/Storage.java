import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Storage class handles the loading and saving of tasks to a file
 */
public class Storage {
    private final String filePath;

    // Constructor to initialise the storage with the given file path
    public Storage(String filepath) {
        this.filePath = filepath;
    }

    /**
     * Loads tasks from the file at the specified file path
     *
     * @return Lists of tasks loaded from the file
     * @throws IOException if an I/O error occurs during file reading
     */
    public List<Task> loadTasks() throws IOException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            file.getParentFile().mkdirs(); //Create directory if file does not exist
            file.createNewFile(); //Create file if file does not exist
            return tasks;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    String[] parts = line.split(" \\| ");
                    String type = parts[0];
                    boolean isDone = parts[1].equals("1");
                    String description = parts[2];

                    Task task = null;
                    switch (type) {
                        case "T":
                            task = new ToDo(description);
                            break;
                        case "D":
                            String by = parts[3];
                            task = new Deadline(description, by);
                            break;
                        case "E":
                            String from = parts[3];
                            String to = parts[4];
                            task = new Event(description, from, to);
                            break;
                        default:
                            throw new SageException("Unknown task type: " + type);
                    }
                    if (isDone) {
                        task.markAsDone();
                    }
                    tasks.add(task);
                } catch (SageException | ArrayIndexOutOfBoundsException e) {
                    // Handle corrupted lines or invalid task format
                    System.out.println("Skipping corrupted task entry: " + line);
                }
            }
        }
        return tasks;
    }

    /**
     * Save the lists of tasks to the file at the specified file path
     *
     * @param tasks the list of tasks to be saved
     */
    public void saveTasks(List<Task> tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : tasks) {
                writer.write(task.toFileFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
