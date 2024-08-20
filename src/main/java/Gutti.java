import java.util.Scanner;


/**
 * The {@code Gutti} class represents a simple chatbot that can add tasks, list them,
 * <p>
 * The chatbot greets the user, processes commands to add and list tasks, and provides
 * a farewell message before exiting when user decides to exit via "bye" command.
 * </p>
 */

public class Gutti {

    private static Task[] tasks = new Task[100];
    private static int noOfTasks = 0;

    /**
     * <p>
     * This method prints a welcome message and prompts the user to enter commands.
     * </p>
     */
    private static void greetings() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Gutti");
        System.out.println("What can I do for you? Meow");
        System.out.println("____________________________________________________________");
    }

    /**
     * <p>
     * This method prints a goodbye message and indicates that the chatbot is closing.
     * </p>
     */
    private static void goodBye() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon! Meow");
        System.out.println("____________________________________________________________");
    }

    /**
     * Handles user input after user initializes the chatbot.
     * <p>
     * This method continuously reads user input from the console.
     * Lists all tasks when the user types "list", marks a task as done when the user
     * types "mark" followed by the task number, and unmarks a task when the user types "unmark"
     * followed by the task number. Exits the program when the user types "bye".
     * </p>
     */
    private static void echo(){
        Scanner scanner = new Scanner(System.in);
        String input;
        while (true) {
            input = scanner.nextLine();
            if(input.equalsIgnoreCase("bye")) {
                break;
            } else if(input.equalsIgnoreCase("list")) {
                listsTask();
            } else if (input.startsWith("mark ")) {
                try{
                    int taskIndex = Integer.parseInt(input.substring(5)) - 1;
                    try{
                        tasks[taskIndex].markAsDone();
                    }
                    catch(NullPointerException e) {
                        System.out.println("Cannot mark task as done as there is no task added yet! Meow");
                    }
                }
                catch (java.lang.NumberFormatException e) {
                    System.out.println("Meow incorrect format for marking tasks! Ensure you typed : Mark (int) " +
                            "with only 1 space");
                }
            } else if (input.startsWith("unmark ")) {
                try {
                    int taskIndex = Integer.parseInt(input.substring(7)) - 1;
                    try {
                        tasks[taskIndex].unmark();
                    }
                    catch(NullPointerException e) {
                        System.out.println("Cannot unmark Task as there is no task added yet! Meow");
                    }
                }
                catch (java.lang.NumberFormatException e) {
                    System.out.println("Meow incorrect format for unmarking tasks! Ensure you typed : unmark (int) " +
                            "with only 1 space");
                }
            } else {
                createsTask(input);
            }
        }
        scanner.close();
    }


    /**
     * Creates a new task with the provided input and adds it to the task list.
     * <p>
     * This method prints a confirmation message indicating that the task has been added.
     * </p>
     *
     * @param input The description of the task to be added.
     */
    private static void createsTask(String input){
        System.out.println("____________________________________________________________");
        System.out.println("Gutti painstakingly added: " + input);
        System.out.println("____________________________________________________________");
        tasks[noOfTasks] = new Task(input);
        Gutti.noOfTasks++;
    }

    /**
     * Lists all the tasks currently stored.
     * <p>
     * This method prints all tasks stored in the array
     * It is only called when the user types "list".
     * </p>
     */
    private static void listsTask() {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < noOfTasks; i++) {
            System.out.println((i + 1) + ". " + tasks[i]);
        }
        System.out.println("____________________________________________________________");
    }

    /**
     * The entry point of the program.
     * @param args (not used)
     */

    public static void main(String[] args) {

        // Display the greeting message
        greetings();
        // Echos
        echo();
        // Exit message
        goodBye();
    }
}
