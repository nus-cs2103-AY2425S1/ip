package lolo.storage;

import lolo.LoloException;
import lolo.task.Deadline;
import lolo.task.Event;
import lolo.task.Task;
import lolo.task.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles the loading and saving of tasks from/to a file.
 * The tasks are represented in a specific format for persistence.
 */
public class Storage {
    private String filePath;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path to the file where tasks are saved and loaded from.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file specified by the file path.
     * Creates the file and directory if they do not exist.
     * Parses the file content to reconstruct the tasks.
     *
     * @return An ArrayList of tasks loaded from the file.
     * @throws LoloException If there is an error loading tasks or parsing the file content.
     */
    public ArrayList<Task> load() throws LoloException {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            File file = new File(filePath);
            if (!file.exists()) {
                // Create the file if it doesn't exist
                File directory = new File(file.getParent());
                if (!directory.exists()) {
                    directory.mkdirs(); // Create the directories if they don't exist
                }
                file.createNewFile();
            } else {
                // Read the file content and load tasks
                List<String> lines = Files.readAllLines(Paths.get(filePath));
                for (String line : lines) {
                    String[] parts = line.split(" \\| ");
                    String type = parts[0];
                    boolean isDone = parts[1].equals("1");
                    String description = parts[2];

                    Task task;
                    switch (type) {
                        case "T":
                            task = new ToDo(description);
                            break;
                        case "D":
                            LocalDateTime by = LocalDateTime.parse(parts[3], FORMATTER);
                            task = new Deadline(description, by);
                            break;
                        case "E":
                            LocalDateTime from = LocalDateTime.parse(parts[3], FORMATTER);
                            LocalDateTime to = LocalDateTime.parse(parts[4], FORMATTER);
                            task = new Event(description, from, to);
                            break;
                        default:
                            throw new LoloException("Unknown task type in file.");
                    }

                    if (isDone) {
                        task.markAsDone();
                    }

                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            throw new LoloException("Error loading tasks from file.");
        } catch (Exception e) {
            throw new LoloException("Error parsing date/time format.");
        }

        return tasks;
    }

    /**
     * Saves the specified list of tasks to the file.
     * Overwrites the existing content in the file.
     *
     * @param tasks The list of tasks to be saved.
     * @throws LoloException If there is an error saving the tasks to the file.
     */
    public void save(ArrayList<Task> tasks) throws LoloException {
        try {
            FileWriter writer = new FileWriter(filePath);

            for (Task task : tasks) {
                writer.write(task.toDataString() + "\n");
            }

            writer.close();
        } catch (IOException e) {
            throw new LoloException("Error saving tasks to file.");
        }
    }
}

