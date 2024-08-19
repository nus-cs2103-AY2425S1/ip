import java.util.Scanner;

/**
 * Represents the Ollie task manager that interacts with the user.
 */
public class Ollie {
    private TaskList taskList;

    /**
     * Constructs an Ollie instance with a new TaskList.
     */
    public Ollie() {
        this.taskList = new TaskList();
    }

    /**
     * Greets the user with a welcome message.
     */
    public void greeting() {
        System.out.println("Hello there! ☺ I'm OLLIE ☺" ) ;
        System.out.println("What can I do for you today? ☺");
    }

    /**
     * Bids farewell to the user with a goodbye message.
     */
    public void exit() {
        System.out.println("Bye. Have a great day. ☺");
        System.out.println("Hope to see you again soon! ☺");
    }

    /**
     * Parses the task number from the user's command.
     *
     * @param userCommand The command entered by the user.
     * @param prefixLength The length of the prefix to trim.
     * @return The parsed task number.
     * @throws OllieException If the task number is invalid.
     */
    private int parseTaskNumber(String userCommand, int prefixLength) throws OllieException {
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
    public void respond(String userCommand) {
        try {
            if (userCommand.equals("hello") || userCommand.equals("hi")) {
                greeting();
                return;
            }
            if (userCommand.equals("bye")) {
                exit();
            } else if (userCommand.equals("list")) {
                taskList.listTasks();
            } else if (userCommand.startsWith("mark ")) {
                int taskNumber = parseTaskNumber(userCommand, 5);
                taskList.markTaskAsDone(taskNumber);
            } else if (userCommand.startsWith("unmark ")) {
                int taskNumber = parseTaskNumber(userCommand, 7);
                taskList.unmarkTaskAsDone(taskNumber);
            } else if (userCommand.startsWith("delete ")) {
                int taskNumber = parseTaskNumber(userCommand, 7);
                taskList.deleteTask(taskNumber);
            } else if (userCommand.startsWith("mark") || userCommand.startsWith("unmark") || userCommand.startsWith("delete")) {
                throw new OllieException("Please enter the command in the correct format with a task number! ☺");
            } else {
                try {
                    if (userCommand.startsWith("todo")) {
                        taskList.addTask(Todo.createTask(userCommand));
                    } else if (userCommand.startsWith("deadline")) {
                        taskList.addTask(Deadline.createTask(userCommand));
                    } else if (userCommand.startsWith("event")) {
                        taskList.addTask(Event.createTask(userCommand));
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
        Ollie ollie = new Ollie();
        ollie.greeting();
        System.out.println();

        String command;
        Scanner input = new Scanner(System.in);

        do {
            command = input.nextLine();
            ollie.respond(command);
        } while (!command.equals("bye"));

        input.close();
    }
}