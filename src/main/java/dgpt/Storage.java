package dgpt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dgpt.exception.DgptFileNotFoundException;
import dgpt.task.Deadline;
import dgpt.task.Event;
import dgpt.task.Recurring;
import dgpt.task.Task;
import dgpt.task.TaskList;
import dgpt.task.ToDo;

/**
 * The Storage class is responsible for handling reading from and writing to
 * the file system for the Dgpt application. It manages loading and saving
 * tasks from/to a specified file path.
 */
public class Storage {

    private final String filePath;

    /**
     * Constructs a Storage instance with the specified file path.
     *
     * @param filePath The path of the file where tasks are stored or retrieved from.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file specified by the file path. The file is expected
     * to contain tasks in a specific format. The method reads the file line by line,
     * parses the task data, and adds them to a list of tasks.
     *
     * @return A list of tasks loaded from the file.
     * @throws IOException If an I/O error occurs while reading the file.
     * @throws DgptFileNotFoundException If the file cannot be found.
     */
    public List<Task> load() throws IOException, DgptFileNotFoundException {
        List<Task> res = new ArrayList<>();
        try {

            File f = new File(this.filePath);
            Scanner s = new Scanner(f);

            while (s.hasNext()) {
                String curr = s.nextLine();
                Task i = createTasks(curr);
                res.add(i);
            }
        } catch (FileNotFoundException e) {
            throw new DgptFileNotFoundException("Could not find existing data");
        }

        return res;
    }

    private Task createTasks(String curr) throws IOException {
        String[] parts = curr.split(" \\| ");
        Task i;

        switch (parts[0]) {
        case "T" -> {
            i = new ToDo(parts[2]);
        }
        case "D" -> {
            i = new Deadline(parts[2], parts[3]);
        }
        case "E" -> {
            i = new Event(parts[2], parts[3], parts[4]);
        }
        case "R" -> {
            i = new Recurring(parts[2], parts[3]);
        }
        default -> {
            throw new IOException("File format is invalid");
        }
        }

        if (parts[1].equals("1")) {
            i.mark();
        }
        return i;
    }

    /**
     * Saves the provided task list to the file specified by the file path. The method
     * writes each task to the file in a specific format. If the parent directory of
     * the file does not exist, it will be created.
     *
     * @param taskList The list of tasks to be saved to the file.
     * @throws IOException If an I/O error occurs while writing to the file or if
     *         the parent directory cannot be created.
     */
    public void save(TaskList taskList) throws IOException {
        assert taskList != null : "taskList cannot be null";

        File file = new File(this.filePath);
        ensureParentDirectoryExists(file);

        try (FileWriter fw = new FileWriter(filePath)) {
            String content = buildTaskListString(taskList);
            fw.write(content);
        } catch (IOException e) {
            throw new IOException("An error occurred while writing to the file: " + e.getMessage(), e);
        }
    }

    private void ensureParentDirectoryExists(File file) throws IOException {
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            if (!parentDir.mkdirs()) {
                throw new IOException("Failed to create directory: " + parentDir.getAbsolutePath());
            }
        }
    }

    private String buildTaskListString(TaskList taskList) throws IOException {
        StringBuilder sb = new StringBuilder();

        for (Task task : taskList.getTaskList()) {
            sb.append(convertTaskToString(task)).append("\n");
        }

        return sb.toString();
    }

    private String convertTaskToString(Task task) throws IOException {
        if (task instanceof ToDo) {
            return formatToDoTask((ToDo) task);
        } else if (task instanceof Deadline) {
            return formatDeadlineTask((Deadline) task);
        } else if (task instanceof Event) {
            return formatEventTask((Event) task);
        } else if (task instanceof Recurring) {
            return formatRecurringTask((Recurring) task);
        } else {
            throw new IOException("Unfamiliar Task Type found");
        }
    }

    private String formatToDoTask(ToDo task) {
        return String.format("T | %s | %s", task.getIsDone() ? "1" : "0", task.getDescription());
    }

    private String formatDeadlineTask(Deadline task) {
        return String.format("D | %s | %s | %s", task.getIsDone() ? "1" : "0", task.getDescription(),
                task.getDueDateInInputFormat());
    }

    private String formatEventTask(Event task) {
        return String.format("E | %s | %s | %s | %s", task.getIsDone() ? "1" : "0", task.getDescription(),
                task.getFromTimeInInputFormat(), task.getToTimeInInputFormat());
    }

    private String formatRecurringTask(Recurring task) {
        return String.format("R | %s | %s | %s", task.getIsDone() ? "1" : "0", task.getDescription(),
                task.getFrequency());
    }
}
