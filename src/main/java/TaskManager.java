import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskManager {
    private final List<Task> taskList;

    public static final String SAVE_FILE = "./data/tasks.txt";

    public TaskManager() {
        taskList = new ArrayList<>();
    }

    public int getTaskCount() {
        return taskList.size();
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    public Task removeTask(int index) {
        return taskList.remove(index);
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public Task createTodo(String description) {
        Task todo = new Todo(description);
        taskList.add(todo);
        return todo;
    }

    public Task createDeadline(String description, LocalDateTime by) {
        Task deadline = new Deadline(description, by);
        taskList.add(deadline);
        return deadline;
    }

    public Task createEvent(String description, String from, String to) {
        Task event = new Event(description, from, to);
        taskList.add(event);
        return event;
    }

    /**
     * Loads tasks from the task save file
     * @return boolean whether tasks loaded successfully
     */
    public boolean loadTasks() {
        File file = new File(SAVE_FILE);
        if (!file.isFile()) {
            System.out.println("Failed to find task save file, not loading any tasks.");
            return false;
        }

        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNext()) {
                String taskString = scanner.nextLine();
                if (taskString.startsWith("T")) {
                    Task task = Todo.fromTaskString(taskString);
                    taskList.add(task);
                } else if (taskString.startsWith("D")) {
                    Task task = Deadline.fromTaskString(taskString);
                    taskList.add(task);
                } else if (taskString.startsWith("E")) {
                    Task task = Event.fromTaskString(taskString);
                    taskList.add(task);
                } else {
                    System.out.println("Invalid task found, skipping: " + taskString);
                }
            }

            System.out.println("Loaded saved tasks from disk!");

            return true;
        } catch (IOException e) {
            System.out.println("Error! Failed to load tasks!");
            return false;
        }
    }

    /**
     * Saves all the tasks to the hard disk in a file
     * @return Whether the save is successful
     */
    public boolean saveTasks() {
        File file = new File(SAVE_FILE);
        // ensure our data directory exists first
        file.getParentFile().mkdir();

        try {
            FileWriter fileWriter = new FileWriter(SAVE_FILE);

            StringBuilder output = new StringBuilder();
            for (Task task : taskList) {
                output.append(task.toTaskString()).append("\n");
            }

            fileWriter.write(output.toString());
            fileWriter.close();
            return true;
        } catch (IOException e) {
            System.out.println("Error! Failed to save tasks!");
            return false;
        }
    }
}
