import java.util.Scanner;

/**
 * Represents the Ollie task manager that interacts with the user.
 */
public class Ollie {

    /**
     * Greets the user with a welcome message.
     */
    public static void greeting() {
        Task.messageWrapper("Hello there! ☺ I'm OLLIE ☺ \nWhat can I do for you? ☺");
    }

    /**
     * Bids farewell to the user with a goodbye message.
     */
    public static void exit() {
        Task.messageWrapper("Bye. Have a great day. ☺ Hope to see you again soon! ☺ ");
    }

    /**
     * Parses the task number from the user's command.
     *
     * @param userCommand The command entered by the user.
     * @param prefixLength The length of the prefix to trim.
     * @return The parsed task number.
     * @throws OllieException If the task number is invalid.
     */
    private static int parseTaskNumber(String userCommand, int prefixLength) throws OllieException {
        try {
            return Integer.parseInt(userCommand.substring(prefixLength).trim()) - 1;
        } catch (NumberFormatException e) {
            throw new OllieException("Please enter a valid task number! ☺");
        }
    }

    /**
     * Echoes the user's command and performs the corresponding action.
     *
     * @param userCommand The command entered by the user.
     */
    public static void echo(String userCommand) {
        try {
            if (userCommand.equals("hello") || userCommand.equals("hi")) {
                greeting();
                return;
            }
            if (userCommand.equals("bye")) {
                exit();
            } else if (userCommand.equals("list")) {
                Task.listTasks();
            } else if (userCommand.startsWith("mark ")) {
                int taskNumber = parseTaskNumber(userCommand, 5);
                Task.markTaskAsDone(taskNumber);
            } else if (userCommand.startsWith("unmark ")) {
                int taskNumber = parseTaskNumber(userCommand, 7);
                Task.unmarkTaskAsDone(taskNumber);
            } else if (userCommand.startsWith("delete ")) {
                int taskNumber = parseTaskNumber(userCommand, 7);
                Task.deleteTask(taskNumber);
            } else if (userCommand.startsWith("mark") || userCommand.startsWith("unmark") || userCommand.startsWith("delete")) {
                throw new OllieException("Please enter the command in the correct format with a task number! ☺");
            } else {
                try {
                    if (userCommand.startsWith("todo")) {
                        Task.addTask(Todo.createTask(userCommand));
                    } else if (userCommand.startsWith("deadline")) {
                        Task.addTask(Deadline.createTask(userCommand));
                    } else if (userCommand.startsWith("event")) {
                        Task.addTask(Event.createTask(userCommand));
                    } else {
                        throw new UnknownTaskTypeException();
                    }
                } catch (OllieException e) {
                    throw e;
                }
            }
        } catch (OllieException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * The main method that runs the Ollie task manager.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) throws OllieException {
        greeting();
        System.out.println();

        String command;
        Scanner input = new Scanner(System.in);

        do {
            command = input.nextLine();
            echo(command);
        } while (!command.equals("bye"));

        input.close();
    }
}