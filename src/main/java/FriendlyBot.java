import java.util.Scanner;
import java.lang.NumberFormatException;

public class FriendlyBot {
    private static Task[] tasks;
    private static int numTasks;

    private static void printHorizontalBar() {
        System.out.println("    ____________________________________________________________");
    }

    private static String makeTaskDescription(Task task) {
        return "[" + task.getStatusIcon() + "] " + task.description;
    }

    public static void main(String[] args) {
        // Initialise variables
        Scanner reader = new Scanner(System.in);
        tasks = new Task[100];
        numTasks = 0;
        // Start of Chat Bot
        FriendlyBot.printHorizontalBar();
        System.out.println("    Hello! I'm Friendly Bot");
        System.out.println("    What can I do for you?");
        FriendlyBot.printHorizontalBar();
        String response = "";
        // Commands
        while (!response.equals("bye")) {
            response = reader.nextLine();
            FriendlyBot.printHorizontalBar();
            if (response.equals("bye")) {
                System.out.println("    Bye. Hope to see you again soon!");
            } else if (response.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i <= numTasks; i++) {
                    Task task = tasks[i - 1];
                    System.out.println("    " + i + "." + FriendlyBot.makeTaskDescription(task));
                }
            } else if (response.startsWith("mark")) {
                try {
                    int index = Integer.parseInt(response.split(" ")[1]);
                    if (index > numTasks) {
                        System.out.println("    There's no such task yet!");
                    } else {
                        Task task = tasks[index - 1];
                        task.markAsDone();
                        System.out.println("    Nice! I've marked this task as done:");
                        System.out.println("      " + FriendlyBot.makeTaskDescription(task));
                    }
                } catch (NumberFormatException e) {
                    System.out.println("    Please input a valid task index!");
                }
            } else if (response.startsWith("unmark")) {
                int index = Integer.parseInt(response.split(" ")[1]);
                if (index > numTasks) {
                    System.out.println("    There's no such task yet!");
                } else {
                    Task task = tasks[index - 1];
                    task.markAsUndone();
                    System.out.println("    OK, I've marked this task as not done yet:");
                    System.out.println("      " + FriendlyBot.makeTaskDescription(task));
                }
            } else {
                tasks[numTasks] = new Task(response);
                numTasks++;
                System.out.println("    added: " + response);
            }
            FriendlyBot.printHorizontalBar();
        }
    }
}
