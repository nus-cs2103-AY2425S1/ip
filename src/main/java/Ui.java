import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    public void greetings() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Gutti");
        System.out.println("What can I do for you? Meow");
        System.out.println("____________________________________________________________");
    }

    public void goodBye() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon! Meow");
        System.out.println("____________________________________________________________");
    }

    public void generateError(GuttiException e) {
        System.out.println("____________________________________________________________");
        System.out.println(e.getMessage());
        System.out.println("____________________________________________________________");
    }

    public void showTaskList(TaskList tasks) {
        ArrayList<Task> task = tasks.getTasks();
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < task.size(); i++) {
            System.out.println((i + 1) + ". " + task.get(i));
        }
        System.out.println("____________________________________________________________");
    }

    public void showTaskUnmarked(Task task) {
        System.out.println("____________________________________________________________");
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
        System.out.println("____________________________________________________________");
    }

    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}