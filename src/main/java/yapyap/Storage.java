package yapyap;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * The storage class handles reading and writing task information to a file.
 * It provides functionality to load tasks and save tasks from a file.
 */
public class Storage {
    private String filePath;

    /**
     * Creates a Storage object with the specified file path.
     *
     * @param filePath The path of the file where tasks will be saved and loaded from.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the specified file. If the file does not exist, an empty task list is returned.
     * Each line in the file is expected to be in the format:
     * TaskType | isDone | description [| additional details...].
     *
     * @return An ArrayList of tasks loaded from the file.
     * @throws YapperBotException If there is an error loading tasks from the file.
     */
    public ArrayList<Task> loadTasks() throws YapperBotException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Path datafilePath = Paths.get(filePath);
            if (!Files.exists(datafilePath)) {
                return tasks;
            }

            BufferedReader reader = Files.newBufferedReader(datafilePath);
            String line;
            while ((line = reader.readLine()) != null) {
                String[] taskInfo = line.split(" \\| ");
                String type = taskInfo[0];
                boolean isDone = taskInfo[1].equals("1");
                String description = taskInfo[2];

                switch (type) {
                case "T":
                    tasks.add(new Todo(description, isDone));
                    break;
                case "D":
                    String by = taskInfo[3];
                    tasks.add(new Deadline(description, by, isDone));
                    break;
                case "E":
                    String from = taskInfo[3];
                    String to = taskInfo[4];
                    tasks.add(new Event(description, from, to, isDone));
                    break;
                default:
                    throw new YapperBotException("Unrecognized task type '" + type + "'. Task cannot be added.");
                }
            }
            reader.close();
        } catch (IOException e) {
            throw new YapperBotException("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Saves the current list of tasks to the specified file.
     * Each task is converted to a save format string as specified in the relevant task class and written to the file.
     *
     * @param tasks The list of tasks to be saved to the file.
     */
    public void saveTasks(ArrayList<Task> tasks) {
        try {
            Path dataFilePath = Paths.get(filePath);
            Files.createDirectories(dataFilePath.getParent());
            FileWriter fw = new FileWriter(dataFilePath.toString());

            for (Task task : tasks) {
                fw.write(task.toSaveFormat() + System.lineSeparator());
            }
            fw.close();

        } catch (IOException e) {
            System.out.println("Unable to save task(s) to file, error occurred: " + e.getMessage());
        }
    }
}

