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
 * The type Storage.
 */
public class Storage {
    private static final String DELIMITER = "\\|";
    /**
     * The File path.
     */
    private final String filePath;
    /**
     * The Parser.
     */
    private final Parser parser;

    /**
     * Instantiates a new Storage.
     *
     * @param filePath the file path
     */
    public Storage(String filePath) {
        assert !filePath.isEmpty() : "filePath cannot be empty";
        this.filePath = filePath;
        this.parser = new Parser();
    }

    /**
     * Ensure file exists.
     */
    private void ensureFileExists() {
        File file = new File(filePath);
        if (!file.exists()) {
            handleMissingFile();
        }
    }

    /**
     * Handle missing file.
     */
    private void handleMissingFile() {
        Path path = Paths.get(filePath);
        try {
            Files.createDirectories(path.getParent());
            Files.createFile(path);
        } catch (IOException e) {
            System.out.println("Error occurred creating file");
        }
    }

    /**
     * Load tasks array list.
     *
     * @return the array list
     */
    public ArrayList<Task> loadTasks() {
        ensureFileExists();
        List<String> lines = readFile();
        return parseTasks(lines);
    }

    /**
     * Read file list.
     *
     * @return the list
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
     * Parse tasks array list.
     *
     * @param lines the lines
     * @return the array list
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
     * Save tasks.
     *
     * @param taskList the task list
     */
    public void saveTasks(ArrayList<Task> taskList) {
        ensureFileExists();
        writeTasksToFile(taskList);
    }

    /**
     * Write tasks to file.
     *
     * @param taskList the task list
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
