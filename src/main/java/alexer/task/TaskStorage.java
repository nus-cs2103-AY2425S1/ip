package alexer.task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class manages the serialisation and
 * de-serialisation of tasks to file and vice-versa.
 *
 * @author sayomaki
 */
public class TaskStorage {
    /** The save file for tasks **/
    private final String saveFile;

    public TaskStorage(String saveFile) {
        this.saveFile = saveFile;
    }

    /**
     * Converts a task string into a task instance.
     *
     * @param taskString the provided task string (from save file)
     * @return the task instance created
     */
    public Task parseTask(String taskString) {
        Task task;
        if (taskString.startsWith("T")) {
            task = Todo.fromTaskString(taskString);
        } else if (taskString.startsWith("D")) {
            task = Deadline.fromTaskString(taskString);
        } else if (taskString.startsWith("E")) {
            task = Event.fromTaskString(taskString);
        } else {
            System.out.println("Invalid task type found, skipping: " + taskString);
            return null;
        }

        if (task == null) {
            System.out.println("Failed to load task: " + taskString);
            return null;
        }

        return task;
    }

    /**
     * Loads all the tasks from the given save file,
     * defined in the constructor.
     *
     * @return a list of Task objects
     */
    public List<Task> loadTasks() {
        List<Task> taskList = new ArrayList<>();
        File file = new File(saveFile);
        if (!file.isFile()) {
            System.out.println("Failed to find task save file, not loading any tasks.");
            return taskList;
        }

        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNext()) {
                String taskString = scanner.nextLine();
                Task task = parseTask(taskString);

                if (task != null) {
                    taskList.add(task);
                }
            }

            System.out.println("Loaded saved tasks from disk!");

            return taskList;
        } catch (IOException e) {
            System.out.println("Error! Failed to load tasks!");
            return taskList;
        }
    }

    /**
     * Saves all the tasks to the hard disk in a file.
     *
     * @return Whether the save is successful
     */
    public boolean saveTasks(List<Task> taskList) {
        File file = new File(saveFile);
        // ensure our data directory exists first
        file.getParentFile().mkdir();

        try {
            FileWriter fileWriter = new FileWriter(saveFile);

            StringBuilder output = new StringBuilder();
            for (Task task : taskList) {
                output.append(task.toTaskString()).append("\n");
            }

            fileWriter.write(output.toString());
            fileWriter.close();
            return true;
        } catch (IOException e) {
            System.out.println("Error! Failed to save tasks!");
            return false;
        }
    }
}
