import java.util.Scanner;
import java.lang.NumberFormatException;

public class FriendlyBot {
    private static Task[] tasks;
    private static int numTasks;

    private static void printHorizontalBar() {
        System.out.println("    ____________________________________________________________");
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
                System.out.println("    Here are the tasks in your list:");
                for (int i = 1; i <= numTasks; i++) {
                    Task task = tasks[i - 1];
                    System.out.println("    " + i + "." + task.toString());
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
                        System.out.println("      " + task.toString());
                    }
                } catch (NumberFormatException e) {
                    System.out.println("    Please input a valid task index!");
                }
            } else if (response.startsWith("unmark")) {
                try {
                    int index = Integer.parseInt(response.split(" ")[1]);
                    if (index > numTasks) {
                        System.out.println("    There's no such task yet!");
                    } else {
                        Task task = tasks[index - 1];
                        task.markAsUndone();
                        System.out.println("    OK, I've marked this task as not done yet:");
                        System.out.println("      " + task.toString());
                    }
                } catch (NumberFormatException e) {
                    System.out.println("    Please input a valid task index!");
                }
            } else if (response.startsWith("todo")) {
                String input = response.split("todo ", 2)[1];
                Task newTask = new ToDo(input);
                tasks[numTasks] = newTask;
                numTasks++;
                System.out.println("    Got it. I've added this task:");
                System.out.println("      " + newTask.toString());
                if (numTasks == 1) {
                    System.out.println("    Now you have " + numTasks + " task in the list.");
                } else {
                    System.out.println("    Now you have " + numTasks + " tasks in the list.");
                }
            } else if (response.startsWith("deadline")) {
                String input = response.split("deadline ", 2)[1];
                String[] descriptions = input.split(" /by ");
                Task newTask = new Deadline(descriptions[0], descriptions[1]);
                tasks[numTasks] = newTask;
                numTasks++;
                System.out.println("    Got it. I've added this task:");
                System.out.println("      " + newTask.toString());
                if (numTasks == 1) {
                    System.out.println("    Now you have " + numTasks + " task in the list.");
                } else {
                    System.out.println("    Now you have " + numTasks + " tasks in the list.");
                }
            } else if (response.startsWith("event")) {
                String input = response.split("event ", 2)[1];
                String[] descriptions = input.split(" /from | /to ");
                Task newTask = new Event(descriptions[0], descriptions[1], descriptions[2]);
                tasks[numTasks] = newTask;
                numTasks++;
                System.out.println("    Got it. I've added this task:");
                System.out.println("      " + newTask.toString());
                if (numTasks == 1) {
                    System.out.println("    Now you have " + numTasks + " task in the list.");
                } else {
                    System.out.println("    Now you have " + numTasks + " tasks in the list.");
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
