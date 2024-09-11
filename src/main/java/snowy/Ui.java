package snowy;

import java.util.ArrayList;

/**
 * Represents the main object which prints out the user interface.
 */
public class Ui {

    private static final String GREETING = "Hello! My name is Snowy\n"
            + "What can I do for you?\n";

    private static final String ENDING = "Bye! Hope to see you again soon!\n";

    public String printGreeting() {
        return GREETING;
    }

    public String printEnding() {
        return ENDING;
    }

    public String printIndexError() {
        return "Invalid index input. Please try again.";
    }

    public String printTodoFormatError() {
        return "Invalid input for todo. Make sure that you are following the correct format.\n"
                + "Example:\n"
                + "todo Read Book";
    }

    public String printDeadlineFormatError() {
        return "Invalid input for Deadline. Make sure that you are following the correct format.\n"
                + "Example:\n"
                + "deadline Return Book /by 2024-09-01";
    }

    public String printEventFormatError() {
        return "Invalid input for Event. Make sure that you are following the correct format.\n"
                + "Example:"
                + "event Orientation Camp /from 2024-09-01 /to 20224-09-04";
    }

    public String printUnknownCommand() {
        return "Invalid command given. Please try again.";
    }

    public String printList(TaskList tasks) {
        return tasks.toString();
    }

    public String printMarkDone(Task task) {
        return "Ok, I've marked this task as completed:\n"
                + task.toString();
    }

    public String printMarkIncomplete(Task task) {
        return "Ok, I've marked this task as incomplete:\n"
                + task.toString();
    }

    public String printDeleteTask(Task task) {
        return "Ok, I've deleted this task:\n"
                + task.toString();
    }

    public String printUpdateError() {
        return "Unable to update save file.";
    }

    public String printFoundTask(ArrayList<Task> foundTasks) {
        int i = 1;
        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
        for (Task task : foundTasks) {
            sb.append(i).append(". ").append(task.toString()).append("\n");
            i++;
        }
        return sb.toString();
    }
}
