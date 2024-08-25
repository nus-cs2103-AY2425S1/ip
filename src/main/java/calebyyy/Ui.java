package calebyyy;
import calebyyy.Tasks.Task;

public class Ui {
    public Ui() {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Calebyyy");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public void addTaskMessage(Task task, int taskCount) {
        System.out.println("____________________________________________________________");
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + taskCount + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    public void byeMessage() {
        System.out.println("____________________________________________________________");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public void deleteTaskMessage(Task task, int taskCount) {
        System.out.println("____________________________________________________________");
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + taskCount + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    public void listTasksMessage(TaskList taskList) {
        System.out.println("____________________________________________________________");
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < taskList.getTaskCount(); i++) {
            System.out.println(" " + (i + 1) + "." + taskList.getTasks().get(i));
        }
        System.out.println("____________________________________________________________");
    }
}
