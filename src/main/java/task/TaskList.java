package task;

import ui.Ui;

import java.util.ArrayList;

/**
 * Represents the list of tasks that is being tracked in Tako.
 */
public class TaskList {

    private static ArrayList<Task> taskLists = new ArrayList<>();

    public TaskList(ArrayList<Task> tasks) {
        taskLists = tasks;
    }

    public static Task get(int i) {
        assert i > 0 && i < TaskList.length() : "Invalid task index!";
        return taskLists.get(i);
    }
    public static int length() {
        return taskLists.size();
    }

    /**
     * Adds a specific task into the list of tasks.
     *
     * @param task task to be added.
     */
    public static String addTask(Task task) {
        taskLists.add(task);
        return Ui.addTaskMessage(task);
    }

    /**
     * Lists out the string representation of the specified task.
     *
     * @param i the position of the task in the list to be listed.
     * @return String the string representation of the task.
     */
    public static String listTask(int i) {
        return (i + 1) + "." + taskLists.get(i).toString();
    }

    public static String markTask(int i) {
        if (i > TaskList.length()) {
            return Ui.noSuchTaskMessage();
        }
        taskLists.get(i - 1).markAsDone();
        return Ui.markTaskMessage(taskLists.get(i - 1));
    }

    public static String unmarkTask(int i) {
        if (i > TaskList.length()) {
            return Ui.noSuchTaskMessage();
        }
        taskLists.get(i - 1).markAsUndone();
        return Ui.unmarkTaskMessage(taskLists.get(i - 1));
    }

    public static String deleteTask(int i) {
        if (i > TaskList.length()) {
            return Ui.noSuchTaskMessage();
        }
        Task toBeRemovedTask = taskLists.get(i - 1);
        taskLists.remove(i - 1);
        return Ui.deleteTaskMessage(toBeRemovedTask);
    }

    /**
     * Gets the string representation of all the tasks in the list.
     *
     * @return String string representing every tasks in the list.
     */
    public static String getAllTask() {
        String tasks = "";
        for (int i = 0; i < taskLists.size(); i++) {
                tasks += taskLists.get(i).toString() + "\n";
        }
        return tasks;
    }

    /**
     * Helps to add the task from the file when the chatbot just starts.
     *
     * @param task task to be loaded into the list
     */
    public static void addTaskLoad(Task task) {
        taskLists.add(task);
    }

    /**
     * Helps to mark the task from the file when the chatbot just starts.
     *
     * @param i the index of the file that is being marked as complete.
     */
    public static void markTaskLoad(int i) {
        taskLists.get(i).markAsDone();
    }
}
