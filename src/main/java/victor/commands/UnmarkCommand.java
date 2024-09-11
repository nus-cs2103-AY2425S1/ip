package victor.commands;

import java.nio.file.Path;

import victor.messages.ReturnMessage;

/**
 * Unmark command class that extends Command class, validates correct input for task to unmark
 * as complete and executes unmark command on specified task.
 */
public class UnmarkCommand extends Command {
    private static final String WRONG_TASK_NUMBER = "-1";
    private String taskNumber;

    /**
     * Unmark command constructor that takes in additional input and validates that
     * task number was provided at all to unmark.
     * @param additionalInput A string array containing the words from the unmark command input.
     */
    public UnmarkCommand(String[] additionalInput) {
        super(additionalInput);
        setTaskNumber();
    }

    /**
     * Checks if additional input has a number provided for the unmark command. If not,
     * sets task number to constant wrong value.
     */
    public void setTaskNumber() {
        if (additionalInput.length == 1) {
            this.taskNumber = WRONG_TASK_NUMBER;
        } else {
            this.taskNumber = additionalInput[1];
        }
    }

    /**
     * Overrides the execute method from the Command class. Processes user input and handles
     * inputs with invalid task number to mark by prompting the user to enter a valid number.
     * @return A return message with the unmark action summary (successful) or a prompt to the user (unsuccessful).
     */
    @Override
    public ReturnMessage execute() {
        try {
            return new ReturnMessage(super.taskList.unmarkDone(Integer.parseInt(taskNumber)));
        } catch (NumberFormatException e) {
            return new ReturnMessage("  ~  I don't think there's a task with that number!");
        }
    }

    /**
     * Re-writes the task list to the output file at the file path specified by calling the writeToFile method
     * from the TaskList class.
     * @param filePath The file path, relative to the project root directory, where to write the changes.
     */
    @Override
    public void write(Path filePath) {
        if (taskNumber.trim().equals("-1")) {
            return;
        }
        super.taskList.writeToFile(filePath, true);
    }
}
