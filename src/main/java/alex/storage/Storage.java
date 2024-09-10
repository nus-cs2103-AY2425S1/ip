package alex.storage;

import alex.task.Deadline;
import alex.task.Event;
import alex.task.Task;
import alex.task.Todo;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;


/**
 * Represents the storage where the list of tasks are stored. A Storage object corresponds to
 * an inventory represented by strings referring to the directory and the data file.
 */
public class Storage {
    private ArrayList<Task> tasks;

    public Storage(String directory, String file) {
        createDirectory(directory);
        loadTasksFromFile(file);
    }
    /**
     * Creates a directory if the directory doesn't exist.
     *
     * @param directory the directory to create
     */
    private void createDirectory(String directory) {
        File folder = new File(directory);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }
    /**
     * Returns the list of tasks that are in the specified file.
     * Saves the current list of tasks into the specified file.
     * Creates a new file if the file doesn't exist.
     *
     * @param file data file.
     * @return list of tasks.
     */
    public ArrayList<Task> loadTasksFromFile(String file) {
        ArrayList<Task> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String desc;
            while ((desc = reader.readLine()) != null) {
                Task task;
                if (desc.startsWith("[T]")) {
                    String details = desc.substring(6).trim();
                    task = new Todo(details);
                } else if (desc.startsWith("[D]")) {
                    String details = desc.substring(6);
                    String[] info = details.split("//");
                    String item = info[0].trim();
                    String dueInter = info[1].substring(4).trim();
                    LocalDate dueDate = LocalDate.parse(dueInter);
                    task = new Deadline(item, dueDate);
                } else {
                    String details = desc.substring(6);
                    String[] info = details.split("//");
                    String item = info[0].trim();
                    String startInter = info[1].substring(6).trim();
                    LocalDate start = LocalDate.parse(startInter);
                    String dueInter = info[2].substring(4).trim();
                    LocalDate dueBy = LocalDate.parse(dueInter);
                    task = new Event(item, start, dueBy);
                }
                task.isDone = desc.substring(4,5).equals("X") ? true : false;
                list.add(task);
            }
        } catch (IOException e) {
            try {
                File dataFile = new File(file);
                dataFile.createNewFile();
            } catch (IOException ee) {
                System.err.println("Error reading tasks from file: " + ee.getMessage());
            }
        }
        tasks = list;
        return list;
    }

    /**
    * Returns string representing whether the method succeeded or failed.
    * Saves the current list of tasks into the specified file.
    *
    * @param file data file.
    * @return success or fail.
    */
    public String saveTasksToFile(String file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Task task : tasks) {
                writer.write(task.toBeSavedAsData());
                writer.newLine();
            }
            return "success";
        } catch (IOException e) {
            System.err.println("Error writing tasks to file: " + e.getMessage());
            return "fail";
        }
    }
}
