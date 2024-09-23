package rapgod.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import rapgod.tasks.Task;
import rapgod.utils.Parser;

/**
 * Manages task data stored in a file, providing functionality to read from and update the task data file.
 * This class handles file operations to persist task data, validates task entry formats, and maintains
 * a list of tasks.
 */
public class DataManager {

    /**
     * Regular expression pattern to validate the format of task entries.
     * The format includes an index, task type, completion status, and optional details.
     */
    private static final Pattern TASK_PATTERN = Pattern.compile(
            "^\\d+\\. \\[[TDE]\\] \\[[ X]\\] .*(\\(from: .+?\\)|\\(to: .+?\\)|\\(by: .+?\\))?$"
    );
    /**
     * Path to the file where task data is stored.
     */
    @SuppressWarnings("checkstyle:AbbreviationAsWordInName")
    private final Path DATA_FILE_PATH;
    private final TaskList taskList;

    /**
     * Constructs a DataManager to manage task data stored at the specified file path.
     * Initializes the task list by reading existing data from the file.
     *
     * @param path The path to the file where task data is stored.
     */
    public DataManager(String path) {
        this.DATA_FILE_PATH = Path.of(path);
        this.taskList = new TaskList(this.readMemory());
    }

    /**
     * Reads task data from the specified file and returns it as a list of {@link Task} objects.
     * If the file does not exist, an empty file is created. Each line in the file is validated
     * against the expected format, and valid tasks are added to the list. If a line does not
     * match the format, an error message is printed to the console. The current list of tasks
     * is returned, and any issues during file reading are logged.
     *
     * @return A list of {@link Task} objects read from the file. If the file is missing or empty,
     *         an empty list is returned.
     */
    public List<Task> readMemory() {
        List<Task> list = new ArrayList<>();
        try {
            loadLinesToList(list, DATA_FILE_PATH);
        } catch (IOException e) {
            System.err.println("Error fetching data ---> " + e.getMessage());
        }
        return list;
    }

    /**
     * Loads lines from the specified file into the given list of {@link Task} objects.
     * Validates each line against a predefined task pattern. If a line matches the expected
     * format, it is parsed and added to the list; otherwise, an error message is printed to
     * the console. If the file does not exist, it creates the necessary directories and an
     * empty file at the specified path.
     *
     * @param list The list to which valid {@link Task} objects are added.
     * @param dataFilePath The path of the file from which task data is read.
     * @throws IOException If an error occurs during file reading or writing.
     */
    private void loadLinesToList(List<Task> list, Path dataFilePath) throws IOException {
        if (Files.exists(dataFilePath)) {
            List<String> lines = Files.readAllLines(dataFilePath);
            for (String line : lines) {
                if (TASK_PATTERN.matcher(line).matches()) {
                    Task task = Parser.parseToTask(line);
                    list.add(task);
                } else {
                    System.err.println("Corrupted line found ---> " + line);
                }
            }
        } else {
            // Create an empty file if it does not exist
            Files.createDirectories(dataFilePath.getParent()); // Ensure the parent directories exist
            Files.createFile(dataFilePath); // Create the empty file
        }
    }

    /**
     * Updates the task data file with the current list of tasks.
     * Converts the list of tasks to a string and writes it to the file, overwriting the existing content.
     */
    public void updateMemory() {
        StringBuilder updatedList = new StringBuilder();
        for (int i = 0; i < this.taskList.getList().size(); i++) {
            updatedList.append(String.format("%d. %s\n", i + 1, this.taskList.getList().get(i).toString()));
        }

        try {
            Files.writeString(DATA_FILE_PATH, updatedList.toString());
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }

    /**
     * Returns the task list managed by this DataManager.
     *
     * @return The {@link TaskList} instance containing the current list of tasks.
     */
    public TaskList getTaskList() {
        return this.taskList;
    }
}
