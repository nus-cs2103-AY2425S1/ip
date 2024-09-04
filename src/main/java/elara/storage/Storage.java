package elara.storage;

import elara.task.Task;
import elara.task.TaskList;
import elara.task.ToDoTask;
import elara.task.DeadlineTask;
import elara.task.EventTask;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String FILE_PATH;

    public Storage(String filePath) {
        FILE_PATH = filePath;
    }

    public ArrayList<Task> load() {
        File file = new File(FILE_PATH);
        ArrayList<Task> tasks = new ArrayList<>();
        DateTimeFormatter isoFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME; // yyyy-MM-dd'T'HH:mm format

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

    public void write(TaskList tasks) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            for (Task task : tasks.getTasks()) {
                writer.write(task.toFileFormat() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

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
