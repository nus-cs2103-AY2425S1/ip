package soju;

import soju.tasks.Deadline;
import soju.tasks.Event;
import soju.tasks.Task;
import soju.tasks.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The {@code Storage} class handles loading and saving tasks to a file.
 * It ensures that tasks are stored persistently between sessions.
 */
public class Storage {
    private String filePath;
    private File tasksFile;

    /**
     * Constructs a {@code Storage} object with the specified file path.
     * It also creates the necessary directories and file if they don't exist.
     *
     * @param filePath The path to the file where tasks will be stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.tasksFile = new File(filePath);
        try {
            boolean canMakeDirectory = tasksFile.getParentFile().mkdirs();
            boolean canCreateNewFile = tasksFile.createNewFile();

            if (canMakeDirectory && canCreateNewFile) {
                System.out.println("Creating new Tasks File for you at: " + tasksFile.getPath());
            } else {
                System.out.println("Unable to make directory or create new file");
                System.out.println("Can make directory: " + canMakeDirectory);
                System.out.println("Can create new file: " + canCreateNewFile);
            }
        } catch (IOException e) {
            System.out.println("Unable to create new tasks file: " + e);
        }
    }

    /**
     * Loads tasks from the file specified during the construction of this {@code Storage} object.
     * Each task is parsed and added to a list, which is then returned.
     *
     * @return A list of tasks loaded from the file.
     * @throws SojuException If there is an error reading the file.
     */
    public List<Task> load() throws SojuException {
        List<Task> taskList = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" \\| ");
                String taskType = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];
                Task newTask = null;

                switch (taskType) {
                    case "T":
                        // Create a new Todo task
                        newTask = new Todo(description);
                        break;
                    case "D":
                        // Create a new Deadline task
                        String doneBy = parts[3];
                        LocalDate localDate = LocalDate.parse(doneBy);
                        newTask = new Deadline(description, localDate);
                        break;
                    case "E":
                        // Create a new Event task
                        String[] eventTimes = parts[3].split(" - ");
                        String from = eventTimes[0];
                        String to = eventTimes[1];
                        LocalDateTime localFromDate = LocalDateTime.parse(from);
                        LocalDateTime localToDate = LocalDateTime.parse(to);
                        newTask = new Event(description, localFromDate, localToDate);
                        break;
                    default:
                        System.out.println("Unknown task type: " + taskType);
                        break;
                }

                if (newTask != null) {
                    if (isDone) {
                        newTask.markAsDone();
                    }
                    taskList.add(newTask);
                }
            }
        } catch (IOException e) {
            throw new SojuException("Error trying to read the file! It may be corrupted!");
        }
        return taskList;
    }

    /**
     * Saves all tasks in the provided {@code TaskList} to the file.
     *
     * @param tasks The list of tasks to be saved.
     * @throws SojuException If there is an error writing to the file.
     */
    public void saveToFile(TaskList tasks) throws SojuException {
        List<Task> listOfTasks = tasks.getTasks();
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            for (Task task : listOfTasks) {
                fileWriter.append(task.toFileString()).append("\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new SojuException("Error saving file!!!");
        }
    }

    /**
     * Saves a single task to the file.
     *
     * @param task The task to be saved.
     * @throws SojuException If there is an error writing to the file.
     */
    public void saveToFile(Task task) throws SojuException {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.append(task.toFileString()).append("\n");
            fileWriter.close();
        } catch (IOException e) {
            throw new SojuException("Error saving file!!!");
        }
    }
}
