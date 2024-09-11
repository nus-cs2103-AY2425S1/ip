package victor.commands;

import java.nio.file.Path;

import victor.messages.ReturnMessage;

/**
 * Delete command that ensures valid input for number of task deleted and
 * executes the delete command.
 */
public class DeleteCommand extends Command {
    private static final String WRONG_TASK_NUMBER = "-1";
    private String taskNumber;

    /**
     * Delete command constructor that takes in additional input and validates that
     * task number was provided at all to delete.
     * @param additionalInput A string array containing the words from the delete command input.
     */
    public DeleteCommand(String[] additionalInput) {
        super(additionalInput);
        setTaskNumber();
    }

    /**
     * Checks if additional input has a number provided for the delete command. If not,
     * sets task number to constant wrong value.
     */
    private void setTaskNumber() {
        if (additionalInput.length == 1) {
            this.taskNumber = WRONG_TASK_NUMBER;
        } else {
            this.taskNumber = additionalInput[1];
        }
    }

    /**
     * Overrides the execute method from the Command class. Processes user input and handles
     * inputs with invalid task number to delete by prompting the user to enter a valid number.
     * @return A return message with the delete action summary (successful) or a prompt to the user (unsuccessful).
     */
    @Override
    public ReturnMessage execute() {
        try {
            return new ReturnMessage(super.taskList.deleteTask(Integer.parseInt(taskNumber)));
        } catch (NumberFormatException e) {
            return new ReturnMessage("  ~  Sorry, I don't think you entered a number for which"
                + " task to delete!");
        }
    }

    /**
     * Re-writes the task list to the output file at the file path specified by calling the writeToFile method
     * from the TaskList class.
     * @param filePath The file path, relative to the project root directory, where to write the changes.
     */
    @Override
    public void write(Path filePath) {
        if (taskNumber.trim().equals(WRONG_TASK_NUMBER)) {
            return;
        }
        // length of additional input if delete command cannot be 0
        assert(super.getAdditionalInput().length != 0);
        super.taskList.writeToFile(filePath, true);
    }
}
