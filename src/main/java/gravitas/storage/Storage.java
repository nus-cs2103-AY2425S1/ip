package gravitas.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import gravitas.exception.DukeException;
import gravitas.parser.Parser;
import gravitas.task.Task;
import gravitas.tasklist.TaskList;


/**
 * Represents the storage of the task list.
 */
public class Storage {

    private Path filePath;

    /**
     * Constructor for Storage.
     *
     * @param filePath File path to load and save file
     */
    public Storage(String filePath) {
        String home = System.getProperty("user.home");
        this.filePath = Paths.get(home, filePath);

        File f = new File(this.filePath.toString());
        //Create file if needed
        if (!f.isFile()) {
            try {
                Files.createFile(this.filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Load the tasks from the storage to the task list.
     *
     * @return ArrayList of tasks
     * @throws DukeException If there is an error in loading the tasks
     */
    public ArrayList<Task> loadTasks() throws DukeException {

        if (new File(this.filePath.toString()).isFile()) {
            try {
                List<String> lines = Files.readAllLines(this.filePath);
                ArrayList<Task> tasks = new ArrayList<>();
                for (String line : lines) {
                    String[] frags = line.split("\\s*\\|\\s*");
                    Task t = Parser.parseStringToTask(line);
                    tasks.add(t);
                }
                return tasks;
            } catch (IOException e) {
                throw new DukeException("Error in loading tasks!");
            }
        }
        return new ArrayList<>();
    }

    /**
     * Save the tasks from the task list to the storage.
     *
     * @param tasklist TaskList object that contains the list of tasks
     * @throws DukeException If there is an error in saving the tasks
     */
    public void saveTask(TaskList tasklist) throws DukeException {

        try {
            FileWriter fw = new FileWriter(this.filePath.toString());
            for (int i = 0; i < tasklist.size(); i++) {
                Task task = tasklist.getTask(i);
                fw.write(task.formatData());
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Error in saving tasks!");
        }
    }
}
