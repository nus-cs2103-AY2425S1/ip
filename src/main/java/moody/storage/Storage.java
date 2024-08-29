package moody.storage;

import moody.tasks.Deadline;
import moody.tasks.Event;
import moody.tasks.Task;
import moody.tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles loading and saving tasks to and from a file.
 * The Storage class provides methods to read tasks from a file and save tasks to a file.
 */
public class Storage {
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    protected String filePath;

    /**
     * Creates a Storage object with the specified file path.
     * Initializes the Storage to read from and write to the given file path.
     *
     * @param filePath The path of the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file specified by the file path.
     * Reads the file and parses the tasks, returning a list of Task objects.
     *
     * @return An ArrayList of tasks loaded from the file.
     * @throws FileNotFoundException If the file specified by the file path is not found.
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            return tasks;
        }

        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(" \\| ");
            String taskType = parts[0];
            boolean isTaskDone = parts[1].equals("1");
            Task task;

            switch (taskType) {
                case "T":
                    task = new Todo(parts[2].trim());
                    break;
                case "D":
                    task = new Deadline(parts[2].trim(), LocalDateTime.parse(parts[3].trim(), INPUT_FORMATTER));
                    break;
                case "E":
                    task = new Event(parts[2].trim(), LocalDateTime.parse(parts[3].trim(), INPUT_FORMATTER),
                            LocalDateTime.parse(parts[4].trim(), INPUT_FORMATTER));
                    break;
                default:
                    continue;
            }

            if (isTaskDone) {
                task.markAsDone();
            }

            tasks.add(task);
        }

        scanner.close();
        return tasks;
    }

    /**
     * Saves the specified list of tasks to the file specified by the file path.
     * Writes each task to the file in a format suitable for loading later.
     *
     * @param tasks The list of tasks to be saved to the file.
     * @throws IOException If an I/O error occurs during the saving process.
     */
    public void save(ArrayList<Task> tasks) throws IOException {
        File directory = new File(filePath).getParentFile();
        if (!directory.exists()) {
            directory.mkdirs();
        }

        FileWriter fileWriter = new FileWriter(filePath);

        for (Task task : tasks) {
            fileWriter.write(task.toFileFormat() + System.lineSeparator());
        }
        fileWriter.close();
    }
}
