package elara.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import elara.task.DeadlineTask;
import elara.task.EventTask;
import elara.task.Task;
import elara.task.ToDoTask;

/**
 * Handles the storage of tasks in a file. Responsible for loading tasks from the file and saving tasks to the file.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a new instance of Storage
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file specified by the file path.
     * Parses each line in the file to create Task objects and adds them to the task list.
     *
     * @return A list of tasks loaded from the file. If the file does not exist, returns an empty list.
     */
    public ArrayList<Task> load() {
        File file = new File(filePath);
        ArrayList<Task> tasks = new ArrayList<>();

        if (!file.exists()) {
            return tasks;
        }

        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] taskDetails = line.split(" \\| ");

                Task newTask = parseTask(taskDetails);
                tasks.add(newTask);
            }
        } catch (IOException | IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Writes the current task list to the file.
     *
     * @param tasks The TaskList object containing tasks to be written to the file.
     */
    public void write(TaskList tasks) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task task : tasks.getTasks()) {
                writer.write(task.toFileFormat() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    /**
     * Parses a line from the file and converts it into a Task object.
     *
     * @param taskDetails The String array representing the task details.
     * @return A Task object created from the parsed details.
     * @throws IllegalArgumentException If the task type or format is invalid.
     */
    private Task parseTask(String[] taskDetails) {
        String taskType = taskDetails[0];
        boolean isDone = taskDetails[1].trim().equals("1");
        String description = taskDetails[2].trim();

        DateTimeFormatter isoFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME; // yyyy-MM-dd'T'HH:mm format

        return switch (taskType) {
        case "T" -> new ToDoTask(description, isDone);
        case "D" -> {
            if (taskDetails.length == 4) {
                LocalDateTime deadline = LocalDateTime.parse(taskDetails[3].trim(), isoFormatter);
                yield new DeadlineTask(description, deadline, isDone);
            }
            throw new IllegalArgumentException("Invalid deadline task format");
        }
        case "E" -> {
            if (taskDetails.length == 5) {
                LocalDateTime startTime = LocalDateTime.parse(taskDetails[3].trim(), isoFormatter);
                LocalDateTime endTime = LocalDateTime.parse(taskDetails[4].trim(), isoFormatter);
                yield new EventTask(description, startTime, endTime, isDone);
            }
            throw new IllegalArgumentException("Invalid event task format");
        }
        default -> throw new IllegalArgumentException("Invalid task type");
        };
    }
}
