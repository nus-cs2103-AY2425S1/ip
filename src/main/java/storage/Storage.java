package storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

/**
 * Responsible for saving the input data into a file after execution.
 * Responsible for loading the saved inputs from the file before the program starts.
 */
public class Storage {
    protected String filePath;
    protected TaskList tasks;

    /**
     * Initialises a Storage object with the file path.
     *
     * @param filePath the path of the file for storing the data.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.tasks = new TaskList();
    }

    /**
     * Loads the tasks from the data file into the TaskList.
     */
    public void loadTasks() {
        File file = new File(this.filePath);
        try {
            // create new file if it doesn't exist
            if (!file.createNewFile()) {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    createTask(line);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Saves the tasks into the data file after the program terminates.
     *
     * @param tasks the input list to be saved to the data file.
     */
    public void saveTasks(TaskList tasks) {
        File file = new File(this.filePath);
        try {
            FileWriter writer = new FileWriter(file);
            for (int i = 0; i < tasks.getSize(); i++) {
                writer.write(writeTask(tasks.getTask(i)));
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Returns the TaskList which has been populated with the saved data.
     *
     * @return TaskList.
     */
    public TaskList getTasks() {
        return this.tasks;
    }

    /**
     * Converts the task string in the data file into a task to be stored in TaskList.
     *
     * @param line task string in data file.
     */
    private void createTask(String line) {
        String[] taskParams = line.split(",");
        String taskType = taskParams[0];
        boolean isDone = Integer.parseInt(taskParams[1]) == 1;
        String priority = Integer.parseInt(taskParams[2]) == 1
                ? "high"
                : Integer.parseInt(taskParams[2]) == 2
                ? "medium"
                : "low";

        Task newTask;
        switch (taskType) {
        case "todo":
            newTask = new Todo(taskParams[3], priority);
            break;
        case "deadline":
            newTask = new Deadline(taskParams[3], taskParams[4], priority);
            break;
        case "event":
            newTask = new Event(taskParams[3], taskParams[4], taskParams[5], priority);
            break;
        default:
            newTask = new Task(taskParams[2], priority);
        }
        if (isDone) {
            newTask.markAsDone();
        }
        tasks.addTask(newTask);
    }

    /**
     * Returns string representation of the task to be saved to data file.
     *
     * @param t task to be saved.
     * @return string representation of task.
     */
    private String writeTask(Task t) {
        StringBuilder s = new StringBuilder();
        if (t instanceof Todo) {
            s.append("todo").append(",").append(t.writeTask());
        } else if (t instanceof Deadline) {
            s.append("deadline").append(",").append(t.writeTask());
        } else if (t instanceof Event) {
            s.append("event").append(",").append(t.writeTask());
        } else {
            s.append("task");
        }
        s.append("\n");
        return s.toString();
    }
}
