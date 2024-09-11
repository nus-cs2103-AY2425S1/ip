package storage;

import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Represents the storage of tasks in a file.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a Storage with the specified file path.
     *
     * @param filePath The file path of the file to store the tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        assert filePath != null && !filePath.isEmpty() : "File path should not be null or empty";
    }

    /**
     * Returns the file path of the storage.
     *
     * @return The file path of the storage.
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * Handles the input string and returns the corresponding task.
     *
     * @param input The input string to be handled.
     * @return The task corresponding to the input string.
     */
    public Task handleInput(String input) {
        assert input != null && !input.isEmpty() : "Input string should not be null or empty";
        String taskType = input.split(" ")[0];
        return switch (taskType) {
            case "T" -> new ToDo(input.substring(4), input.split(" ")[1].equals("0"));
            case "D" -> new Deadline(input.substring(4), input.split(" ")[1].equals("0"));
            case "E" -> new Event(input.substring(4), input.split(" ")[1].equals("0"));
            default -> null;
        };
    }

    /**
     * Loads the tasks from the file and returns the list of tasks.
     *
     * @return The list of tasks loaded from the file.
     * @throws IOException If an error occurs during loading of the tasks.
     */
    public List<Task> load() throws IOException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            File directory = new File("./data");
            if (!directory.exists()) {
                directory.mkdir();
            }
            file = new File(directory,filePath.substring(5));
            file.createNewFile();
            System.out.println(" File not found. Creating new file...");
            return tasks;
        }

        if (!isFileUncorrupted(file)) {
            throw new IOException(" File is corrupted");
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = this.handleInput(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        }

        return tasks;
    }

    /**
     * Saves the list of tasks to the file.
     *
     * @param tasks The list of tasks to be saved to the file.
     * @throws IOException If an error occurs during saving of the tasks.
     */
    public void save(List<Task> tasks) throws IOException {
        assert tasks != null : "Tasks list should not be null";
        try (FileWriter fw = new FileWriter(filePath)) {
            for (Task task : tasks) {
                fw.write(task.writeToFile());
                fw.write("\n");
                fw.flush();
            }
        }
    }

    /**
     * Checks if the file is corrupted.
     *
     * @param file The file to be checked.
     * @return True if the file is not corrupted, false otherwise.
     */
    public boolean isFileUncorrupted(File file) {
        Pattern TODO_PATTERN = Pattern.compile("^T\\s+\\d\\s+\\w+.*$");
        Pattern EVENT_PATTERN = Pattern.compile("^E\\s+\\d\\s+\\w+.*\\s+/from\\s+\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|30)\\s+/to\\s+\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|30)$");

        Pattern DEADLINE_PATTERN = Pattern.compile("^D\\s+\\d\\s+\\w+.*\\s+/by\\s+\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|30)$");
        assert file != null : "File object should not be null";
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!TODO_PATTERN.matcher(line).matches() &&
                        !EVENT_PATTERN.matcher(line).matches() &&
                        !DEADLINE_PATTERN.matcher(line).matches()) {
                    return false;
                }
            }
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}
