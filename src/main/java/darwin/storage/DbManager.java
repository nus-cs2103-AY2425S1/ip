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
    private final String TASK_TB_PATH = "./tasks.txt";
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
        this.ensureFileExists(TASK_TB_PATH);
        ArrayList<Task> taskList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(TASK_TB_PATH))) {
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
        this.ensureFileExists(TASK_TB_PATH);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TASK_TB_PATH))) {
            for (Task task : taskList) {
                writer.write(task.toCsv());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Failed to write tasks to file");
        }
    }

}
