import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

/**
 * Represents Elsa, a chatbot.
 * @author Aaron
 */
public class Elsa {
    private static final List<Task> THINGS_TO_DO = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Hi! I'm Elsa");
        System.out.println("It's nice to meet you.\nHow can I help you?");
        addLine();
        processUserInput(THINGS_TO_DO);
    }

    /**
     * This method adds a horizontal separator line consisting of underscores.
     */
    private static void addLine() {
        System.out.println("______________________________________");
    }

    /**
     * This method ends the conversation and says bye to the user.
     */
    private static void goodbye() {
        addLine();
        System.out.println("Bye! It was so nice chatting with you.\nSee you again soon!");
        addLine();
    }

    /**
     * This method adds a Todo_task to the list.
     */
    private static void addTodo(List<Task> tasks, String description) {
        Todo newTodo = new Todo(description);
        tasks.add(newTodo);
        addLine();
        System.out.println("Alright, I've added this task:\n  " + tasks.get(tasks.size() - 1) + "\nWe have " +
                           tasks.size() + " tasks in our list now.");
        addLine();
    }

    /**
     * This method adds a Deadline_task to the list.
     */
    private static void addDeadline(List<Task> tasks, String description, String by) {
        Deadline newDeadline = new Deadline(description, by);
        tasks.add(newDeadline);
        addLine();
        System.out.println("Alright, I've added this task:\n  " + tasks.get(tasks.size() - 1) + "\nWe have " +
                           tasks.size() + " tasks in our list now.");
        addLine();
    }

    /**
     * This method adds an Event_task to the list.
     */
    private static void addEvent(List<Task> tasks, String description, String from, String to) {
        Event newEvent = new Event(description, from, to);
        tasks.add(newEvent);
        addLine();
        System.out.println("Alright, I've added this task:\n  " + tasks.get(tasks.size() - 1) + "\nWe have " +
                           tasks.size() + " tasks in our list now.");
        addLine();
    }

    private static void deleteTask(List<Task> tasks, String userInput) {
        // Finds the index of the task that is to be deleted
        int taskIndex = Integer.parseInt(userInput.substring(7)) - 1;
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            // Informs the user that the task has been deleted
            addLine();
            System.out.println("Okay, I've removed this task:\n  " + tasks.get(taskIndex).toString() + "\nWe have " +
                               (tasks.size() - 1) + " tasks in our list now.");
            addLine();
            // Deletes the task from the list
            tasks.remove(taskIndex);
        }
    }

    /**
     * This method lists all the tasks in the task list.
     */
    private static void listTasks(List<Task> tasks) {
        addLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
        addLine();
    }

    /**
     * This method marks any task as done with an 'X'.
     */
    private static void markTask(List<Task> tasks, String userInput) {
        // Finds and marks the task the user is referring to as done
        int taskIndex = Integer.parseInt(userInput.substring(5)) - 1;
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            tasks.get(taskIndex).done();
            // Informs the user that the task has been marked as done
            addLine();
            System.out.println("Great! I've marked it as done:\n  " + tasks.get(taskIndex).toString());
            addLine();
        }
    }

    /**
     * This method unmarks any task by removing any 'X'.
     */
    private static void unmarkTask(List<Task> tasks, String userInput) {
        // Finds and marks the task the user is referring to as not done
        int taskIndex = Integer.parseInt(userInput.substring(7)) - 1;
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            tasks.get(taskIndex).notDone();
            // Informs the user that the task has been marked as not done
            addLine();
            System.out.println("Alright, I've unchecked this task:\n  " + tasks.get(taskIndex).toString());
            addLine();
        }
    }

    /**
     * This method creates a new scanner object to process user input.
     */
    private static void processUserInput(List<Task> tasks) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userInput = scanner.nextLine();

            if (userInput.contains("bye")) {
                goodbye();
                break;
            } else if (userInput.contains("list")) {
                listTasks(tasks);
            } else if (userInput.startsWith("mark")) {
                markTask(tasks, userInput);
            } else if (userInput.startsWith("unmark")) {
                unmarkTask(tasks, userInput);
            } else if (userInput.startsWith("todo")) {
                addTodo(tasks, userInput.substring(5));
            } else if (userInput.startsWith("deadline")) {
                String[] parts = userInput.split(" /by ", 2);
                String description = parts[0].replace("deadline ", "").trim();
                String by = parts[1].trim();
                addDeadline(tasks, description, by);
            } else if (userInput.startsWith("event")) {
                String[] parts = userInput.split(" /from | /to ");
                String description = parts[0].replace("event ", "").trim();
                String from = parts[1].trim();
                String to = parts[2].trim();
                addEvent(tasks, description, from, to);
            } else if (userInput.startsWith("delete")) {
                deleteTask(tasks, userInput);
            } else {
                // Elsa will ask for clarification upon encountering any unrecognised input
                addLine();
                System.out.println("Sorry, I'm unable to perform this action: " + userInput);
                addLine();
            }
        }
        scanner.close();
    }
}
