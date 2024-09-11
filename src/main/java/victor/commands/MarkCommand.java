package victor.commands;

import java.nio.file.Path;

import victor.messages.ReturnMessage;

/**
 * Mark command class that extends Command class, validates correct input for task to mark
 * as complete and executes mark command on specified task.
 */
public class MarkCommand extends Command {
    private static final String WRONG_TASK_NUMBER = "-1";
    private String taskNumber;

    /**
     * Mark command constructor that takes in additional input and validates that
     * task number was provided at all to mark.
     * @param additionalInput A string array containing all additional inputs to Mark command.
     */
    public MarkCommand(String[] additionalInput) {
        super(additionalInput);
        setTaskNumber();
    }

    /**
     * Mark command constructor that only takes in a task number to mask as complete.
     * Used when reading tasks from file to avoid having to format as typical mark command.
     * @param task An integer representing which task to mark as complete.
     */
    public MarkCommand(int task) {
        super(new String[] {});
        this.taskNumber = String.valueOf(task);
    }

    /**
     * Checks if additional input has a number provided for the mark command. If not,
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
     * inputs with invalid task number to mark by prompting the user to enter a valid number.
     * @return A return message with the mark action summary (successful) or a prompt to the user (unsuccessful).
     */
    @Override
    public ReturnMessage execute() {
        try {
            return new ReturnMessage(super.taskList.markDone(Integer.parseInt(taskNumber)));
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
        if (taskNumber.trim().equals(WRONG_TASK_NUMBER)) {
            return;
        }
        super.taskList.writeToFile(filePath, true);
    }
}
