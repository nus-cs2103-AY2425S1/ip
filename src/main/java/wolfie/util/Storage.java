package wolfie.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import wolfie.task.Deadline;
import wolfie.task.Event;
import wolfie.task.Task;
import wolfie.task.TaskList;
import wolfie.task.Todo;

/**
 * Represents a storage object that handles the loading and saving of tasks to a file.
 */
public class Storage {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file at the specified file path.
     *
     * @return List of tasks loaded from the file.
     * @throws IOException If an error occurs while reading the file.
     */
    public List<Task> load() throws IOException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(" \\| ");
                    String type = parts[0];
                    boolean isDone = parts[1].equals("1");
                    String description = parts[2];
                    switch (type) {
                    case "T":
                        tasks.add(new Todo(description, isDone));
                        break;
                    case "D":
                        LocalDateTime by = LocalDateTime.parse(parts[3], DATE_TIME_FORMATTER);
                        tasks.add(new Deadline(description, by, isDone));
                        break;
                    case "E":
                        LocalDateTime from = LocalDateTime.parse(parts[3], DATE_TIME_FORMATTER);
                        LocalDateTime to = LocalDateTime.parse(parts[4], DATE_TIME_FORMATTER);
                        tasks.add(new Event(description, from, to, isDone));
                        break;
                    default:
                        throw new IOException("Unknown task type in file");
                    }
                }
            } catch (FileNotFoundException e) {
                System.err.println("Error: File not found - " + filePath);
            } catch (IOException e) {
                System.err.println("Error: Unable to access file - " + filePath);
            }
        }
        return tasks;
    }

    /**
     * Saves the tasks in the task list to the file at the specified file path.
     *
     * @param taskList Task list to save.
     * @throws IOException If an error occurs while writing to the file.
     */
    public void save(TaskList taskList) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : taskList.getTasks()) {
                writer.write(task.toFileFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error: Unable to write to file - " + filePath);
            throw e;
        }
    }
}
