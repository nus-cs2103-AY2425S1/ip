package taskalyn;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
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
                + task.toString() + "\n" + "Wah bro... "
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
                + task.toString() + "\n" + "Wah bro... "
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
        String taskString = "Here are the tasks in your list:\n";
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
        if (task.isCompleted()) {
            return "This task was already marked as complete:\n"
                    + task.toString();
        }
        task.setComplete();
        ui.printLines("Nice, I've marked this task as complete:\n"
                + task.toString());
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
        if (!task.isCompleted()) {
            return "This task was already unmarked and incomplete:\n"
                    + task.toString();
        }
        task.setIncomplete();
        ui.printLines("Ok, I've marked this task as incomplete:\n"
                + task.toString());
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
            String matchingTasks = "Here are the matching tasks in your list:\n";
            matchingTasks += stringBuilder(matchedTasks);
            return ui.showSearchTasksByKeywordMessage(matchingTasks);
        } else {
            return ui.showSearchTasksByKeywordMessage("Aw... there are no matching tasks :(");
        }
    }

    /**
     * Returns the deadline tasks sorted by their deadlines in chronological order.
     *
     * @return String of deadline tasks sorted by earliest to latest deadline.
     */
    public String sortDeadlineTasksByDeadline() {
        String taskString = "Here are the sorted deadline tasks in your list:\n";
        List<DeadlineTask> deadlineTasks = new ArrayList<>(tasks.stream()
                .filter(task -> task instanceof DeadlineTask)
                .map(task -> (DeadlineTask) task)
                .toList());

        if (deadlineTasks.isEmpty()) {
            return "There are no deadline tasks in your list!";
        }

        // Solution below inspired by:
        // https://stackoverflow.com/questions/71548399/what-is-the-use-of-comparator-comparing-in-respect-to-comparator
        deadlineTasks.sort(Comparator.comparing(DeadlineTask::getDeadline));
        taskString += stringBuilder(deadlineTasks);
        return taskString;
    }

    /**
     * Loads database when TaskManager is initialised.
     */
    private void loadDatabase() {
        try {
            List<String> textLines = this.database.readFromDatabase();
            for (String line : textLines) {
                Task task = parseTaskFromString(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading database: " + e.getMessage());
        }
    }

    /**
     * Parses type of task and creates a new Task object of that type.
     *
     * @param line String line from each line in database file.
     * @return A new TodoTask, DeadlineTask, or EventTask object or null.
     */
    private Task parseTaskFromString(String line) {
        String[] parts = line.split(" \\| ");
        assert parts.length >= 3 : "There should be at least 3 parts after splitting.";
        String taskType = parts[0];
        boolean isCompleted = parts[1].equals("1");
        String taskInfo = parts[2];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");

        switch (taskType) {
        case "T":
            return new TodoTask(taskInfo, isCompleted);

        case "D":
            LocalDateTime date = LocalDateTime.parse(parts[3], formatter);
            return new DeadlineTask(taskInfo, date, isCompleted);

        case "E":
            LocalDateTime fromDate = LocalDateTime.parse(parts[3], formatter);
            LocalDateTime toDate = LocalDateTime.parse(parts[4], formatter);
            return new EventTask(taskInfo, toDate, fromDate, isCompleted);

        default:
            return null;
        }
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
    private String stringBuilder(List<? extends Task> tasks) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            stringBuilder.append(i + 1).append(".").append(tasks.get(i).toString());
            if (i != tasks.size() - 1) {
                stringBuilder.append("\n");
            }
        }
        return stringBuilder.toString();
    }
}
