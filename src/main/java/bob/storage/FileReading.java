package bob.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

import bob.data.TaskList;
import bob.tasks.DeadlineTask;
import bob.tasks.EventTask;
import bob.tasks.Task;
import bob.tasks.TodoTask;

/**
 * Class representing the file reading.
 */
public class FileReading extends Storage {
    private static final String SEPARATOR = " \\| ";

    /**
     * Creates a new FileReading object with a given filepath.
     *
     * @param filePath the filepath to the file.
     */
    public FileReading(String filePath) {
        super(filePath);
    }

    protected static void createDirectory(String dirName) {
        assert dirName != null : "The directory name cannot be null.";
        File directory = new File(dirName);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    protected static void createFile(String filePath) throws IOException {
        assert filePath != null : "The file path cannot be null.";
        File file = new File(filePath);
        if (!file.exists()) {
            assert file.exists() : "The file already exist.";
            file.createNewFile(); // Creates the file if it does not exist
        }
    }

    protected static TaskList loadTasks(String filePath) throws FileNotFoundException {
        TaskList list = new TaskList();
        File f = new File(filePath);
        Scanner s = new Scanner(f);

        while (s.hasNext()) {
            String[] parts = s.nextLine().split(SEPARATOR);

            switch (parts[0]) {
            case "T" -> {
                Task todo = new TodoTask(parts[2]);
                todo.setIsDone(isDone(Integer.parseInt(parts[1])));
                list.add(todo);
            }
            case "D" -> {
                LocalDateTime by = LocalDateTime.parse(parts[3]);
                Task deadline = new DeadlineTask(parts[2], by);
                deadline.setIsDone(isDone(Integer.parseInt(parts[1])));
                list.add(deadline);
            }
            case "E" -> {
                LocalDateTime from = LocalDateTime.parse(parts[3]);
                LocalDateTime to = LocalDateTime.parse(parts[4]);
                Task event = new EventTask(parts[2], from, to);
                event.setIsDone(isDone(Integer.parseInt(parts[1])));
                list.add(event);
            }
            default -> throw new IllegalArgumentException("Invalid task type: " + parts[0]);
            }
        }
        return list;
    }

    protected static boolean isDone(int i) {
        return i == 1;
    }
}
