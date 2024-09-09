package regina;

import java.util.Scanner;

/**
 * The regina.Ui class handles all user interactions for the regina.Regina chatbot.
 * It provides methods to display messages, prompts, and help information to the user.
 * The class manages the input and output operations, ensuring a user-friendly command-line interface.
 *
 * <p>This class includes the following functionalities:
 * <ul>
 * <li>Display greeting messages, including instructions on using the chatbot.</li>
 * <li>Print error messages with formatting to enhance visibility.</li>
 * <li>Read user input from the console.</li>
 * <li>Print standard and formatted messages with optional indentations.</li>
 * <li>Provide help information outlining available commands and their usage.</li>
 * <li>Exit the application while ensuring resources are properly cleaned up.</li>
 * </ul>
 *
 * <p>Usage of this class is primarily to facilitate communication between the user and the chatbot,
 * enhancing the overall user experience while managing tasks and commands.
 *
 * <p>Note: This class utilizes a {@link Scanner} instance for reading user input from the console,
 * and it is responsible for closing this scanner when no longer needed to prevent resource leaks.
 */
public class Ui {
    private static final String INDENT = "    ";
    private static final String LINE = INDENT + "********************************************************************";
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Greets the user and provides instructions on how to interact with the chatbot.
     *
     * @param name The name of the chatbot, which will be displayed in the greeting message.
     */
    public String greet(String name) {
        String greetingMessage = String.format("""
                Hey there! I'm %s
                I am a chatbot designed to help you track your activities.
                You can add tasks using the following formats:
                1. To add a To-Do task: todo <task_description>
                   Example: todo Finish homework
                2. To add a Deadline task: deadline <task_description> /by <deadline>
                   Example: deadline Submit report /by 2/12/2024 1800
                3. To add an Event task: event <task_description> /from <start_time> /to <end_time>
                   Example: event Team meeting /from 2/12/2024 1600 /to 2/12/2024 1800
                You can also:
                1. Mark a task as done: mark <task_number>
                   Example: mark 1
                2. Unmark a task: unmark <task_number>
                   Example: unmark 1
                3. Delete a task: delete <task_number>
                   Example: delete 1
                4. List tasks: type 'list' to see all your tasks
                5. Delete all current tasks: type 'clear'
                6. Find out current date and time: type 'now'
                7. To snooze the deadline by
                      default value of 30 minutes: snooze <task_number>
                      custom value: snooze <task_number> /by <duration_value> <duration_type>
                      The 3 duration types are: day, hour and minute
                   Example: snooze 1 /by 3 hours
                8. List out all tasks occurring at a specified date and time: occurring <date_and_time>
                   Example: occurring 2/12/2024 1800
                9. For help: type 'help'
                What can I do for you?
                """, name
        );

        System.out.println(greetingMessage);
        return greetingMessage;
    }

    /**
     * Provides help details about the commands the user can use.
     * This method displays a list of available commands and their formats in the UI.
     */
    public String help() {
        String helpMessage = """
                Here are the commands you can use:\s
                - To add a To-Do task: todo <task_description>
                  Example: todo Finish homework
                - To add a Deadline task: deadline <task_description> /by <deadline>
                  Example: deadline Submit report /by 2023-12-01 1600
                - To add an Event task: event <task_description> /from <start_time> /to <end_time>
                  Example: event Team meeting /from 2/12/2024 1600 /to 2/12/2024 1800
                - To mark a task as done: mark <task_number>
                  Example: mark 1
                - To unmark a task: unmark <task_number>
                  Example: unmark 1
                - To delete a task: delete <task_number>
                  Example: delete 1
                - To view your tasks: list
                - To delete all current tasks: clear
                - To snooze a task: snooze <task_number> /by <duration_value> <duration_type>
                - For current date and time: now
                - To view tasks occurring on a specified date and time: occurring <date_and_time>
                  Example: occurring 2/12/2024 1800
                """;
        System.out.println(helpMessage);
        return helpMessage;
    }

    /**
     * Reads a line of input from the user.
     *
     * @return The input string entered by the user.
     */
    public String readInput() {
        return this.scanner.nextLine();
    }

    /**
     * Prints an error message contained within the provided string.
     *
     * @param message The error message to display to the user.
     */
    public String printError(String message) {
        System.out.printf("%s\n%s%s\n%s\n", LINE, INDENT, message, LINE);
        return String.format("%s\n%s%s\n%s\n", LINE, INDENT, message, LINE);
    }

    /**
     * Prints a formatted message surrounded by decorative lines.
     *
     * @param message The message to be printed to the console.
     */
    public String printMessage(String message) {
        String formattedMessage = formatMessage(message);
        System.out.println(formattedMessage);
        return formattedMessage;
    }

    /**
     * Exits the program and closes the scanner.
     * This method displays a goodbye message to the user.
     */
    public void exit() {
        System.out.println(LINE + "\n" + INDENT + "Bye. Hope to see you again soon!\n" + LINE);
        this.scanner.close();
    }

    /**
     * Formats the input message by adding indentation to each new line.
     *
     * @param message The original message string.
     * @return The formatted string with indentation.
     */
    private String formatMessage(String message) {
        StringBuilder formatted = new StringBuilder();
        String[] lines = message.split("\n");
        for (String line : lines) {
            formatted.append(line).append("\n");
        }
        return formatted.toString();
    }
}
