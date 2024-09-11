package victor.commands;

import java.nio.file.Path;

import victor.messages.ReturnMessage;

/**
 * Delete command that ensures valid input for number of task deleted and
 * executes the delete command.
 */
public class DeleteCommand extends Command {
    private String taskNumber;

    /**
     * Delete command constructor that takes in additional input and validates that
     * task number was provided at all to delete.
     * @param additionalInput A string array containing the words from the delete command input.
     */
    public DeleteCommand(String[] additionalInput) {
        super(additionalInput);
        if (additionalInput.length == 1) {
            this.taskNumber = "-1";
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
        if (!taskNumber.trim().equals("-1")) {
            // length of additional input if delete command cannot be 0
            assert(super.getAdditionalInput().length != 0);
            super.taskList.writeToFile(filePath, true);
        }
    }
}
