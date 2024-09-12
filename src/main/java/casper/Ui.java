package casper;

import exception.CasperBotException;
import exception.CasperBotOutOfBoundsException;

import java.time.LocalDate;

/**
 * Represents the Ui class
 */
public class Ui {
    /**
     * Represents the enumeration of the various task commands
     */
    public enum TaskCommand {
        MARK, UNMARK, DELETE;
    }
    public Ui() {}

    public String greeting() {
        return "Hello! I'm CasperBot." + System.lineSeparator() + "What can I do for you?";
    }

    /**
     * Returns the response after a command from the user
     * @param command The command that was executed
     * @param task The task that was modified
     */
    public String displayUpdateMessage(TaskCommand command, Task task) {
        String output = "";
        switch (command) {
        case MARK:
            output += "Nice! I've marked this task as done:";
            break;
        case UNMARK:
            output += "OK, I've marked this task as not done yet:";
            break;
        case DELETE:
            output += "Noted. I've removed this task:";
            break;
        default:
            break;
        }
        output += System.lineSeparator() + "  " + task;
        return output;
    }


    /**
     * Returns information about all tasks in the task list provided
     * @throws CasperBotOutOfBoundsException If getTask calls an index out of bounds
     */
    public String displayTaskList(TaskList taskList) throws CasperBotOutOfBoundsException {
        if (taskList.isEmpty()) {
            return "You currently have no tasks in your list!";
        } else {
            StringBuilder output = new StringBuilder("Here are the tasks in your list:");
            for (int i = 0; i < taskList.getNumberOfTasks(); i++) {
                output.append(System.lineSeparator());
                output.append(String.format("%d. %s", i + 1, taskList.getTask(i)));
            }
            return String.valueOf(output);
        }
    }

    /**
     * Returns a string describing the task added and the number of tasks in the task list
     * @param task The task to be added
     * @param numberOfTasks The number of tasks after adding the current task
     */
    public String addTaskMessage(Task task, int numberOfTasks) {
        return "Got it. I've added this task:"
                + System.lineSeparator()
                + "  " + task
                + System.lineSeparator()
                + this.displayTaskListLength(numberOfTasks);
    }

    /**
     * Displays a message regarding how many tasks are in the task list
     * @param length The length of the task list
     */
    public String displayTaskListLength(int length) {
        if (length == 1) {
            return "Now you have 1 task in the list.";
        } else {
            return String.format("Now you have %d tasks in the list.", length);
        }
    }

    /**
     * Displays a message regarding how many matching tasks are in the task list
     * @param taskList The task list where matches are checked for
     * @throws CasperBotOutOfBoundsException If index is out of bounds
     */
    public String displayMatchedTasks(TaskList taskList) throws CasperBotOutOfBoundsException {
        if (taskList.isEmpty()) {
            return "There are no matches in your list.";
        } else {
            StringBuilder output = new StringBuilder("Here are the matching tasks in your list:");
            for (int i = 0; i < taskList.getNumberOfTasks(); i++) {
                output.append(System.lineSeparator());
                output.append(String.format("%d. %s", i + 1, taskList.getTask(i)));
            }
            return String.valueOf(output);
        }
    }
    public String displaySchedule(TaskList taskList, LocalDate date) throws CasperBotOutOfBoundsException {
        if (taskList.isEmpty()) {
            return "There is nothing on your schedule on " + date + ".";
        } else {
            StringBuilder output = new StringBuilder(String.format("Here are the tasks on %s:", date));
            for (int i = 0; i < taskList.getNumberOfTasks(); i++) {
                output.append(System.lineSeparator());
                output.append(String.format("%d. %s", i + 1, taskList.getTask(i)));
            }
            return String.valueOf(output);
        }
    }


    public String exit() {
        return "Bye. Hope to see you again soon!";
    }

    public String showErrorMessage(CasperBotException e) {
        return e.getMessage();
    }
}
