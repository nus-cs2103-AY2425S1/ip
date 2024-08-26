package friday.util;

import friday.task.Deadline;
import friday.task.Event;
import friday.task.Task;
import friday.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles the loading and saving of tasks to and from a file.
 */
public class Storage {
    private static final String FILE_PATH = "data/friday.txt";

    /**
     * Loads tasks from the storage file.
     *
     * @return An ArrayList of tasks loaded from the file.
     * @throws IOException If an error occurs while reading the file.
     */
    public ArrayList<Task> loadTasks() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(FILE_PATH);
        File currentDir = new File(System.getProperty("user.dir"));

        if (currentDir.getName().equals("text-ui-test")) { // Locate to the right directory
            file = new File("../data/friday.txt");
        }
        if (!file.exists()) { // If file is still not found, create the file and directory
            file.getParentFile().mkdirs();
            file.createNewFile();
        } else {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String[] taskData = sc.nextLine().split(" \\| ");
                if ((taskData.length < 3) || !taskData[1].chars().allMatch(Character::isDigit)) continue;
                Task task = parseTask(taskData);
                if (task != null && taskData[1].equals("1")) {
                    task.markAsDone();
                }
                tasks.add(task);
            }
            sc.close();
        }
        return tasks;
    }

    /**
     * Parses a string array representing task data and creates a corresponding {@link Task} object.
     * The array should be in the format where the first element indicates the type of task
     * ("T" for Todo, "D" for Deadline, "E" for Event) and subsequent elements provide
     * the necessary details for constructing the task.
     *
     * @param taskData A string array containing task data, where the first element specifies
     *                 the task type and the remaining elements provide the task's details.
     * @return The {@link Task} object created from the given data, or {@code null} if the data
     *         does not match any recognized task type.
     */
    private Task parseTask(String[] taskData) {
        Task task = null;

        switch (taskData[0]) {
        case "T":
            task = new Todo(taskData[2]);
            break;
        case "D":
            task = new Deadline(taskData[2], taskData[3]);
            break;
        case "E":
            task = new Event(taskData[2], taskData[3], taskData[4]);
            break;
        default:
            System.out.println("\tCorrupted data found: "  + String.join(" | ", taskData));
        }
        return task;
    }

    /**
     * Saves the list of tasks to the storage file.
     *
     * @param tasks The ArrayList of tasks to be saved.
     * @throws IOException If an error occurs while writing to the file.
     */
    public void saveTasks(ArrayList<Task> tasks) throws IOException {
        File currentDir = new File(System.getProperty("user.dir"));
        FileWriter writer;

        if (currentDir.getName().equals("ip")) {
            writer = new FileWriter(FILE_PATH);
        }
        else {
            currentDir = new File("../data/friday.txt");
            if (!currentDir.exists()) { // If file is still not found, create the file and directory
                currentDir.getParentFile().mkdirs();
                currentDir.createNewFile();
            }
            writer = new FileWriter("../data/friday.txt");
        }

        for (Task task : tasks) {
            String taskData = task.toFileFormat();
            writer.write(taskData + "\n");
        }
        writer.close();
    }
}
