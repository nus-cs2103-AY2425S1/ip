import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    public Task createDeadline(String description, String by) {
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
     * Saves all the tasks to the hard disk in a file
     * @return Whether the save is successful
     */
    public boolean saveTasks() {
        FileWriter file;
        try {
            file = new FileWriter(SAVE_FILE);

            StringBuilder output = new StringBuilder();

            for (Task task : taskList) {
                output.append(task.toTaskString());
            }

            file.write(output.toString());
            file.close();
            return true;
        } catch (IOException e) {
            System.out.println("Error! Failed to save tasks!");
            return false;
        }
    }
}
