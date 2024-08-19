import java.util.Scanner;
import java.lang.NumberFormatException;
import java.util.ArrayList;

/**
 * FriendlyBot is a simple task management bot that can list, mark, and unmark tasks.
 */
public class FriendlyBot {
    private static ArrayList<Task> tasks;
    private static int numTasks;

    /**
     * Prints a horizontal bar for visual separation in the console output.
     */
    private static void printHorizontalBar() {
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Prints a farewell message to the console.
     */
    private static void exit() {
        System.out.println("    Bye. Hope to see you again soon!");
    }

    /**
     * Lists all the tasks currently in the task list.
     */
    private static void list() {
        System.out.println("    Here are the tasks in your list:");
        for (int i = 1; i <= numTasks; i++) {
            Task task = tasks.get(i - 1);
            System.out.println("    " + i + "." + task.toString());
        }
    }

    /**
     * Marks a task as done based on the input index.
     *
     * @param input The input string containing the command and the task index.
     */
    private static void mark(String input) {
        try {
            int index = Integer.parseInt(input.split(" ")[1]);
            if (index > numTasks) {
                System.out.println("    There's no such task yet!");
            } else {
                Task task = tasks.get(index - 1);
                task.markAsDone();
                System.out.println("    Nice! I've marked this task as done:");
                System.out.println("      " + task.toString());
            }
        } catch (NumberFormatException e) {
            System.out.println("    Please input a valid task index!");
        }
    }

    /**
     * Marks a task as not done based on the input index.
     *
     * @param input The input string containing the command and the task index.
     */
    private static void unmark(String input) {
        try {
            int index = Integer.parseInt(input.split(" ")[1]);
            if (index > numTasks) {
                System.out.println("    There's no such task yet!");
            } else {
                Task task = tasks.get(index - 1);
                task.markAsUndone();
                System.out.println("    OK, I've marked this task as not done yet:");
                System.out.println("      " + task.toString());
            }
        } catch (NumberFormatException e) {
            System.out.println("    Please input a valid task index!");
        }
    }

    /**
     * Deletes a task based on the input index.
     *
     * @param input The input string containing the task index.
     */
    private static void delete(String input) {
        try {
            int index = Integer.parseInt(input.split(" ")[1]);
            if (index > numTasks) {
                System.out.println("    There's no such task yet!");
            } else {
                Task task = tasks.remove(index - 1);
                numTasks--;
                System.out.println("    Noted. I've removed this task:");
                System.out.println("      " + task.toString());
                if (numTasks == 1) {
                    System.out.println("    Now you have 1 task in the list.");
                } else if (numTasks == 0) {
                    System.out.println("    You have no more tasks!");
                } else {
                    System.out.println("    Now you have " + numTasks + " tasks in the list.");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("    Please input a valid task index!");
        }
    }

    /**
     * Creates a new todo task based on the input response.
     *
     * @param response The input string containing the task description.
     */
    private static void createTodo(String response) {
        try {
            String input = response.split("todo ", 2)[1];
            Task newTask = new ToDo(input);
            tasks.add(newTask);
            numTasks++;
            System.out.println("    Got it. I've added this task:");
            System.out.println("      " + newTask.toString());
            if (numTasks == 1) {
                System.out.println("    Now you have " + numTasks + " task in the list.");
            } else {
                System.out.println("    Now you have " + numTasks + " tasks in the list.");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("    Please follow this format: todo {task_description}");
        }
    }

    /**
     * Creates a new deadline task based on the input response.
     *
     * @param response The input string containing the task description and deadline.
     */
    private static void createDeadline(String response) {
        try {
            String input = response.split("deadline ", 2)[1];
            String[] descriptions = input.split(" /by ");
            Task newTask = new Deadline(descriptions[0], descriptions[1]);
            tasks.add(newTask);
            numTasks++;
            System.out.println("    Got it. I've added this task:");
            System.out.println("      " + newTask.toString());
            if (numTasks == 1) {
                System.out.println("    Now you have " + numTasks + " task in the list.");
            } else {
                System.out.println("    Now you have " + numTasks + " tasks in the list.");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("    Please follow this format: deadline {task_description} /by {date}");
        }
    }

    /**
     * Creates a new event task based on the input response.
     *
     * @param response The input string containing the task description, start date, and end date.
     */
    private static void createEvent(String response) {
        try {
            String input = response.split("event ", 2)[1];
            String[] descriptions = input.split(" /from | /to ");
            Task newTask = new Event(descriptions[0], descriptions[1], descriptions[2]);
            tasks.add(newTask);
            numTasks++;
            System.out.println("    Got it. I've added this task:");
            System.out.println("      " + newTask.toString());
            if (numTasks == 1) {
                System.out.println("    Now you have " + numTasks + " task in the list.");
            } else {
                System.out.println("    Now you have " + numTasks + " tasks in the list.");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("    Please follow this format: event {task_description} /from {date} /to {date}");
        }
    }

    /**
     * The main method to run FriendlyBot.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        // Initialise variables
        Scanner reader = new Scanner(System.in);
        tasks = new ArrayList<>();
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
                FriendlyBot.exit();
            } else if (response.equals("list")) {
                FriendlyBot.list();
            } else if (response.startsWith("mark")) {
                FriendlyBot.mark(response);
            } else if (response.startsWith("unmark")) {
                FriendlyBot.unmark(response);
            } else if (response.startsWith("delete")) {
                FriendlyBot.delete(response);
            } else if (response.startsWith("todo")) {
                FriendlyBot.createTodo(response);
            } else if (response.startsWith("deadline")) {
                FriendlyBot.createDeadline(response);
            } else if (response.startsWith("event")) {
                FriendlyBot.createEvent(response);
            } else {
                System.out.println("    OOPS!! I'm sorry, that's not a command :-(");
            }
            FriendlyBot.printHorizontalBar();
        }
    }
}
