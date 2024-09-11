package victor.commands;

import java.io.IOException;
import java.nio.file.Path;
import java.time.format.DateTimeParseException;

import victor.messages.ReturnMessage;
import victor.tasks.Deadline;

/**
 * Deadline command that extends Command class, has a Deadline object that is generated
 * by the execute method.
 */
public class DeadlineCommand extends Command {
    private Deadline deadline;
    public DeadlineCommand(String[] additionalInput) {
        super(additionalInput);
    }

    /**
     * Overrides the execute method from the Command class. Processes user input and handles
     * inputs with incorrect date formats by returning user prompt messages that state the correct
     * input format. Calls the addTask method of task list to add the task to the program-wide task list.
     * @return A return message with the deadline action summary (successful) or a prompt to the user (unsuccessful).
     */
    @Override
    public ReturnMessage execute() {
        String[] deadlineParts = getDeadlineParts();
        String taskNameString = deadlineParts[0];
        String deadlineString = deadlineParts[1];

        try {
            if (taskNameString.isBlank()) {
                return new ReturnMessage("  ~  Please give a name for the deadline.",
                        "  ~  The format should be \"deadline"
                            + " (description) /by (deadline, in format yyyy-mm-dd or dd-mm-yyyy)\"");
            } else if (deadlineString.isBlank()) {
                return new ReturnMessage("  ~  Please give a deadline for the deadline.",
                        "  ~  The format should be \"deadline"
                            + " (description) /by (deadline, in format yyyy-mm-dd or dd-mm-yyyy)\"");
            }
            this.deadline = new Deadline(taskNameString, deadlineString);
            return new ReturnMessage(super.taskList.addTask(deadline));
        } catch (DateTimeParseException parseException) {
            return new ReturnMessage("  ~  Please format deadline as " + "\"deadline"
                + " (description) /by (deadline, in format yyyy-mm-dd or dd-mm-yyyy)\"");
        }
    }

    private String[] getDeadlineParts() {
        String taskNameString = "";
        String deadlineString = "";

        boolean isDeadline = false;

        for (int i = 1; i < additionalInput.length; i++) {
            if (additionalInput[i].startsWith("/")) {
                isDeadline = true;
                continue;
            }
            if (!isDeadline) {
                taskNameString += " " + additionalInput[i];
            } else {
                deadlineString += " " + additionalInput[i];
            }
        }
        // Trim so that blank space cannot be counted as name for task or deadlines
        taskNameString = taskNameString.trim();
        deadlineString = deadlineString.trim();
        return new String[] {taskNameString, deadlineString};
    }

    /**
     * Overrides the generic write method in the parent Command class. Handles the case where the deadline is null
     * (has not been set or incorrectly generated) by not writing anything. Otherwise, calls the writeToFile method
     * from the TaskList class with the given file path. Appends to file instead of overwriting.
     * @param filePath The file path, relative to the project root directory, where to write the changes.
     */
    @Override
    public void write(Path filePath) {
        try {
            if (this.deadline == null) {
                return;
            }
            this.deadline.writeToFile(filePath);
        } catch (IOException writeException) {
            throw new RuntimeException("Problem writing to file.");
        }
    }

    public Deadline getDeadline() {
        return this.deadline;
    }

}
