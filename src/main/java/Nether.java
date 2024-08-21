/**
 * Produces greetings for users and initializes chat.
 */

import java.util.Scanner;

public class Nether {
    private static final int MAX_TASKS = 100;
    private static final String EXIT_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String MARK_DONE_COMMAND = "mark";
    private static final String MARK_NOT_DONE_COMMAND = "unmark";

    /**
     * The main method where the program starts.
     * Initializes the application, takes user input, and responds based on commands.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        String logo = " _   _      _   _        \n"
                + "| \\ | | ___| |_| |__  ___ _ __ \n"
                + "|  \\| |/ _ \\ __| '_ \\/ _ \\ '__|\n"
                + "| |\\  |  __/ |_| | | ||__/ |  \n"
                + "|_| \\_|\\___|\\__|_| |_\\___|_|\n";
        Task[] tasks = new Task[MAX_TASKS];
        int counter = 0;

        System.out.println("Hello from\n" + logo);
        printHorizontalLine();
        System.out.println("Hello sir! I'm Nether");
        System.out.println("What can I do for you today?");
        printHorizontalLine();

        Scanner scanner = new Scanner(System.in);
        System.out.print("");
        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase(EXIT_COMMAND)) {
                break;
            }

            if (userInput.equalsIgnoreCase(LIST_COMMAND)) {
                printList(tasks);
                printHorizontalLine();
                continue;
            }

            if (userInput.toLowerCase().startsWith(MARK_DONE_COMMAND)
                    || userInput.toLowerCase().startsWith(MARK_NOT_DONE_COMMAND)) {
                int taskNumber = extractTaskNumber(userInput);
                if (taskNumber != -1 && taskNumber <= counter) {
                    Task taskToMark = tasks[taskNumber - 1];
                    if (userInput.toLowerCase().startsWith(MARK_DONE_COMMAND)) {
                        taskToMark.markAsDone();
                        System.out.println("Well done! I've marked this task as done:");
                    } else {
                        taskToMark.markAsNotDone();
                        System.out.println("Understood, I've marked this task as not done:");
                    }
                    System.out.println("  [" + taskToMark.getStatusIcon() + "] " + taskToMark.getDescription());
                }
                printHorizontalLine();
                continue;
            }

            // if not special command above are input, run the code below:
            printHorizontalLine();
            System.out.println("added: " + userInput);
            printHorizontalLine();

            // update tasks array and increment counter
            tasks[counter] = new Task(counter, userInput);
            counter++;
        }

        // exit message
        printHorizontalLine();
        System.out.println("Bye. If you need any more help in the future, feel free to ask me. Enjoy your day!");
        printHorizontalLine();
    }

    /**
     * Prints out a long horizontal line to act as separator in the chat
     */
    private static void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints out the task list along with its status (done or not done)
     *
     * @param tasks array that holds the task list
     */
    private static void printList(Task[] tasks) {
        for (int i = 0; tasks[i] != null; i++) {
            System.out.println((i + 1) + ".[" + tasks[i].getStatusIcon() + "] " + tasks[i].getDescription());
        }
    }

    /**
     * Returns the index/number of the task to be marked/unmarked
     *
     * @param userInput the string input by user
     * @return (index + 1) of the task to be marked/unmarked
     */
    private static int extractTaskNumber(String userInput) {
        try {
            String[] parts = userInput.split(" ");
            return Integer.parseInt(parts[1]);
        } catch (Exception e) {
            return -1;
        }
    }

}
