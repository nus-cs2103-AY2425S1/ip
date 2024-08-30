import java.util.List;

public class Ui {

    public void showWelcomeMessage() {
        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Pixy.\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n");
    }

    public void showTaskAdded(Task task, int size) {
        System.out.println("Got it. I've added this task:\n " + task);
        System.out.println("Now you have " + size + " tasks in the list." +
                "\n____________________________________________________________\n");
    }

    public void showListEmpty() {
        System.out.println("List is Empty! Add tasks to list.");
    }

    public void showTasks(List<Task> list) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            System.out.println((i + 1) + ". " + task.toString());
        }
    }

    public void showTaskMarked(Task task) {
        System.out.println(task.toString() +
                "\n____________________________________________________________\n");
    }

    public void showTaskRemoved(Task task, int size) {
        System.out.println(task.toString());
        System.out.println("Now you have " + size + " tasks in the list." +
                "\n____________________________________________________________\n");
    }

    public void showGoodbyeMessage() {
        System.out.println("____________________________________________________________\n");
        System.out.println("Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n");
    }

    public void showError(String message) {
        System.out.println(message);
        System.out.println("____________________________________________________________\n");
    }
}
