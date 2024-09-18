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
     * @param task task to be added
     * @return message to show task has been added
     */
    public static String addTask(Task task) {
        taskLists.add(task);
        return Ui.addTaskMessage(task);
    }

    /**
     * Lists out the string representation of the all task.
     *
     * @param i the task number to be output
     * @return String the string representation of all the task
     */
    public static String listTask(int i) {
        return (i + 1) + "." + taskLists.get(i).toString();
    }

    /**
     * Mark the task that is number 'i' on the list
     *
     * @param i the task to be marked
     * @return message of whether the task has been marked
     */
    public static String markTask(int i) {
        if (i > TaskList.length()) {
            return Ui.noSuchTaskMessage();
        }
        taskLists.get(i - 1).markAsDone();
        return Ui.markTaskMessage(taskLists.get(i - 1));
    }

    /**
     * Unmark the task that is number 'i' on the list
     *
     * @param i the task to be unmarked
     * @return message of whether the task has been unmarked
     */
    public static String unmarkTask(int i) {
        if (i > TaskList.length()) {
            return Ui.noSuchTaskMessage();
        }
        taskLists.get(i - 1).markAsUndone();
        return Ui.unmarkTaskMessage(taskLists.get(i - 1));
    }

    /**
     * Delete the task that is number 'i' on the list
     *
     *
     * @param i the task to be deleted
     * @return message of whether the task has been deleted
     */
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
     * @return String string representing every tasks in the list
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

    /**
     * Helps to set the priority of the task from the file when the chatbot just starts to medium.
     *
     * @param i the index of the task that is being set to medium priority
     */
    public static void priorityMediumTaskLoad(int i) {
        taskLists.get(i).setPriority(Task.Priority.MEDIUM);
    }

    /**
     * Helps to set the priority of the task from the file when the chatbot just starts to high.
     *
     * @param i the index of the task that is being set to high priority
     */
    public static void priorityHighTaskLoad(int i) {
        taskLists.get(i).setPriority(Task.Priority.HIGH);
    }

    /**
     * Changes the priority of the task.
     *
     * @param priority the priority to be changed to
     * @param i the index of the task to be changed
     * @return message whether the change of priority is successful
     */
    public static String changePriority(String priority, int i) {
        if (i >= TaskList.length()) {
            return Ui.noSuchTaskMessage();
        }
        if (priority.equalsIgnoreCase("low")) {
            taskLists.get(i).setPriority(Task.Priority.LOW);
        }
        if (priority.equalsIgnoreCase("medium")) {
            taskLists.get(i).setPriority(Task.Priority.MEDIUM);
        }
        if (priority.equalsIgnoreCase("high")) {
            taskLists.get(i).setPriority(Task.Priority.HIGH);
        }
        TaskList.rearrangeTaskList();
        return Ui.changePriorityMessage(priority, i);
    }

    /**
     * Rearranges the task so that 'high' priority task will be listed at the
     * top, followed by 'medium' task then 'low' task.
     */
    public static void rearrangeTaskList() {
        ArrayList<Task> highTask = new ArrayList<>();
        ArrayList<Task> mediumTask = new ArrayList<>();
        ArrayList<Task> lowTask = new ArrayList<>();
        for (int i = 0; i < TaskList.length(); i++) {
            if (taskLists.get(i).getPriority() == Task.Priority.HIGH) {
                highTask.add(taskLists.get(i));
            }
            if (taskLists.get(i).getPriority() == Task.Priority.MEDIUM) {
                mediumTask.add(taskLists.get(i));
            }
            if (taskLists.get(i).getPriority() == Task.Priority.LOW) {
                lowTask.add(taskLists.get(i));
            }
        }
        taskLists.clear();
        taskLists.addAll(highTask);
        taskLists.addAll(mediumTask);
        taskLists.addAll(lowTask);
    }
}
