package hien.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import hien.exception.HienException;
import hien.task.Deadline;
import hien.task.Event;
import hien.task.Task;
import hien.task.Todo;

public class Storage {
    private static final DateTimeFormatter INPUT_DATE_FORMAT =
                                            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_DATE_FORMAT =
                                            DateTimeFormatter.ofPattern("MMM d yyyy, hh:mm a");
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from a file and returns a {@code TaskList} object containing these tasks.
     * If the file does not exist, a new file is created and an empty {@code TaskList} is returned.
     * The tasks are expected to be stored in a specific format in the file,
     * and each task is parsed and loaded into the list.
     *
     * The supported task types include:
     * <ul>
     *   <li>{@code T}: Todo tasks</li>
     *   <li>{@code D}: Deadline tasks, with a date/time for the deadline</li>
     *   <li>{@code E}: Event tasks, with a start and end date/time</li>
     * </ul>
     * Each task can also be marked as done or not done based on the second field in the file.
     *
     * @return A {@code TaskList} object containing the loaded tasks, or an empty {@code TaskList} if no tasks were loaded.
     * @throws HienException If there is an error during task loading, such as a parsing issue or I/O error.
     */
    public TaskList load() throws HienException {
        TaskList tasks = new TaskList();
        try {
            Files.createDirectories(Paths.get("data"));
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
                return tasks;
            }
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                if (parts.length >= 3) {
                    Task task;
                    switch (parts[0]) {
                    case "T":
                        task = new Todo(parts[2]);
                        break;
                    case "D":
                        LocalDateTime by = LocalDateTime.parse(parts[3].trim(), INPUT_DATE_FORMAT);
                        task = new Deadline(parts[2], by);
                        break;
                    case "E":
                        LocalDateTime from = LocalDateTime.parse(parts[3].trim(), INPUT_DATE_FORMAT);
                        LocalDateTime to = LocalDateTime.parse(parts[4].trim(), INPUT_DATE_FORMAT);
                        task = new Event(parts[2], from, to);
                        break;
                    default:
                        continue;
                    }
                    if (parts[1].equals("1")) {
                        task.markAsDone();
                    }
                    tasks.addTask(task);
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("An error occurred while loading tasks: " + e.getMessage() + "Return empty tasks");
            return new TaskList();
        }
        return tasks;
    }

    /**
     * Saves the tasks from the provided {@code TaskList} to a file.
     * Each task is written to the file in a specific format, depending on its type.
     * The method handles the creation of the necessary directories and file if they do not already exist.
     *
     * The task types are saved in the following format:
     * <ul>
     *   <li>{@code T | 0/1 | description} for Todo tasks</li>
     *   <li>{@code D | 0/1 | description | by-date-time} for Deadline tasks</li>
     *   <li>{@code E | 0/1 | description | from-date-time | to-date-time} for Event tasks</li>
     * </ul>
     * The second field, 0 or 1, indicates whether the task is marked as done.
     *
     * @param tasks The {@code TaskList} containing the tasks to be saved.
     * @throws HienException If an error occurs during the saving process, such as an I/O failure.
     */
    public void save(TaskList tasks) throws HienException {
        try {
            Files.createDirectories(Paths.get("data"));
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            for (Task task : tasks.getAllTasks()) {
                String line;
                if (task instanceof Todo) {
                    line = "T | " + (task.isDone() ? "1" : "0") + " | " + task.getDescription();
                } else if (task instanceof Deadline) {
                    Deadline deadline = (Deadline) task;
                    line = "D | " + (task.isDone() ? "1" : "0") + " | "
                                                                + task.getDescription() + " | "
                                                                + deadline.getBy().format(INPUT_DATE_FORMAT);
                } else if (task instanceof Event) {
                    Event event = (Event) task;
                    line = "E | " + (task.isDone() ? "1" : "0") + " | "
                                                                + task.getDescription() + " | "
                                                                + event.getFrom().format(INPUT_DATE_FORMAT)
                                                                + " | " + event.getTo().format(INPUT_DATE_FORMAT);
                } else {
                    continue;
                }
                writer.write(line);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks: " + e.getMessage());
        }
    }
}
