package Alex.Storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import Alex.Exceptions.AlexException;
import Alex.Task.Deadline;
import Alex.Task.Event;
import Alex.Task.Task;
import Alex.Task.TaskType;
import Alex.Task.Todo;



/**
 * Manages the loading and saving of tasks from/to a file.
 */
public class Storage {

    private String filePath;

    /**
     * Constructs a Storage object with the given file path.
     * @param filePath The path to the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        createDataDirectory();
    }

    /**
     * Loads tasks from the file specified by the file path.
     * @return An ArrayList of tasks loaded from the file.
     * @throws AlexException If there is an error loading tasks from the file.
     */
    public ArrayList<Task> load() throws AlexException {
        File file = new File(filePath);
        ArrayList<Task> tasks = new ArrayList<>();
        if (!file.exists()) {
            return tasks; // No tasks to load if file doesn't exist
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                if (parts.length < 2) {
                    continue; // Skip invalid lines
                }

                TaskType type = TaskType.valueOf(parts[0]);
                boolean isDone = parts[1].equals("1");
                String description = parts[2];

                Task task = null;
                switch (type) {
                case TODO:
                    task = new Todo(description);
                    break;
                case DEADLINE:
                    if (parts.length == 4) {
                        task = new Deadline(description, parts[3]);
                    }
                    break;
                case EVENT:
                    if (parts.length == 5) { // Adjusted length to 5 for Event (with from and to)
                        task = new Event(description, parts[3], parts[4]);
                    }
                    break;
                default:
                    throw new AlexException("Unexpected task type: " + type);
                }

                if (task != null) {
                    if (isDone) {
                        task.markAsDone();
                    }
                    tasks.add(task);
                }
            }
        } catch (IOException | IllegalArgumentException e) {
            throw new AlexException("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Saves the given tasks to the file specified by the file path.
     * @param tasks The ArrayList of tasks to save to the file.
     * @throws AlexException If there is an error saving tasks to the file.
     */
    public void save(ArrayList<Task> tasks) throws AlexException {
        File file = new File(filePath);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (Task task : tasks) {
                String line = task.getTaskType() + " | " + (task.isDone ? "1" : "0") + " | " + task.getDescription();
                if (task instanceof Deadline) {
                    line += " | " + ((Deadline) task).by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
                } else if (task instanceof Event) {
                    line += " | " + ((Event) task).getStarttime() + " | " + ((Event) task).getEndtime();
                }
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            throw new AlexException("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Creates the data directory if it does not exist.
     */
    private void createDataDirectory() {
        File dataDir = new File("./data");
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }
    }
}