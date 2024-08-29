package bob;

import java.util.ArrayList;

public class Ui {

    public void showWelcomeMessage(String botName) {
        System.out.println("____________________________________________________________");
        System.out.printf("Hello! I'm %s!\n", botName);
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public void showGoodbyeMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showTaskAdded(Task task, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.printf("  %s\n", task);
        System.out.printf("Now you have %d tasks in the list.\n", size);
    }

    public void showTaskDeleted(String deleted, int size) {
        System.out.println(deleted);
        System.out.printf("Now you have %d tasks in the list.\n", size);
    }

    public void showError(String errorMessage) {
        System.out.println(" Error: " + errorMessage);
    }

    public void showTaskMarked(String mark) {
        System.out.println(mark);
    }

    public void showTaskUnmarked(String unmark) {
        System.out.println(unmark);
    }

    public void showTasksFound(ArrayList<Task> tasksWithKey) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasksWithKey.size(); i++) {
            System.out.printf("%d.%s\n", i + 1, tasksWithKey.get(i));
        }
    }
}
