import java.util.ArrayList;
public class TaskCollection {
    public static ArrayList<Task> taskList = new ArrayList<>();

    public static void printList() {
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println("\t" + (i+1) + ". " + taskList.get(i).getTaskDescription());
        }
    }

    public static void addTask(Task task) {
        taskList.add(task);
        System.out.println("\t added: " + task.getTaskDescription());
    }
}
