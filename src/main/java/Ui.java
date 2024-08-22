import java.util.Scanner;

/**
 * This class is responsible for all the UI in the app.
 */
public class Ui {
    private enum Message {
        SEPARATOR("-----------------------------------------------------------"),
        GOODBYE("See you whenever."),
        MARK("Task done. Finally."),
        UNMARK("Task unmarked. Seriously?"),
        DELETE("Task removed. Bye bye."),
        GREETING("Hi, or whatever. What do you want from me today?"),
        ADD("Another day, another task. Added."),
        TASKTOTAL("You have %d task(s). Get moving.");

        private final String msg;

        Message(String msg) {
            this.msg = msg;
        }

        @Override
        public String toString() {
            return this.msg;
        }
    }

    public static Scanner SCANNER = new Scanner(System.in);

    /**
     * Prints a message into the command line.
     * @param msg The message requested to be printed out.
     */
    public static void printMessage(String msg) {
        System.out.println(Message.valueOf(msg.toUpperCase()));
    }

    private static void printMessage(Message msg) {
        System.out.println(msg.toString());
    }

    /**
     * Prints the task list.
     * @param taskList The task list
     */
    public static void printTaskList(TaskList taskList) {
        System.out.print(taskList.toString());
    }

    /**
     * Prints the greeting message.
     */
    public static void printGreeting() {
        System.out.println(Message.GREETING);
    }

    /**
     * Prints the separator line
     */
    public static void printSeparator() {
        System.out.println(Message.SEPARATOR);
    }

    /**
     * Reads a command from the input
     * @return The command read in
     * @throws ZaibotException when there is no next line.
     */
    public static String readCommand() throws ZaibotException {
        if (!SCANNER.hasNextLine()) {
            throw new ZaibotException("No input waiting.");
        }
        return SCANNER.nextLine();
    }

    /**
     * Displays the error message.
     * @param e The exception
     */
    public static void displayError(Exception e) {
        System.out.println(e.getMessage());
    }

    /**
     * Displays the number of tasks.
     * @param taskList The list of tasks
     */
    public static void displayTasksNumber(TaskList taskList) {
        System.out.println(String.format(Message.TASKTOTAL.toString(), taskList.getNumberOfTasks()));
    }

    /**
     * Displays the task, and the type of update that was done to it
     * @param task The task
     * @param type Either "mark", "unmark", "add"
     * @param taskList The list of tasks
     * @throws ZaibotException If the update task is not part of the values for task above.
     */
    public static void displayTask(Task task,
                                         String type,
                                         TaskList taskList) throws ZaibotException {
        switch (type) {
            case "mark":
            case "unmark":
            case "add":
                printMessage(Message.SEPARATOR);
                System.out.println(task);
                printMessage(type);
                displayTasksNumber(taskList);
                printMessage(Message.SEPARATOR);
                break;
            default:
                throw new ZaibotException("Updating task not of correct format.");
        }
    }
}
