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
     * @param taskList
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

    public static void printSeparator() {
        System.out.println(Message.SEPARATOR);
    }

    public static String readCommand() throws ZaibotException {
        if (!SCANNER.hasNextLine()) {
            throw new ZaibotException("No input waiting.");
        }
        return SCANNER.nextLine();
    }

    public static void displayError(Exception e) {
        System.out.println(e.getMessage());
    }

    public static void displayTasksNumber(TaskList taskList) {
        System.out.println(String.format(Message.TASKTOTAL.toString(), taskList.getNumberOfTasks()));
    }

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
