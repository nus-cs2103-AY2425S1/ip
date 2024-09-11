package skywalker.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import skywalker.parser.Parser;
import skywalker.task.Task;
import skywalker.task.TaskList;

/**
 * The Storage class handles reading and writing tasks to a file.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a Storage object with the given file path.
     *
     * @param filePath The file path where tasks will be stored.
     */
    public Storage(String filePath) {
        assert filePath != null : "File path should not be null";
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file and returns them as an ArrayList.
     *
     * @return An ArrayList of tasks loaded from the file.
     * @throws IOException If there is an error reading from the file.
     */
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (file.exists()) {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = Parser.parseTaskFromFileString(line);
                tasks.add(task);
            }
            scanner.close();
        }
        return tasks;
    }

    /**
     * Saves the list of tasks to the file.
     *
     * @param tasks The TaskList containing tasks to save.
     * @throws IOException If there is an error writing to the file.
     */
    public void save(TaskList tasks) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        for (Task task : tasks.getTasks()) {
            writer.write(Parser.taskToFileString(task) + "\n");
        }
        writer.close();
    }
}
