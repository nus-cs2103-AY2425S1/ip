import java.util.Scanner;

/**
 * Represents the Ollie task manager that interacts with the user.
 */
public class Ollie {

    /**
     * Greets the user with a welcome message.
     */
    public static void greeting() {
        Task.messageWrapper("Hello there! I'm OLLIE :) \nWhat can I do for you?");
    }

    /**
     * Bids farewell to the user with a goodbye message.
     */
    public static void exit() {
        Task.messageWrapper("Bye. Have a great day. Hope to see you again soon! :) ");
    }

    /**
     * Echoes the user's command and performs the corresponding action.
     *
     * @param userCommand The command entered by the user.
     * @throws OllieException if the user command is invalid or unrecognized.
     */
    public static void echo(String userCommand) throws OllieException {
        if (userCommand.equals("bye")) {
            exit();
        } else if (userCommand.equals("list")) {
            Task.listTasks();
        } else if (userCommand.startsWith("mark ")) {
            try {
                int taskNumber = Integer.parseInt(userCommand.substring(5).trim()) - 1;
                Task.markTaskAsDone(taskNumber);
            } catch (NumberFormatException e) {
                throw new OllieException("Please enter a valid task number to mark as done! ☺");
            }
        } else if (userCommand.startsWith("unmark ")) {
            try {
                int taskNumber = Integer.parseInt(userCommand.substring(7).trim()) - 1;
                Task.unmarkTaskAsDone(taskNumber);
            } catch (NumberFormatException e) {
                throw new OllieException("Please enter a valid task number to unmark as done! ☺");
            }
        } else if (userCommand.startsWith("mark") || userCommand.startsWith("unmark")) {
            throw new OllieException("Please enter the task number to mark or unmark as done! ☺");
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
                Task.messageWrapper(e.getMessage());
            }
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
