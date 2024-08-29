package bob.storage;

import bob.data.TaskList;
import bob.tasks.DeadlineTask;
import bob.tasks.EventTask;
import bob.tasks.Task;
import bob.tasks.TodoTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * Reads list of tasks from a file.
 */
public class FileReading extends Storage {
    private static final String SEPARATOR = " \\| ";

    /**
     * Constructs a new FileReading object.
     *
     * @param filePath The path to the file.
     */
    public FileReading(String filePath) {
        super(filePath);
    }

    /**
     * Creates a directory if it does not exist.
     *
     * @param dirName The name of the directory.
     */
    protected static void createDirectory(String dirName) {
        File directory = new File(dirName);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    /**
     * Creates a file if it does not exist.
     *
     * @param filePath The path to the file.
     * @throws IOException If the file cannot be created.
     */
    protected static void createFile(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile(); // Creates the file if it does not exist
        }
    }

    /**
     * Loads tasks from a file.
     *
     * @param filePath The path to the file.
     * @return TaskList The list of tasks.
     * @throws FileNotFoundException If the file cannot be found.
     */
    protected static TaskList loadTasks(String filePath) throws FileNotFoundException {
        TaskList list = new TaskList();
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String[] parts = s.nextLine().split(SEPARATOR);
            switch (parts[0]) {
                case "T" -> {
                    Task todo = new TodoTask(parts[2]);
                    todo.isDone = isDone(Integer.parseInt(parts[1]));
                    list.add(todo);
                }
                case "D" -> {
                    LocalDateTime by = LocalDateTime.parse(parts[3]);
                    Task deadline = new DeadlineTask(parts[2], by);
                    deadline.isDone = isDone(Integer.parseInt(parts[1]));
                    list.add(deadline);
                }
                case "E" -> {
                    LocalDateTime from = LocalDateTime.parse(parts[3]);
                    LocalDateTime to = LocalDateTime.parse(parts[4]);
                    Task event = new EventTask(parts[2], from, to);
                    event.isDone = isDone(Integer.parseInt(parts[1]));
                    list.add(event);
                }
            }
        }
        return list;
    }

    /**
     * Checks if a task is done.
     *
     * @param i The index of the task.
     * @return Boolean True if the task is done, false otherwise.
     */
    protected static boolean isDone(int i) {
        return i == 1;
    }
}
