package bobby.storage;

import bobby.tasklist.TaskList;
import bobby.tasks.Deadline;
import bobby.tasks.Event;
import bobby.tasks.Task;
import bobby.tasks.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the list of tasks to a file specified by the {@code filePath}.
     * This method writes the tasks from the provided {@code TaskList} to the file,
     * with each task serialized to a specific format to facilitate easy loading later.
     * If an I/O error occurs during the writing process, an error message is printed to the console.
     *
     * @param tasks the {@code TaskList} containing the tasks to be saved
     */
    public void saveTasks(TaskList tasks) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task task : tasks.getTasks()) {
                writer.write(task.toFileString() + System.lineSeparator());
            }
            System.out.println("Tasks successfully saved to file.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Loads the list of tasks from a file specified by the {@code filePath}.
     * This method reads the tasks from the file, reconstructs them into their corresponding
     * {@code Task} objects (e.g., {@code Todo}, {@code Deadline}, {@code Event}), and adds them to a new {@code TaskList}.
     * If the file does not exist, it will be created and an empty {@code TaskList} is returned.
     * If an I/O error occurs during the reading process, an error message is printed to the console.
     *
     * @return a {@code TaskList} containing the tasks loaded from the file, or an empty {@code TaskList} if the file does not exist
     */
    public TaskList loadTasks() {
        TaskList tasks = new TaskList();
        File file = new File(filePath);
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
                return tasks;
            }
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" \\| ");
                String taskType = parts[0];
                boolean isDone = parts[1].equals("true");
                String description = parts[2];

                Task task;
                switch (taskType) {
                    case "T":
                        task = new Todo(description);
                        break;
                    case "D":
                        String byString = parts[3];
                        LocalDate by = LocalDate.parse(byString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                        task = new Deadline(description, by);
                        break;
                    case "E":
                        String fromString = parts[3];
                        String toString = parts[4];
                        LocalDate from = LocalDate.parse(fromString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                        LocalDate to = LocalDate.parse(toString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                        task = new Event(description, from, to);
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + taskType);
                }

                if (isDone) {
                    task.markTask();
                }

                tasks.add(task);
            }
            scanner.close();
            System.out.println("Tasks successfully loaded from file.");
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
        return tasks;
    }
}
