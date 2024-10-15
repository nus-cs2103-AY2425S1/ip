package lunabot.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import lunabot.exception.LunaBotException;
import lunabot.task.Deadline;
import lunabot.task.Event;
import lunabot.task.Task;
import lunabot.task.ToDo;

/**
 * The Storage class handles the reading and writing of tasks to a file.
 * It saves tasks to a file and loads them from the file when the application starts.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path to the file where tasks will be saved and loaded from.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the tasks from the file specified by the file path.
     * If the file or its parent directory does not exist, they will be created.
     *
     * @return An ArrayList of Task objects loaded from the file.
     * @throws LunaBotException If there is an error reading the file or the file format is invalid.
     */
    public ArrayList<Task> load() throws LunaBotException {
        ArrayList<Task> taskList = new ArrayList<>();
        File file = new File(filePath);
        File directory = new File(file.getParent());

        if (!directory.exists()) {
            directory.mkdir();
        }

        if (!file.exists()) {
            return taskList;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] arr = line.split(" \\| ");
                String taskType = arr[0];
                boolean isDone = arr[1].equals("1");
                String description = arr[2];
                LocalDateTime dateTime = null;

                if (arr.length > 3) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    dateTime = LocalDateTime.parse(arr[3], formatter);
                }

                switch (taskType) {
                case "T":
                    taskList.add(new ToDo(description, isDone));
                    break;
                case "D":
                    taskList.add(new Deadline(description, dateTime, isDone));
                    break;
                case "E":
                    LocalDateTime from = LocalDateTime.parse(arr[3], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                    LocalDateTime to = LocalDateTime.parse(arr[4], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                    taskList.add(new Event(description, from, to, isDone));
                    break;
                default:
                    throw new LunaBotException("Invalid task type found in file");

                }
            }
        } catch (IOException e) {
            throw new LunaBotException("Error reading file: " + e.getMessage());
        }
        return taskList;
    }

    /**
     * Saves the current task list to the file specified by the file path.
     * Creates the file and its parent directories if they do not exist.
     *
     * @param taskList The ArrayList of tasks to be saved to the file.
     * @throws LunaBotException If there is an error writing to the file.
     */
    public void save(ArrayList<Task> taskList) throws LunaBotException {
        File file = new File(filePath);
        file.getParentFile().mkdirs();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Task task : taskList) {
                writer.write(task.toFileFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new LunaBotException("Error writing to file: " + e.getMessage());
        }
    }
}
