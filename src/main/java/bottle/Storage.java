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
        File file = new File(filePath);
        ArrayList<Task> taskList = new ArrayList<>();
        if (!file.exists()) {
            handleMissingFile();
        } else {
            try {
                List<String> lines = Files.readAllLines(Paths.get(filePath));
                for (String line : lines) {
                    String[] parts = line.split("\\|");
                    assert parts.length > 0 : "input cannot be empty!";
                    Task task;
                    task = parser.parseTask(line);
                    taskList.add(task);
                }
            } catch (IOException e) {
                System.out.println("Error reading file");
            }
        }
        return taskList;
    }

    /**
     * Save tasks.
     *
     * @param taskList the task list
     */
    public void saveTasks(ArrayList<Task> taskList) {
        File file = new File(filePath);
        if (!file.exists()) {
            handleMissingFile();
        }
        try {
            FileWriter fileWriter = new FileWriter(file);
            for (Task task : taskList) {
                fileWriter.write(task.toSaveFormat() + System.lineSeparator());
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error saving file");
        }
    }
}
