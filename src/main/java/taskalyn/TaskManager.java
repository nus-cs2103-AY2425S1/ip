package taskalyn;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages the tasks in the database.
 */
public class TaskManager {
    private List<Task> tasks;
    private Database database;
    private Ui ui;

    /**
     * Constructs the TaskManager object with the database and ui.
     *
     * @param database Database object to read from and write to.
     * @param ui Ui object for user interaction.
     */
    public TaskManager(Database database, Ui ui) {
        this.database = database;
        this.ui = ui;
        this.tasks = new ArrayList<>(100);
        loadDatabase();
    }

    /**
     * Adds a task to the database.
     *
     * @param task Task object.
     */
    public void addTask(Task task) {
        tasks.add(task);
        ui.printLines("Got it, I've added this task to your list!\n" +
                "      " + task.toString() + "\n" + "    Wah bro... " +
                getTaskSize() + (getTaskSize() > 1 ? " tasks already!" : " task already!"));
        updateDatabase();
    }

    /**
     * Deletes a task from the database.
     *
     * @param taskId The id of the task.
     */
    public void deleteTask(int taskId) {
        if (taskId <= tasks.size() + 1) {
            Task task = tasks.get(taskId - 1);
            tasks.remove(task);
            ui.printLines("Awesome bro! One task gone :D\n" +
                    "      " + task.toString() + "\n" + "    Wah bro... " +
                    getTaskSize() + (getTaskSize() > 1 ? " tasks already!" : " task already!"));
        }
        updateDatabase();
    }

    /**
     * Returns the total number of tasks in database.
     *
     * @return Total number of tasks in database.
     */
    public int getTaskSize() {
        return tasks.size();
    }

    /**
     * Lists the tasks in the database.
     */
    public void listTasks() {
        String taskString = "Here are the tasks in your list:\n" + "    ";

        if (!tasks.isEmpty()) {
            for (int i=0; i<tasks.size(); i++) {
                if (i != tasks.size() - 1) {
                    taskString += i + 1 + "." + tasks.get(i).toString() + "\n    ";
                }
                else {
                    taskString += i + 1 + "." + tasks.get(i).toString();
                }
            }
        }
        else {
            taskString += "Nothing!";
        }

        ui.printLines(taskString);
    }

    /**
     * Marks a task as complete.
     *
     * @param taskId The id of the task.
     */
    public void markTaskAsComplete(int taskId) {
        if (taskId <= tasks.size() + 1) {
            tasks.get(taskId - 1).setComplete();
            ui.printLines("Nice, I've marked this task as complete:\n" +
                    "       " + tasks.get(taskId - 1).toString());
        }
        updateDatabase();
    }

    /**
     * Marks a task as incomplete.
     *
     * @param taskId The id of the task.
     */
    public void markTaskAsIncomplete(int taskId) {
        if (taskId <= tasks.size() + 1) {
            tasks.get(taskId - 1).setIncomplete();
            ui.printLines("Ok, I've marked this task as incomplete:\n" +
                    "       " + tasks.get(taskId - 1).toString());
        }
        updateDatabase();
    }

    private void loadDatabase() {
        try {
            List<String> textLines = this.database.readFromDatabase();
            for (String line : textLines) {
                Task task = findTask(line);
                tasks.add(task);
            }
        } catch (IOException e) {
            System.out.println("Error reading database: " + e.getMessage());
        }
    }

    private Task findTask(String line) {
        String[] parts = line.split(" \\| ");
        if (parts.length >= 3) {
            String taskType = parts[0];
            boolean isCompleted = parts[1].equals("1");
            String taskInfo = parts[2];

            switch (taskType) {
            case "T":
                return new TodoTask(taskInfo, isCompleted);

            case "D":
                if (parts.length == 4) {
                    String date = parts[3];
                    return new DeadlineTask(taskInfo, date, isCompleted);
                }
                break;

            case "E":
                if (parts.length == 5) {
                    String toDate = parts[3];
                    String fromDate = parts[4];
                    return new EventTask(taskInfo, toDate, fromDate, isCompleted);
                }
                break;

            default:
            }
        }
        return null;
    }

    private void updateDatabase() {
        List<String> textLines = new ArrayList<>();
        for (Task task : tasks) {
            textLines.add(task.toDatabaseFormat());
        }
        database.writeToDatabase(textLines);
    }
}
