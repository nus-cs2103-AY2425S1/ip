import java.util.ArrayList;

public class Commands {

    public static void addTask(TaskList taskList, Task task) {
        taskList.add(task);
    }
    public static void markTask(TaskList taskList, int taskNumber) {
        int taskIndex = taskNumber - 1;
        Task currTask = taskList.get(taskIndex);
        currTask.setIsDone(true);
    }

    public static void unmarkTask(TaskList taskList, int taskNumber) {
        int taskIndex = taskNumber - 1;
        Task currTask = taskList.get(taskIndex);
        currTask.setIsDone(false);
    }

    public static void deleteTask(TaskList taskList, int taskNumber) {
        int taskIndex = taskNumber -1;
        Task currTask = taskList.get(taskIndex);
        taskList.delete(taskIndex);
    }

}
