package asura.storage;

import asura.data.exception.AsuraException;
import asura.data.tasks.Deadline;
import asura.data.tasks.Event;
import asura.data.tasks.Task;
import asura.data.tasks.Todo;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a Storage to save/load the user's task data.
 */
public class Storage {

    private String filePath;

    /**
     * Creates a Storage with the specified file path.
     * @param filePath The file path that the user wants to save their task data at.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the user's task data.
     * @return The saved list of tasks of the user.
     * @throws AsuraException If loading the data fails.
     */
    public List<Task> load() throws AsuraException {
        List<Task> taskList = new ArrayList<>();
        File data = new File(filePath);
        data.getParentFile().mkdirs();
        try {
            if (data.createNewFile()) {
                throw new AsuraException("Existing data not detected, starting with a clean slate.");
            }
            Scanner scanner = new Scanner(data);
            while (scanner.hasNextLine()) {
                Task task = parseTask(scanner.nextLine());
                taskList.add(task);
            }
        } catch (Exception e) {
            throw new AsuraException(e.getMessage());
        }

        return taskList;
    }

    private Task parseTask(String line) throws AsuraException {
        String[] task = line.split("\\|");
        Task result = switch (task[0]) {
            case "T" -> new Todo(task[2]);
            case "E" -> new Event(task[2], task[3], task[4]);
            case "D" -> new Deadline(task[2], LocalDateTime.parse(task[3]));
            default -> throw new AsuraException("Unknown task: " + task[0]);
        };

        if (Integer.parseInt(task[1]) == 1) {
            result.markAsDone();
        }

        return result;
    }

    /**
     * Saves the task data of the user with the give task list.
     * @param taskList The list of tasks to be saved.
     * @throws AsuraException If saving the tasks fails.
     */
    public void save(List<Task> taskList) throws AsuraException {
        StringBuilder sb = new StringBuilder();
        for (Task task : taskList) {
            if (task instanceof Event event) {
                sb.append("E|").append(event.getIsDone() ? 1 : 0).append("|").append(event.getDescription()).append("|").append(event.getStart()).append("|").append(event.getEnd()).append("\n");
            } else if (task instanceof Todo todo) {
                sb.append("T|").append(todo.getIsDone() ? 1 : 0).append("|").append(todo.getDescription()).append("\n");
            } else if (task instanceof Deadline deadline) {
                sb.append("D|").append(deadline.getIsDone() ? 1 : 0).append("|").append(deadline.getDescription()).append("|").append(deadline.getBy()).append("\n");
            }
        }
        try {
            Files.write(Paths.get(filePath), sb.toString().getBytes());
        } catch (Exception e) {
            throw new AsuraException(e.getMessage());
        }
    }
}
