package bottle;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import bottle.task.Task;

/**
 * The Storage class handles reading and writing tasks to and from a file.
 * It ensures that the file exists and provides methods to load and save tasks.
 */
public class Storage {
    private static final String DELIMITER = "\\|"; // Unused variable, consider removing if not needed
    /**
     * The file path where tasks are stored.
     */
    private final String filePath;
    /**
     * The parser for parsing task data.
     */
    private final Parser parser;

    /**
     * Instantiates a new Storage.
     *
     * @param filePath the file path for storing tasks
     */
    public Storage(String filePath) {
        assert !filePath.isEmpty() : "filePath cannot be empty";
        this.filePath = filePath;
        this.parser = new Parser();
    }

    /**
     * Ensures that the file exists, creating it if necessary.
     */
    private void ensureFileExists() {
        File file = new File(filePath);
        if (!file.exists()) {
            handleMissingFile();
        }
    }

    /**
     * Handles the scenario where the file is missing by creating necessary directories and the file.
     */
    private void handleMissingFile() {
        Path path = Paths.get(filePath);
        try {
            Files.createDirectories(path.getParent());
            Files.createFile(path);
        } catch (IOException e) {
            System.out.println("Error occurred creating file: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the file and returns them as an ArrayList.
     *
     * @return the list of tasks loaded from the file
     */
    public ArrayList<Task> loadTasks() {
        ensureFileExists();
        List<String> lines = readFile();
        return parseTasks(lines);
    }

    /**
     * Reads all lines from the file.
     *
     * @return a list of lines read from the file
     */
    private List<String> readFile() {
        try {
            return Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Parses the given lines into Task objects and returns them as an ArrayList.
     *
     * @param lines the lines to parse
     * @return the list of tasks parsed from the lines
     */
    private ArrayList<Task> parseTasks(List<String> lines) {
        ArrayList<Task> taskList = new ArrayList<>();
        for (String line : lines) {
            assert !line.isEmpty() : "Input cannot be empty!";
            Task task = parser.parseTask(line);
            taskList.add(task);
        }
        return taskList;
    }

    /**
     * Saves the provided task list to the file.
     *
     * @param taskList the list of tasks to save
     */
    public void saveTasks(ArrayList<Task> taskList) {
        ensureFileExists();
        writeTasksToFile(taskList);
    }

    /**
     * Writes the tasks to the file, each task on a new line.
     *
     * @param taskList the list of tasks to write
     */
    private void writeTasksToFile(ArrayList<Task> taskList) {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            for (Task task : taskList) {
                fileWriter.write(task.toSaveFormat() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
}
