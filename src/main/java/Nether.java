/**
 * Produces greetings for users and initializes chat.
 */

import java.util.Arrays;
import java.util.Scanner;

public class Nether {
    private static final int MAX_TASKS = 100;

    private static final String EXIT_COMMAND = "bye";

    private static final String LIST_COMMAND = "list";
    private static final String MARK_DONE_COMMAND = "mark";
    private static final String MARK_NOT_DONE_COMMAND = "unmark";

    private static final String TODO_TASK_COMMAND = "todo";
    private static final String DEADLINE_TASK_COMMAND = "deadline";
    private static final String EVENT_TASK_COMMAND = "event";

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
                printHorizontalLine();
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
                    System.out.println("  " + taskToMark);
                }
                printHorizontalLine();
                continue;
            }

            // if none of the special command above are input, run the code below:
            printHorizontalLine();
            System.out.println("Got it. I've added this task:");

            String[] processedInput;
            if (userInput.toLowerCase().startsWith(TODO_TASK_COMMAND)) {
                processedInput = extractInputDetails(userInput, TODO_TASK_COMMAND);
                tasks[counter] = new TodoTask(processedInput[0]);
            } else if (userInput.toLowerCase().startsWith(DEADLINE_TASK_COMMAND)) {
                processedInput = extractInputDetails(userInput, DEADLINE_TASK_COMMAND);
                tasks[counter] = new DeadlineTask(processedInput[0], processedInput[1]);
            } else if (userInput.toLowerCase().startsWith(EVENT_TASK_COMMAND)) {
                processedInput = extractInputDetails(userInput, EVENT_TASK_COMMAND);
                tasks[counter] = new EventTask(processedInput[0], processedInput[1], processedInput[2]);
            }

            System.out.println("  " + tasks[counter].toString());
            counter++;
            System.out.println("Now you have " + counter + " tasks in the list.");
            printHorizontalLine();
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
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; tasks[i] != null; i++) {
            System.out.println((i + 1) + ". " + tasks[i].toString());
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

    /**
     * processes the user input into parts using split and regex to make it easier to instantiate the respective tasks
     *
     * @param userInput input by the user
     * @param taskType can be todo, deadline, or event
     * @return a String array of the parts of the user input that will be used for the 
     *
     */
    private static String[] extractInputDetails(String userInput, String taskType) {
        String[] preprocessArray;
        String[] resultArray;
        switch (taskType) {
        case "todo":
            preprocessArray = userInput.split("todo ", 2);
            resultArray = new String[]{preprocessArray[1]};
            break;
        case "deadline":
            preprocessArray = userInput.split("deadline ", 2);
            String[] deadlineParts = preprocessArray[1].split("/by ", 2);
            resultArray = new String[2];
            resultArray[0] = deadlineParts[0]; // Task description
            resultArray[1] = deadlineParts[1]; // Task deadline
            break;
        case "event":
            preprocessArray = userInput.split("event ", 2);
            String[] eventParts = preprocessArray[1].split("/from |/to ", 3);
            resultArray = new String[3];
            resultArray[0] = eventParts[0]; // Event description
            resultArray[1] = eventParts[1]; // Event start time
            resultArray[2] = eventParts[2]; // Event end time
            break;
        default:
            resultArray = new String[]{};
        }
        return resultArray;
    }

}
