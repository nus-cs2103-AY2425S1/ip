import java.util.ArrayList;

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

    public static void addTask(Task task) {
        taskList.add(task);
        Ui.addTaskMessage(task);
    }

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

    public static String getAllTask() {
        String tasks = "";
        for (int i = 0; i < taskList.size(); i++) {
                tasks += taskList.get(i).toString() + "\n";
        }
        return tasks;
    }

    public static void addTaskLoad(Task task) {
        taskList.add(task);
    }

    public static void markTaskLoad(int i) {
        taskList.get(i).markAsDone();
    }
}
