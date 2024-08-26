import java.util.Scanner;

public class Ui {
    private final static String INDENT = "    ";
    private final static String LINE = INDENT + "********************************************************************";
    private final Scanner SCANNER = new Scanner(System.in);

    public void greet(String name) {
        String greetingMessage = String.format(
                LINE
                        + "\n%sHey there! I'm %s\n"
                        + "%sI am a chatbot designed to help you track your activities.\n"
                        + "%sYou can add tasks using the following formats:\n"
                        + "%s1. To add a To-Do task: todo <task_description>\n"
                        + "%s   Example: todo Finish homework\n"
                        + "%s2. To add a Deadline task: deadline <task_description> /by <deadline>\n"
                        + "%s   Example: deadline Submit report /by 2/12/2024 1800\n"
                        + "%s3. To add an Event task: event <task_description> /from <start_time> /to <end_time>\n"
                        + "%s   Example: event Team meeting /from 2/12/2024 1600 /to 2/12/2024 1800\n"
                        + "%sYou can also:\n"
                        + "%s1. Mark a task as done: mark <task_number>\n"
                        + "%s   Example: mark 1\n"
                        + "%s2. Unmark a task: unmark <task_number>\n"
                        + "%s   Example: unmark 1\n"
                        + "%s3. Delete a task: delete <task_number>\n"
                        + "%s   Example: delete 1\n"
                        + "%s4. List tasks: type 'list' to see all your tasks\n"
                        + "%s5. Delete all current tasks: type 'clear'\n"
                        + "%s6. Find out current date and time: type 'now'\n"
                        + "%s7. List out all tasks occurring at a specified date and time: occurring <date_and_time>\n"
                        + "%s   Example: occurring 2/12/2024 1800\n"
                        + "%s8. For help: type 'help'\n"
                        + "%sWhat can I do for you?\n%s",
                INDENT, name, INDENT, INDENT, INDENT, INDENT,
                INDENT, INDENT, INDENT, INDENT, INDENT, INDENT,
                INDENT, INDENT, INDENT, INDENT, INDENT, INDENT, INDENT,
                INDENT, INDENT, INDENT, INDENT, INDENT, LINE);

        System.out.println(greetingMessage);
    }


    public void help() {
        String helpMessage = String.format(LINE
                        + "\n%sHere are the commands you can use: \n"
                        + "%s- To add a To-Do task: todo <task_description>\n"
                        + "%s  Example: todo Finish homework\n"
                        + "%s- To add a Deadline task: deadline <task_description> /by <deadline>\n"
                        + "%s  Example: deadline Submit report /by 2023-12-01 1600\n"
                        + "%s- To add an Event task: event <task_description> /from <start_time> /to <end_time>\n"
                        + "%s  Example: event Team meeting /from 2/12/2024 1600 /to 2/12/2024 1800\n"
                        + "%s- To mark a task as done: mark <task_number>\n"
                        + "%s  Example: mark 1\n"
                        + "%s- To unmark a task: unmark <task_number>\n"
                        + "%s  Example: unmark 1\n"
                        + "%s- To delete a task: delete <task_number>\n"
                        + "%s  Example: delete 1\n"
                        + "%s- To view your tasks: list\n"
                        + "%s- To delete all current tasks: clear\n"
                        + "%s- For current date and time: now\n"
                        + "%s- To view tasks occurring on a specified date and time: occurring <date_and_time>\n"
                        + "%s  Example: occurring 2/12/2024 1800\n"
                        + "%s\n",
                LINE, INDENT, INDENT, INDENT, INDENT, INDENT, INDENT, INDENT, INDENT,
                INDENT, INDENT, INDENT, INDENT, INDENT, INDENT, INDENT, INDENT, INDENT,
                LINE);

        System.out.println(helpMessage);
    }

    public String readInput() {
        return this.SCANNER.nextLine();
    }

    public void printError(String message) {
        System.out.printf("%s\n%s%s\n%s\n", LINE, INDENT, message, LINE);
    }

    public void printMessage(String message) {
        String formattedMessage = formatMessage(message);
        System.out.println(LINE + "\n" + formattedMessage + LINE);
    }

    public void exit() {
        System.out.println(LINE + "\n" + INDENT + "Bye. Hope to see you again soon!\n" + LINE);
        this.SCANNER.close();
    }

    /**
     * Formats the input message by adding indentation to each new line.
     * @param message The original message string.
     * @return The formatted string with indentation.
     */
    private String formatMessage(String message) {
        StringBuilder formatted = new StringBuilder();
        String[] lines = message.split("\n");
        for (String line : lines) {
            formatted.append(INDENT).append(line).append("\n");
        }
        return formatted.toString();
    }
}
