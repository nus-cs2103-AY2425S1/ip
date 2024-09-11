package storage;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import exception.PrimoException;
import tasks.DeadlineTask;
import tasks.EventTask;
import tasks.Task;
import tasks.TaskList;
import tasks.TodoTask;

/**
 * The Storage class handles the loading and saving of tasks from/to a file.
 * It is responsible for reading tasks from a file when the application starts
 * and writing the current tasks to the file when they are updated.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path to the file where tasks will be stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the storage file into an array of Task objects.
     *
     * @return An array of Task objects loaded from the file.
     * @throws PrimoException If the file format is incorrect or tasks cannot be loaded.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public Task[] load() throws PrimoException, IOException {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            Path filePath = Paths.get(this.filePath);
            List<String> lines = Files.readAllLines(filePath);
            for (String s : lines) {
                String[] words = s.split(" ");
                boolean isDone;
                switch (words[0].charAt(3)) {
                case 'T':
                    int todoFromIndex = 9;
                    String todoDescription = s.substring(todoFromIndex).trim();
                    isDone = s.charAt(6) == 'X';
                    Task newToDoTask = new TodoTask(todoDescription);
                    if (isDone) {
                        newToDoTask.markAsDone();
                    }
                    taskList.add(newToDoTask);
                    break;
                case 'D':
                    int deadlineFromIndex = 9;
                    int deadlineToIndex = s.indexOf("(by:");
                    String deadlineDescription = s.substring(deadlineFromIndex, deadlineToIndex).trim();
                    String dueTime = s.substring(deadlineToIndex + 4, s.indexOf(')')).trim();
                    isDone = s.charAt(6) == 'X';
                    Task newDeadlineTask = new DeadlineTask(deadlineDescription, dueTime);
                    if (isDone) {
                        newDeadlineTask.markAsDone();
                    }
                    taskList.add(newDeadlineTask);
                    break;
                case 'E':
                    String eventDescription = s.substring(9, s.indexOf("(from:")).trim();
                    String eventFromTime = s.substring(s.indexOf("from: ") + 6, s.indexOf("to: ")).trim();
                    String eventToTime = s.substring(s.indexOf("to: ") + 4, s.indexOf(")")).trim();
                    isDone = s.charAt(6) == 'X';
                    Task newEventTask = new EventTask(eventDescription, eventFromTime, eventToTime);
                    if (isDone) {
                        newEventTask.markAsDone();
                    }
                    taskList.add(newEventTask);
                    break;
                default:
                    return null; // should not reach here if exception handling is correct
                }
            }
            return taskList.toArray(new Task[0]);
        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * Updates the storage file with the current list of tasks.
     *
     * @param taskList The TaskList object containing the tasks to be saved.
     */
    public void updateStorage(TaskList taskList) {
        ArrayList<Task> list = taskList.getTasks();
        int len = list.size();
        StringBuilder data = new StringBuilder();
        for (int i = 0; i < len; i++) {
            String output = i + 1 + "." + list.get(i);
            data.append(output);
            data.append("\n");
        }
        try (FileWriter writer = new FileWriter("./data/data.txt")) {
            writer.write(data.toString());
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
