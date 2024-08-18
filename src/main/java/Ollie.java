import java.util.Scanner;

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
     * @param userCommand the command entered by the user
     */
    public static void echo(String userCommand) {
        if (userCommand.equals("bye")) {
            exit();
        } else if (userCommand.equals("list")) {
            Task.listTasks();
        } else if (userCommand.startsWith("mark ")) {
            int taskNumber = Integer.parseInt(userCommand.substring(5)) - 1;
            Task.markTaskAsDone(taskNumber);
        } else if (userCommand.startsWith("unmark ")) {
            int taskNumber = Integer.parseInt(userCommand.substring(7)) - 1;
            Task.unmarkTaskAsDone(taskNumber);
        } else {
            try {
                Task task = Task.createTask(userCommand);
                Task.addTask(task);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid command: " + e.getMessage());
            }
        }
    }

    /**
     * The main method that runs the Ollie task manager.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
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
