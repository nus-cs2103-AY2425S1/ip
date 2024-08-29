package task;

import ui.Ui;

import java.util.ArrayList;

/**
 * Represents the list of tasks that is being tracked in Tako.
 */
public class TaskList {

    private static ArrayList<Task> taskList = new ArrayList<>();

    public TaskList(ArrayList<Task> tasks) {
        taskList = tasks;
    }

    public static Task get(int i) {
        return taskList.get(i);
    }
    public static int length() {
        return taskList.size();
    }

    /**
     * Adds a specific task into the list of tasks.
     *
     * @param task task to be added.
     */
    public static void addTask(Task task) {
        taskList.add(task);
        Ui.addTaskMessage(task);
    }

    /**
     * Lists out the string representation of the specified task.
     *
     * @param i the position of the task in the list to be listed.
     * @return String the string representation of the task.
     */
    public static String listTask(int i) {
        return (i + 1) + "." + taskList.get(i).toString();
    }

    public static void markTask(int i) {
        taskList.get(i - 1).markAsDone();
        Ui.markTaskMessage(taskList.get(i - 1));
    }

    public static void unmarkTask(int i) {
        taskList.get(i - 1).markAsUndone();
        Ui.unmarkTaskMessage(taskList.get(i - 1));
    }

    public static void deleteTask(int i) {
        Task toBeRemovedTask = taskList.get(i - 1);
        taskList.remove(i - 1);
        Ui.deleteTaskMessage(toBeRemovedTask);
    }

    /**
     * Gets the string representation of all the tasks in the list.
     *
     * @return String string representing every tasks in the list.
     */
    public static String getAllTask() {
        String tasks = "";
        for (int i = 0; i < taskList.size(); i++) {
                tasks += taskList.get(i).toString() + "\n";
        }
        return tasks;
    }

    /**
     * Helps to add the task from the file when the chatbot just starts.
     *
     * @param task task to be loaded into the list
     */
    public static void addTaskLoad(Task task) {
        taskList.add(task);
    }

    /**
     * Helps to mark the task from the file when the chatbot just starts.
     *
     * @param i the index of the file that is being marked as complete.
     */
    public static void markTaskLoad(int i) {
        taskList.get(i).markAsDone();
    }
}
