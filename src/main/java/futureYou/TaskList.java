package futureyou;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;

import futureyou.task.Deadline;
import futureyou.task.Events;
import futureyou.task.Task;

/**
 * The TaskList manages a list of tasks.
 */
public class TaskList {

    private static final ArrayList<Task> taskList = new ArrayList<>();
    private static Storage storage;

    /**
     * Constructs a new TaskList instance using data from file.
     *
     * @throws IOException when the file does not exist
     */
    public TaskList(String filePath) throws IOException {
        storage = new Storage(filePath);
        storage.loadTasks();
    }

    /**
     * Returns message to be displayed when new task of any kind is added to task list.
     *
     * @return message stating the details of the newly added task
     */
    private static String newTaskAddedMsg(Task newTask) {
        return "Added this task:" + System.lineSeparator() + newTask.print()
                + System.lineSeparator() + taskList.size() + " tasks in the list";
    }

    /**
     * Returns the task list.
     *
     * @return The task list.
     */
    public static ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Returns all the tasks in the user's tasklist.
     *
     * @return message All tasks in tasklist neatly formatted
     */
    public static String listTasks() {
        if (taskList.isEmpty()) {
            return "Task List is empty!";
        }
        StringBuilder message = new StringBuilder("Items in List:");
        int count = 1;
        message.append(System.lineSeparator());
        for (Task item : taskList) {
            message.append(count++).append(". ").append(item.print()).append(System.lineSeparator());
        }
        return message.toString();
    }

    /**
     * Adds a new task to the list.
     *
     * @param newTask The new task to add to the list.
     */
    public static void add(Task newTask) {
        taskList.add(newTask);
    }

    /**
     * Adds a new task to the list and returns the formatted output to print.
     *
     * @param taskName The name of the new task to add.
     * @return The message to be printed
     */
    public static String addTask(String taskName) {
        Task newTask = new Task(taskName);
        taskList.add(newTask);
        storage.saveTasks();
        return newTaskAddedMsg(newTask);
    }

    /**
     * Adds a new Deadline to the list and returns the formatted output to print.
     *
     * @param taskName The name of the new deadline task to add.
     * @param deadline The deadline of the new deadline task to add.
     * @return The message to be printed
     */
    public static String addTask(String taskName, LocalDateTime deadline) {
        Deadline newDeadline = new Deadline(taskName, deadline);
        taskList.add(newDeadline);
        storage.saveTasks();
        return newTaskAddedMsg(newDeadline);
    }

    /**
     * Adds a new Event to the list and returns the formatted output to print.
     *
     * @param taskName      The name of the new Event task to add.
     * @param startDateTime The start date time of the new Event task to add.
     * @param endDateTime   The start date time of the new Event task to add.
     * @return The message to be printed
     */
    public static String addTask(String taskName, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        Events newEvent = new Events(taskName, startDateTime, endDateTime);
        taskList.add(newEvent);
        storage.saveTasks();
        return newTaskAddedMsg(newEvent);
    }

    /**
     * Marks a specified task as done based on the task number input by the user.
     *
     * @param taskNumber The task number to mark as done.
     * @return message to be printed after task is marked
     */
    public static String markTask(int taskNumber) {
        try {
            Task markedTask = taskList.get(taskNumber).markTask();
            storage.saveTasks();
            StringBuilder message = new StringBuilder("Task marked as ");
            if (markedTask.getTaskStatus()) {
                message.append("completed:").append(System.lineSeparator());
            } else {
                message.append("incompleted:").append(System.lineSeparator());
            }
            message.append(taskList.get(taskNumber).print());

            return message.toString();
        } catch (Exception e) {
            return "Please enter a valid Task number";
        }
    }

    /**
     * Deletes a specific task based on the given task number.
     *
     * @param taskNumber The task number to delete.
     * @return message to be printed after task is deleted
     */
    public static String deleteTask(int taskNumber) {
        try {
            Task removedTask = taskList.remove(taskNumber);
            storage.saveTasks();
            return "Task Deleted: \n" + removedTask.print() + System.lineSeparator() + taskList.size()
                    + " tasks left in the list";

        } catch (Exception e) {
            return "Please enter a valid Task number";
        }
    }

    /**
     * Finds specified tasks using text input by the user.
     *
     * @param text The text to search.
     * @return message of all tasks containing the text (if it exists)
     * @throws Exception If the task number is invalid.
     */
    public static String findTask(String text) throws Exception {
        if (taskList.isEmpty()) {
            return "Task List is empty!";
        } else if (text.trim().isEmpty()) {
            return "Please enter a non empty task name to search";
        }

        StringBuilder message = new StringBuilder("Here are the matching tasks in your list:");
        message.append(System.lineSeparator());
        int count = 0;
        for (Task task : taskList) {
            if (task.getTaskName().contains(text.trim())) {
                message.append("[").append(count++).append("]").append(task.print()).append(System.lineSeparator());
            }
        }
        if (count == 0) {
            return "No task matching that text was found";
        }
        return message.toString();
    }

    /**
     * Comparator function used in sorting
     *
     * @return whether the t1 or t2 is bigger
     */
    private static int compareTasks(Task t1, Task t2) {
        // Solution below adopted from https://stackoverflow.com/questions/62492114/how-does-comparator-works-internally
        // Sort by type: Task (0) -> Deadline (1) -> Event (2)
        if (t1.getClass() != t2.getClass()) {
            if (t1 instanceof Task && !(t1 instanceof Deadline) && !(t1 instanceof Events)) {
                return -1;
            }
            if (t2 instanceof Task && !(t2 instanceof Deadline) && !(t2 instanceof Events)) {
                return 1;
            }
            if (t1 instanceof Deadline) {
                return -1;
            }
            if (t2 instanceof Deadline) {
                return 1;
            }
            if (t1 instanceof Events) {
                return -1;
            }
            if (t2 instanceof Events) {
                return 1;
            }
        }

        // If the same type, use the compareTo method of each class
        return t1.compareTo(t2);
    }

    /**
     * Sorts the task list by task type then by the end date time.
     *
     * @return the list of tasklist that is sorted
     */
    public static String sortTask() {
        // Solution below adopted from https://stackoverflow.com/questions/2784514/sort-arraylist-of-custom-objects-by-property
        Collections.sort(taskList, (t1, t2) -> compareTasks(t1, t2));
        storage.saveTasks();
        return listTasks();
    }
    /**
     * Clears the tasklist (for testing purposes)
     *
     */
    public static void clearTaskList() {
        taskList.clear();
    }
}
