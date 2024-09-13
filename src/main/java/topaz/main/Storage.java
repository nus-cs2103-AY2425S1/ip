package topaz.main;

import topaz.task.Deadline;
import topaz.task.Event;
import topaz.task.Task;
import topaz.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Manages the storage and retrieval of tasks from a file.
 * This class handles loading tasks from a specified file and saving tasks back to that file.
 */
public class Storage {
    private String filePath;
    private File file;

    /**
     * Constructs a {@link Storage} object with the specified file path.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.file = new File(filePath);
    }

    /**
     * Loads tasks from the file specified by the file path.
     * Creates the file if it does not exist and initializes it if needed.
     *
     * <p>The file should have tasks stored in a specific format with fields separated by " | ". The task type
     * and its details are parsed to create appropriate {@link Task} objects.</p>
     *
     * @return A {@link TaskList} containing all tasks loaded from the file.
     * @throws IOException If an error occurs while reading the file or parsing its contents.
     */
    public TaskList load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdir();
        }
        if (!file.exists()) {
            file.createNewFile();
        }
        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            String nextLine = s.nextLine();
            String[] parts = nextLine.split(" \\| ");
            Task task;
            boolean isDone = Integer.parseInt(parts[1]) == 1 ? true : false;
            Topaz.TaskType type = Topaz.TaskType.valueOf(parts[0]);
            switch (type) {
            case T:
                task = new Todo(parts[2], isDone);
                break;
            case D:
                task = new Deadline(parts[2], isDone, LocalDateTime.parse(parts[3]));
                break;
            case E:
                task = new Event(parts[2], isDone, LocalDateTime.parse(parts[3]), LocalDateTime.parse(parts[4]));
                break;
            default:
                throw new IOException();
            }
            tasks.add(task);
        }
        return new TaskList(tasks);
    }

    public void save(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        taskList.write(fw);
        fw.close();
    }

}
