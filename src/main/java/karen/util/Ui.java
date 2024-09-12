package karen.util;

import java.util.NoSuchElementException;
import java.util.Scanner;

import karen.tasks.Task;
import karen.tasks.TaskList;

/**
 * Handles all the input/output of the program through the command line
 */
public class Ui {
    private static final String LINE = "_______________________\n";
    private final Scanner scanner;


    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Returns a string from the next line of user input
     */
    public String readInput() {
        try {
            String result = this.scanner.nextLine();
            return result;
        } catch (NoSuchElementException e) {
            System.err.println("Ui readInput: no such element exception");
            return "";
        }
    }

    /**
     * Prints a simple welcome message
     */
    public String showWelcome() {
        String output = LINE
                + "Hi! I'm Karen\n"
                + "What can I do for you?\n"
                + LINE;
        System.out.print(output);
        return output;
    }

    /**
     * Prints a simple goodbye message
     */
    public String sayGoodbye() {
        String output = LINE
                + "Bye! Hope to see you again!\n"
                + LINE;
        System.out.print(output);
        return output;
    }

    /**
     * Prints an acknowledgement message after a <code>Task</code> is marked
     * @param t The <code>Task</code> that was marked
     */
    public String showMarkMessage(Task t) {
        String output = LINE
                + "Nice! I've marked this task as done:\n\t"
                + t.toString() + "\n"
                + LINE;
        System.out.print(output);
        return output;
    }

    /**
     * Prints a simple acknowledgement message when a <code>Task</code> is unmarked
     * @param t The <code>Task</code> that was unmarked
     */
    public String showUnmarkMessage(Task t) {
        String output = LINE
                + "Ok! This task is now marked undone:\n\t"
                + t.toString() + "\n"
                + LINE;
        System.out.print(output);
        return output;
    }

    /**
     * Prints an acknowledgement message when a new <code>Task</code> is added
     * @param t The <code>Task</code> that was added
     * @param taskList The <code>TaskList</code> that the Task was added to
     */
    public String showAddTaskMessage(Task t, TaskList taskList) {
        String output = LINE
                + "Got it! Added this task:\n\t"
                + t.toString() + "\n"
                + String.format("Now you have %d tasks in the list.\n", taskList.getSize())
                + LINE;
        System.out.print(output);
        return output;
    }

    /**
     * Prints an acknowledgement message when a <code>Task</code> is deleted
     * @param t The <code>Task</code> that was deleted
     */
    public String showDeleteMessage(Task t) {
        String output = LINE
                + "Alright! I've removed this task from your list:\n\t"
                + t.toString() + "\n"
                + LINE;
        System.out.print(output);
        return output;
    }

    /**
     * Prints the contents of a <code>TaskList</code>
     * @param taskList The <code>TaskList</code> to display
     */
    public String displayTaskList(TaskList taskList) {
        String[] taskStrings = taskList.toTaskStrings();

        String output;
        if (taskStrings.length == 0) {
            output = LINE
                    + "No tasks yet!\n"
                    + LINE;
        } else {
            output = LINE;
            for (int i = 0; i < taskStrings.length; i++) {
                output += String.format("%d. %s\n", i + 1, taskStrings[i]);

            }
            output += LINE;
        }
        System.out.print(output);
        return output;
    }

    /**
     * Prints the output from executing the <Code>FindCommand</Code> on a <code>TaskList</code>
     * @param foundTasks A <code>TaskList</code> containing <code>Tasks</code> which match the search string
     */
    public String showFindOutput(TaskList foundTasks) {
        String[] strArr = foundTasks.toTaskStrings();
        String output = LINE
                + "Here are the matching tasks in your list:\n";
        for (int i = 0; i < strArr.length; i++) {
            output += String.format("%d. %s\n", i + 1, strArr[i]);
        }
        output += LINE;
        System.out.print(output);
        return output;
    }

    /**
     * Prints the message when a 'find' command fails to find anything
     */
    public String showFoundNothingMessage() {
        String output = LINE
                + "Sorry! Could not find any matching tasks :(\n"
                + LINE;
        System.out.print(output);
        return output;
    }

    /**
     * Prints an error message when the user inputs an unknown command
     */
    public String showUnknownInputError() {
        String output = LINE
                + "Sorry! I couldn't recognise that :(\n"
                + "Try one of these commands:\n"
                + "\t list\n"
                + "\t todo <task name>\n"
                + "\t deadline <task name> /by <datetime>\n"
                + "\t event <task name> /from <datetime /to <datetime>\n"
                + "\t mark <task index>\n"
                + "\t unmark <task index>\n"
                + "\t delete <task index>\n"
                + "\t bye\n"
                + LINE;
        System.out.print(output);
        return output;
    }

    /**
     * Prints an error message when the user inputs invalid syntax when adding a new <code>Todo</code>
     */
    public String showTodoSyntax() {
        String output = LINE
                + "Invalid syntax. Please follow this syntax for Todos:\n"
                + "\t todo <task name>\n"
                + LINE;
        System.out.print(output);
        return output;
    }

    /**
     * Prints an error message when the user inputs invalid syntax when adding a new <code>Deadline</code>
     */
    public String showDeadlineSyntax() {
        String output = LINE
                + "Invalid syntax. Please follow this syntax for Deadlines:\n"
                + "\t deadline <task name> /by <datetime>\n"
                + LINE;
        System.out.print(output);
        return output;
    }

    /**
     * Prints an error message when user inputs invalid syntax when adding a new <code>Event</code>
     */
    public String showEventSyntax() {
        String output = LINE
                + "Invalid syntax. Please follow this syntax for Events:\n"
                + "\t event <task name> /from <datetime> /to <datetime>\n"
                + LINE;
        System.out.print(output);
        return output;
    }

    /**
     * Prints an error message when user inputs invalid syntax when marking a <code>Task</code>
     */
    public String showMarkSyntax() {
        String output = LINE
                + "Invalid syntax. Please follow this syntax for Mark:\n"
                + "\t mark <task index>\n"
                + LINE;
        System.out.print(output);
        return output;
    }

    /**
     * Prints an error message when user inputs invalid syntax when unmarking a <code>Task</code>
     */
    public String showUnmarkSyntax() {
        String output = LINE
                + "Invalid syntax. Please follow this syntax for Unmark:\n"
                + "\t unmark <task index>\n"
                + LINE;
        System.out.print(output);
        return output;
    }

    /**
     * Prints an error message when the user inputs invalid syntax when deleting a <code>Task</code>
     */
    public String showDeleteSyntax() {
        String output = LINE
                + "Invalid syntax. Please follow this syntax for Delete:\n"
                + "\t delete <task index>\n"
                + LINE;
        System.out.print(output);
        return output;
    }

    /**
     * Prints an error message when user inputs invalid syntax when finding a <code>Task</code>
     */
    public String showFindSyntax() {
        String output = LINE
                + "Invalid syntax. Please follow this syntax for Find:\n"
                + "\t find <search string>\n"
                + LINE;
        System.out.print(output);
        return output;
    }

    /**
     * Prints an error message when user fails to input a valid digit
     */
    public String showNotANumberError() {
        String output = LINE
                + "Please enter a valid number!\n"
                + LINE;
        System.out.print(output);
        return output;
    }

    /**
     * Prints an error message when user fails to input a valid DateTime
     */
    public String showDateTimeError() {
        String output = LINE
                + "Invalid format! Datetime must be in this form: year-month-day 24hr_time\n"
                + "E.g. 2024-10-11 1200\n"
                + LINE;
        System.out.print(output);
        return output;
    }
}
