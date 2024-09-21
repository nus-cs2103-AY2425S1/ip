package hannah;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import hannah.task.Deadline;
import hannah.task.Event;
import hannah.task.Task;
import hannah.task.TaskList;
import hannah.task.ToDos;

/**
 * The Storage class handles reading from and writing to the file that stores task data.
 */
public class Storage {
    private String filePath;
    private TaskList taskList;
    private Task task;

    /**
     * Initializes a Storage object with the given file path.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Storage(String filePath) {
        if (filePath == null || filePath.isEmpty()) {
            // Use the current directory and create a default save path if filePath is not provided
            String currentDir = System.getProperty("user.dir");
            this.filePath = currentDir + File.separator + "data" + File.separator + "Hannah.txt";
        } else {
            this.filePath = filePath;
        }
        this.taskList = new TaskList();
        initSaveFile();
    }

    /**
     * Ensures the save file and its directories are created if they do not exist.
     */
    private void initSaveFile() {
        File file = new File(filePath);
        try {
            if (!file.exists()) {
                File parentDir = file.getParentFile();
                if (!parentDir.exists()) {
                    parentDir.mkdirs();
                }
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error creating save file: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the file and returns a TaskList.
     *
     * @return A TaskList containing the tasks loaded from the file.
     */
    public TaskList loadFile() throws IOException {
        File file = new File(filePath);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] taskInfo = line.split(" \\| ");
                Task task = createTask(taskInfo);
                if (task != null) {
                    taskList.addTask(task);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
        return taskList;
    }

    /**
     * Returns the current list of tasks.
     *
     * @return The TaskList containing all tasks.
     */
    public TaskList getTaskList() {
        return taskList;
    }

    /**
     * Creates a Task object based on the given task information.
     *
     * @param taskInfo The array of strings containing task type, status, and details.
     * @return A Task object representing the parsed task, or null if the task type is invalid.
     */
    private Task createTask(String[] taskInfo) {
        String type = taskInfo[0];
        boolean isDone = taskInfo[1].equals("1");

        switch (type) {
        case "T":
            ToDos todoTask = new ToDos(taskInfo[2]);
            if (isDone) {
                todoTask.setDone();
            }
            return todoTask;
        case "D":
            Deadline deadlineTask = new Deadline(taskInfo[2]);
            if (isDone) {
                deadlineTask.setDone();
            }
            deadlineTask.setDeadline(taskInfo[3].substring(3));
            return deadlineTask;
        case "E":
            Event eventTask = new Event(taskInfo[2]);
            if (isDone) {
                eventTask.setDone();
            }
            String duration = taskInfo[3];
            int fromIndex = duration.indexOf("from ");
            int toIndex = duration.indexOf("to ");
            eventTask.setDuration(duration.substring(fromIndex + 5, toIndex - 1), duration.substring(toIndex + 3));
            return eventTask;
        default:
            return null;
        }
    }

    /**
     * Saves a task to the file by appending it.
     *
     * @param task The Task object to be saved.
     * @throws IOException if there is an error writing to the file.
     */
    public void save(Task task) throws IOException {
        this.task = task;
        try (FileWriter fw = new FileWriter(filePath, true)) {
            fw.write(task.saveTaskFormat());
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Rewrites the entire file with the current task list, overwriting existing data.
     *
     * @throws IOException if there is an error writing to the file.
     */
    public void rewriteFile() throws IOException {
        try (FileWriter fw = new FileWriter(filePath, false)) {
            for (Task task : taskList.getTasks()) {
                fw.write(task.saveTaskFormat());
            }
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
