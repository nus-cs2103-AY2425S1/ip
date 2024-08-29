package task;

import ui.Ui;

import java.util.ArrayList;

public class TaskList {

    private static ArrayList<Task> taskLists = new ArrayList<>();

    public TaskList(ArrayList<Task> tasks) {
        taskLists = tasks;
    }

    public static Task get(int i) {
        return taskLists.get(i);
    }
    public static int length() {
        return taskLists.size();
    }

    public static void addTask(Task task) {
        taskLists.add(task);
        Ui.addTaskMessage(task);
    }

    public static String listTask(int i) {
        return (i + 1) + "." + taskLists.get(i).toString();
    }

    public static void markTask(int i) {
        taskLists.get(i - 1).markAsDone();
        Ui.markTaskMessage(taskLists.get(i - 1));
    }

    public static void unmarkTask(int i) {
        taskLists.get(i - 1).markAsUndone();
        Ui.unmarkTaskMessage(taskLists.get(i - 1));
    }

    public static void deleteTask(int i) {
        Task toBeRemovedTask = taskLists.get(i - 1);
        taskLists.remove(i - 1);
        Ui.deleteTaskMessage(toBeRemovedTask);
    }

    public static String getAllTask() {
        String tasks = "";
        for (int i = 0; i < taskLists.size(); i++) {
                tasks += taskLists.get(i).toString() + "\n";
        }
        return tasks;
    }

    public static void addTaskLoad(Task task) {
        taskLists.add(task);
    }

    public static void markTaskLoad(int i) {
        taskLists.get(i).markAsDone();
    }
}
