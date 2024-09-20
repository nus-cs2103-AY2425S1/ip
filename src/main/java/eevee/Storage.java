package eevee;

import eevee.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Handles loading and storing of tasks in a text file.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs an instance of storage with the given file path.
     *
     * @param filePath
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        checkFileExists();
    }

    /**
     * Checks if the storage directory and file exists.
     * Creates them if they don't exist.
     */
    private void checkFileExists() {
        try {
            File f = new File(filePath);
            File dir = f.getParentFile();

            // If directory does not exist, make it
            if (dir != null && !dir.exists()) {
                dir.mkdirs();
            }

            // If file doesn't exist, make it
            if (!f.exists()) {
                f.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error occurred when attempted to create storage file!");
            e.printStackTrace();
        }
    }

    /**
     * Saves tasks from the given TaskList into the storage file.
     *
     * @param taskList The TaskList of tasks to be saved.
     * @throws IOException If an IO error occurs when writing to the file.
     */
    public void saveTasks(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task task : taskList.getTasks()) {
            fw.write(task.toFileString() + "\n");
        }
        fw.close();
    }

    /**
     * Loads tasks from the storage file into the given TaskList.
     *
     * @param tasks The TaskList to add the tasks to.
     * @throws FileNotFoundException If the storage file is not found.
     */
    public void loadTasks(TaskList tasks) throws FileNotFoundException {
        File f = new File(filePath);
        if (!f.exists() || f.length() == 0) {
            return;
        }
        Scanner scanner = new Scanner(f);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] taskData = line.split("\\|");

            String type = taskData[0];
            boolean isDone = taskData[1].equals("1");
            String description = taskData[2];
            Task.Priority priority = Task.Priority.valueOf(taskData[3]);

            switch (type) {
            case "T":
                handleTodoTask(description, isDone, priority, tasks);
                break;
            case "D":
                handleDeadlineTask(taskData, description, isDone, priority, tasks);
                break;
            case "E":
                handleEventTask(taskData, description, isDone, priority, tasks);
                break;
            default:
                System.out.println("Invalid task found!");
                break;
            }
        }
    }

    private void handleTodoTask(String description, boolean isDone, Task.Priority priority, TaskList tasks) {
        Todo t = new Todo(description);
        if (isDone) {
            t.markAsDone();
        }
        t.setPriority(priority);
        tasks.addTask(t);
    }

    private void handleDeadlineTask(String[] taskData, String description, boolean isDone, Task.Priority priority, TaskList tasks) {
        String deadline = taskData[4];
        Deadline d = new Deadline(description, deadline);
        if (isDone) {
            d.markAsDone();
        }
        d.setPriority(priority);
        tasks.addTask(d);
    }

    private void handleEventTask(String[] taskData, String description, boolean isDone, Task.Priority priority, TaskList tasks) {
        String from = taskData[4];
        String to = taskData[5];
        Event e = new Event(description, from, to);
        if (isDone) {
            e.markAsDone();
        }
        e.setPriority(priority);
        tasks.addTask(e);
    }
}
