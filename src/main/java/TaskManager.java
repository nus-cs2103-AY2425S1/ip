import java.util.ArrayList;
public class TaskManager {
    private int taskCount;
    private ArrayList<Task> tasks;
    private boolean taskStatus;
    public TaskManager() {
        taskCount = 0;
        tasks = new ArrayList<>();
    }
    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("____________________________________________________________");
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    public void listTask() {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(" " + (i + 1) + ". " + tasks.get(i).toString());
        }
        System.out.println("____________________________________________________________");
    }

    public void markTask(int num) {
        tasks.get(num - 1).complete();
        System.out.println("____________________________________________________________");
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + tasks.get(num - 1).toString());
        System.out.println("____________________________________________________________");
    }

    public void unmarkTask(int num) {
        tasks.get(num - 1).notComplete();
        System.out.println("____________________________________________________________");
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("   " + tasks.get(num - 1).toString());
        System.out.println("____________________________________________________________");
    }

    public void deleteTask(int num) {
        Task removedTask = tasks.remove(num - 1);
        System.out.println("____________________________________________________________");
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + removedTask.toString());
        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    public String checkStatus() {
        if (taskStatus) {
            return "X";
        } else {
            return " ";
        }
    }
}
