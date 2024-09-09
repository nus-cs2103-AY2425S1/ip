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
    public String addTask(Task task) {
        tasks.add(task);
        ui.printLines("Got it, I've added this task to your list!\n"
                + "      " + task.toString() + "\n" + "    Wah bro... "
                + getTaskSize() + (getTaskSize() > 1 ? " tasks already!" : " task already!"));
        updateDatabase();
        return ui.showAddTaskMessage(task, this);
    }

    /**
     * Deletes a task from the database.
     *
     * @param taskId The id of the task.
     */
    public String deleteTask(int taskId) {
        Task task = tasks.get(taskId - 1);
        tasks.remove(task);
        ui.printLines("Awesome bro! One task gone :D\n"
                + "      " + task.toString() + "\n" + "    Wah bro... "
                + getTaskSize() + (getTaskSize() > 1 ? " tasks already!" : " task already!"));
        updateDatabase();
        return ui.showDeleteTaskMessage(task, this);
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
    public String listTasks() {
        String taskString = "Here are the tasks in your list:\n" + "    ";
        if (!tasks.isEmpty()) {
            taskString += stringBuilder(tasks);
        } else {
            taskString += "Nothing!";
        }

        ui.printLines(taskString);
        return taskString;
    }

    /**
     * Marks a task as complete.
     *
     * @param taskId The id of the task.
     */
    public String markTaskAsComplete(int taskId) {
        Task task = tasks.get(taskId - 1);
        task.setComplete();
        ui.printLines("Nice, I've marked this task as complete:\n"
                + "       " + task.toString());
        updateDatabase();
        return ui.showMarkTaskAsCompleteMessage(task);
    }

    /**
     * Marks a task as incomplete.
     *
     * @param taskId The id of the task.
     */
    public String markTaskAsIncomplete(int taskId) {
        Task task = tasks.get(taskId - 1);
        task.setIncomplete();
        ui.printLines("Ok, I've marked this task as incomplete:\n"
                + "       " + task.toString());
        updateDatabase();
        return ui.showMarkTaskAsIncompleteMessage(task);
    }

    /**
     * Searches for tasks with the given keyword in their descriptions.
     *
     * @param keyword Keyword input from user.
     */
    public String searchTasksByKeyword(String keyword) {
        List<Task> matchedTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getTaskDescription().contains(keyword)) {
                matchedTasks.add(task);
            }
        }
        if (!matchedTasks.isEmpty()) {
            String matchingTasks = "Here are the matching tasks in your list:\n" + "    ";
            matchingTasks += stringBuilder(matchedTasks);
            return ui.showSearchTasksByKeywordMessage(matchingTasks);
        } else {
            return ui.showSearchTasksByKeywordMessage("Aw... there are no matching tasks :(");
        }
    }

    /**
     * Loads database when TaskManager is initialised.
     */
    private void loadDatabase() {
        try {
            List<String> textLines = this.database.readFromDatabase();
            for (String line : textLines) {
                Task task = parseTaskFromString(line);
                tasks.add(task);
            }
        } catch (IOException e) {
            System.out.println("Error reading database: " + e.getMessage());
        }
    }

    /**
     * Parses type of task and creates a new Task object of that type.
     *
     * @param line String line from each line in database file.
     * @return A new TodoTask, DeadlineTask, or EventTask object.
     */
    private Task parseTaskFromString(String line) {
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

    /**
     * Updates database with new task information.
     */
    private void updateDatabase() {
        List<String> textLines = new ArrayList<>();
        for (Task task : tasks) {
            textLines.add(task.toDatabaseFormat());
        }
        database.writeToDatabase(textLines);
    }

    /**
     * Returns a list of Task objects as a formatted String.
     *
     * @param tasks A list of Task objects.
     * @return A formatted String of a list of Task objects.
     */
    private String stringBuilder(List<Task> tasks) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            stringBuilder.append(i + 1).append(".").append(tasks.get(i).toString()).append("\n");
            if (i != tasks.size() - 1) {
                stringBuilder.append("\n    ");
            }
        }
        return stringBuilder.toString();
    }
}
