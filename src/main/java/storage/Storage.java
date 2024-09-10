package storage;

import tasks.Task;
import tasks.ToDo;
import tasks.Deadline;
import tasks.Event;
import main.Parser;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.io.*;

/**
 * The {@code Storage} class handles reading tasks from a file and saving tasks back to a file.
 * This class ensures that task data is persistent between application runs.
 */
public class Storage {
    private final String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Loads tasks from the file at the specified filepath.
     *
     * @return a list of tasks loaded from the file
     */
    public List<Task> loadTasks() {
        List<Task> taskList = new ArrayList<>();
        File file = new File(this.filepath);

        if (!file.exists()) {
            return taskList;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split("\\s*\\|\\s*");
                if (parts.length < 3) {
                    System.out.println("Skipping invalid line: " + line);
                    continue;
                }

                String taskType = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];

                switch (taskType) {
                    case "T":
                        ToDo newToDo = new ToDo(description);
                        if (isDone) {
                            newToDo.markAsDone();
                        }
                        taskList.add(newToDo);
                        break;
                    case "D":
                        if (parts.length < 4) {
                            System.out.println("Skipping invalid Tasks.Deadline line: " + line);
                            continue;
                        }
                        try {
                            LocalDateTime dueWhen = Parser.parseDateTime(parts[3]);
                            Deadline newDeadline = new Deadline(description, dueWhen);
                            if (isDone) {
                                newDeadline.markAsDone();
                            }
                            taskList.add(newDeadline);
                        } catch (DateTimeParseException e) {
                            System.out.println("Skipping invalid date format in Tasks.Deadline: " + line);
                        }
                        break;
                    case "E":
                        if (parts.length < 5) {
                            System.out.println("Skipping invalid Tasks.Event line: " + line);
                            continue;
                        }
                        try {
                            LocalDateTime startWhen = Parser.parseDateTime(parts[3]);
                            LocalDateTime endWhen = Parser.parseDateTime(parts[4]);
                            Event newEvent = new Event(description, startWhen, endWhen);
                            if (isDone) {
                                newEvent.markAsDone();
                            }
                            taskList.add(newEvent);
                        } catch (DateTimeParseException e) {
                            System.out.println("Skipping invalid date format in Tasks.Event: " + line);
                        }
                        break;
                    default:
                        System.out.println("Unknown task type: " + taskType);
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
        return taskList;
    }


    /**
     * Saves the list of tasks to the file at the specified filepath.
     *
     * @param taskList the list of tasks to save
     */
    public void saveTasks(List<Task> taskList) {
        assert taskList != null : "Task list should not be null";

        File file = new File(this.filepath);
        file.getParentFile().mkdirs();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (Task task : taskList) {
                bw.write(task.toFileString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
}
