package ip.derrick;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Storage class to store and load the TaskList.
 */
public class Storage {

    private static final String FOLDER_PATH = Paths.get(System.getProperty("user.home"), "ip", "data").toString();
    private static final String FILE_NAME = "DATA.TXT";
    private static final Path FILE_PATH = Paths.get(FOLDER_PATH, FILE_NAME);

    public Storage() {
        createDirectory();
    }

    /**
     * Creates the directory and file if they do not exist.
     */
    private void createDirectory() {
        Path folderPath = Paths.get(FOLDER_PATH);

        try {
            if (!Files.exists(folderPath)) {
                Files.createDirectories(folderPath);
            }

            if (!Files.exists(FILE_PATH)) {
                Files.createFile(FILE_PATH);
            }
        } catch (IOException e) {
            System.out.println("Error while creating directory or file: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the file, or returns an empty list if the file is empty.
     *
     * @return ArrayList&lt;Task&gt; the list of tasks loaded from the file.
     */
    public ArrayList<Task> loadTasksFromFile() {
        ArrayList<Task> tasks = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(FILE_PATH)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];

                Task task = createTaskFromData(type, isDone, description, parts);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error while loading tasks from file: " + e.getMessage());
        }

        return tasks;
    }

    /**
     * Creates a task based on the type and the provided data.
     *
     * @param type The task type (T, D, E).
     * @param isDone Boolean indicating if the task is completed.
     * @param description The task description.
     * @param parts Additional data required for specific task types.
     * @return Task The constructed task, or null if an error occurs.
     */
    private Task createTaskFromData(String type, boolean isDone, String description, String[] parts) {
        Task task = null;
        switch (type) {
        case "T":
            task = new Todo(description);
            break;
        case "D":
            String by = parts[3];
            task = new Deadline(description, by);
            break;
        case "E":
            String start = parts[3];
            String end = parts[4];
            task = new Event(description, start, end);
            break;
        }

        if (task != null && isDone) {
            task.setMark();
        }

        return task;
    }

    /**
     * Saves the given TaskList to the file.
     *
     * @param list The TaskList to save.
     */
    public void saveTasksToFile(TaskList list) {
        try (BufferedWriter writer = Files.newBufferedWriter(FILE_PATH)) {
            for (Task task : list.output()) {
                writer.write(task.changeFormat() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error while saving tasks to file: " + e.getMessage());
        }
    }
}
