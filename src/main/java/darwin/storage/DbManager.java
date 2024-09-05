package darwin.storage;

import darwin.exception.IllegalTaskArgumentException;
import darwin.exception.IllegalTaskTypeException;
import darwin.task.Task;
import darwin.parser.TaskFactory;

import java.io.*;
import java.util.ArrayList;

/**
 * DbManager class to manage the database of tasks.
 */
public class DbManager {
    private final String path;

    public DbManager(String path) {
        this.path = path;
    }

    private void ensureFileExists(String filePath) {
        File file = new File(filePath);
        try {
            if (!file.exists()) {
                file.createNewFile();
                System.out.println("File created: " + filePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the list of tasks from the database.
     * @return list of tasks from the database
     */
    public ArrayList<Task> getTasks() {
        this.ensureFileExists(this.path);
        ArrayList<Task> taskList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = TaskFactory.dbToTask(line);
                taskList.add(task);
            }
        } catch (IOException e) {
            System.out.println("Failed to load tasks from file.");
        } catch (IllegalTaskTypeException | IllegalTaskArgumentException e) {
            System.out.println("Parsing from file failed due to unexpected format.");
        }
        return taskList;
    }

    /**
     * Writes the list of tasks to the database.
     * @param taskList list of tasks to write to the database
     */
    public void writeTasks(ArrayList<Task> taskList) {
        this.ensureFileExists(this.path);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.path))) {
            for (Task task : taskList) {
                writer.write(task.toCsv());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Failed to write tasks to file");
        }
    }
}
