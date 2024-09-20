package duck.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import duck.command.InvalidCommandException;
import duck.task.Deadline;
import duck.task.Event;
import duck.task.Task;
import duck.task.TaskList;
import duck.task.ToDo;

/**
 * The Storage class handles the loading and saving of tasks to and from a file.
 */
public class Storage {

    private final String filePath;

    /**
     * Constructs a Storage instance with the specified file path.
     *
     * @param filePath the path to the file for loading and saving tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file.
     *
     * @return an ArrayList of tasks loaded from the file.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> taskList = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            return taskList;
        }

        try {
            loadTasksFromFile(file, taskList);
        } catch (InvalidCommandException e) {
            System.out.println("Invalid command: " + e.getMessage());
        }
        return taskList;
    }

    private void loadTasksFromFile(File file, ArrayList<Task> taskList) throws InvalidCommandException {
        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNext()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(" \\| ");
                Task task = createTask(parts);
                if (parts[1].equals("1")) {
                    task.mark();
                }
                taskList.add(task);
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }
    }

    private Task createTask(String[] parts) throws InvalidCommandException {
        return switch (parts[0]) {
        case "T" -> new ToDo(parts[2]);
        case "D" -> new Deadline(parts[2], LocalDate.parse(parts[3]));
        case "E" -> new Event(parts[2], parts[3], parts[4]);
        default -> throw new InvalidCommandException("Invalid task type in file");
        };
    }

    /**
     * Saves the tasks to the file.
     *
     * @param list the TaskList containing tasks to be saved.
     */
    public void saveTasks(TaskList list) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task task : list.getList()) {
                writer.write(task.toFileString() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }

        try (Scanner fileScanner = new Scanner(new File(filePath))) {
            int lineCount = 0;
            while (fileScanner.hasNextLine()) {
                fileScanner.nextLine();
                lineCount++;
            }
            assert lineCount == list.getList().size();
        } catch (IOException e) {
            System.out.println("Error reading tasks from file: " + e.getMessage());
        }
    }
}
