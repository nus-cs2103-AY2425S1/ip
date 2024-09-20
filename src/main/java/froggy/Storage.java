package froggy;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code Storage} class handles reading from and writing to a file that stores the user's task list.
 * It is responsible for loading tasks from a specified file and saving tasks back to that file.
 */
public class Storage {

    private String filePath;

    /**
     * Constructs a new {@code Storage} object with the specified file path.
     *
     * @param filePath The path of the file where tasks will be saved and loaded from.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the tasks from the specified file. If the file does not exist, it will create a new file.
     * The file is expected to store tasks in a specific format,
     * where each line represents a task (todo, deadline, or event).
     *
     * @return A {@code List<Task>} containing the tasks loaded from the file.
     * @throws FroggyException If the file format is invalid or the file cannot be read correctly.
     */
    public List<Task> loadTasks() throws FroggyException {
        List<Task> taskList = new ArrayList<>();
        File file = new File(filePath);
        try {
            if (file.exists()) {
                System.out.println("Task list found.");
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    boolean isDone = (line.charAt(2) == '1');
                    switch (line.charAt(0)) {
                    case 'T':
                        Task newTodo = new Todo(line.substring(4));
                        newTodo.setStatus(isDone);
                        taskList.add(newTodo);
                        break;
                    case 'D':
                        int index = line.indexOf('|');
                        String by = line.substring(index + 2);
                        Task newDeadline = new Deadline(line.substring(4, index - 1), by);
                        newDeadline.setStatus(isDone);
                        taskList.add(newDeadline);
                        break;
                    case 'E':
                        int index1 = line.indexOf('|');
                        int index2 = line.indexOf('|', index1 + 1);
                        String from = line.substring(index1 + 2, index2 - 1);
                        String to = line.substring(index2 + 2);
                        Task newEvent = new Event(line.substring(4, index1 - 1), from, to);
                        newEvent.setStatus(isDone);
                        taskList.add(newEvent);
                        break;
                    default:
                        throw new FroggyException("Task list file format is invalid.");
                    }
                }

            } else {
                file.getParentFile().mkdirs();
                file.createNewFile();
                System.out.println("No task list found. Created new task list.");
            }
        } catch (IOException e) {
            System.out.println("Error: Failed to read task list file.");
        }
        return taskList;
    }

    /**
     * Saves the tasks to the specified file by overwriting its contents. The tasks are written
     * in a specific format so they can be read back into the program later.
     *
     * @param newTaskList A {@code List<Task>} containing the tasks to be saved to the file.
     */
    public void saveTasks(List<Task> newTaskList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task t : newTaskList) {
                writer.write(t.toTxt());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error: Failed to save task list.");
        }
    }

    /**
     * Saves the tasks from a {@code TaskList} object by overwriting the file with its contents.
     * This is an overloaded version of {@code saveTasks(List<Task>)}.
     *
     * @param newTaskList A {@code TaskList} containing the tasks to be saved.
     */
    public void saveTasks(TaskList newTaskList) {
        saveTasks(newTaskList.getTasks());
    }
}
