package yoda.commands;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import yoda.TaskList;
import yoda.exceptions.YodaException;
import yoda.tasks.Deadline;

/**
 * Represents a command that adds a deadline task to the task list.
 */
public class DeadlineCommand extends Command {
    private TaskList taskList;
    private String input;

    /**
     * Constructurs a DeadlineCommand with the specified task list and input.
     *
     * @param taskList Task list to add new deadline task to.
     * @param input User input containing task description and deadline.
     */
    public DeadlineCommand(TaskList taskList, String input) {
        this.taskList = taskList;
        this.input = input;
    }

    /**
     * Executes the command to add a new deadline task to the tasklist.
     *
     * @return
     * @throws YodaException if input format was invalid.
     */
    public String run() throws YodaException {
        if (!hasValidFormat(input)) {
            throw new YodaException("A deadline must have a description and due by time, no...?");
        }
        String[] splitInput = input.split(" ", 2);
        String task = splitInput[1];
        String[] splitTask = task.split(" /by ", 2);
        Deadline newTask = null;
        String message = null;
        try {
            LocalDate by = LocalDate.parse(splitTask[1]);
            newTask = new Deadline(splitTask[0], by);
            taskList.add(newTask);
            message = "Added task:\n" + newTask + "\n"
                    + String.format("Now you have %d tasks in the list", taskList.getLength());
        } catch (DateTimeParseException e) {
            throw new YodaException("Deadline must be in format yyyy-mm-dd");
        }
        return message;
    }

    /**
     * Checks if formatting of input is valid for the deadline command.
     *
     * @return true of formatting is valid.
     */
    public boolean hasValidFormat(String input) {
        String[] splitInput = input.split(" ", 2);
        if (splitInput.length == 2) {
            String[] splitTask = splitInput[1].split("/by ", 2);
            return splitTask.length == 2;
        } else {
            return false;
        }
    }
}

