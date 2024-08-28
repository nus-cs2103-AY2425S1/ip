package storage;

import exception.DudeException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Represents a storage file that stores the tasks.
 */
public class Storage {
    private final String filePath;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    /**
     * Creates a storage file with the given file path.
     *
     * @param filePath The file path of the storage file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the tasks to the storage file.
     *
     * @param tasks The tasks to be saved.
     * @throws DudeException If there is an error saving the tasks.
     */
    public void saveTasks(TaskList tasks) throws DudeException {
        try {
            File file = new File(this.filePath);
            FileWriter writer = new FileWriter(file);
            for (Task task : tasks.getTasks()) {
                writer.write(task.toString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new DudeException("An error occured while saving data to your file!");
        }
    }

    /**
     * Initializes the tasks from the storage file.
     *
     * @return The task list with the tasks from the storage file.
     * @throws IOException If there is an error initializing the tasks.
     */
    public TaskList initTasks() throws IOException {
        TaskList tasks = new TaskList();
        File file = new File(this.filePath);
        if (!file.exists()) {
            return tasks;
        }
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] arr = line.split("]");
            String type = arr[0].substring(1);
            boolean markedDone = arr[1].substring(1).equals("X");
            switch (type) {
                case "T":
                    tasks.addTask(new Todo(arr[2].substring(1)));
                    if (markedDone) {
                        tasks.markDone(tasks.getSize() - 1);
                    }
                    break;
                case "D":
                    String[] deadlineArr = arr[2].split(" \\(by: ");
                    tasks.addTask(new Deadline(deadlineArr[0].substring(1),
                            LocalDateTime.parse(deadlineArr[1].substring(0, deadlineArr[1].length() - 1), FORMATTER)));
                    if (markedDone) {
                        tasks.markDone(tasks.getSize() - 1);
                    }
                    break;
                case "E":
                    String[] eventArr = arr[2].split(" \\(from: ");
                    String[] eventArrDetails = eventArr[1].split(" to: ");
                    tasks.addTask(new Event(eventArr[0].substring(1),
                            LocalDateTime.parse(eventArrDetails[0], FORMATTER),
                            LocalDateTime.parse(eventArrDetails[1].substring(0, eventArrDetails[1].length() - 1),
                                    FORMATTER)));
                    if (markedDone) {
                        tasks.markDone(tasks.getSize() - 1);
                    }
                    break;
            }
        }
        return tasks;
    }
}
