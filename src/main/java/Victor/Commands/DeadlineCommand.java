package Victor.Commands;

import Victor.Messages.ReturnMessage;
import Victor.Tasks.Deadline;

import java.io.IOException;
import java.nio.file.Path;
import java.time.format.DateTimeParseException;

public class DeadlineCommand extends Command {
    private Deadline deadline;
    public DeadlineCommand(String[] additionalInput) {
        super(additionalInput);
    }
    @Override
    public ReturnMessage execute() {
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

        try {
            if (taskNameString.isBlank()) {
                return new ReturnMessage("  ~  Please give a name for the deadline.",
                        "The format should be \"deadline" +
                        " (description) /by (deadline, in format yyyy-mm-dd or dd-mm-yyyy)\"");
            } else if (deadlineString.isBlank()) {
                return new ReturnMessage("  ~  Please give a deadline for the deadline.",
                        "The format should be \"deadline" +
                        " (description) /by (deadline, in format yyyy-mm-dd or dd-mm-yyyy)\"");
            } else {
                this.deadline = new Deadline(taskNameString, deadlineString);
                return new ReturnMessage(super.taskList.addTask(deadline));
            }
        } catch (DateTimeParseException parseException) {
            return new ReturnMessage("  ~  Please format deadline as " + "\"deadline" +
                    " (description) /by (deadline, in format yyyy-mm-dd or dd-mm-yyyy)\"");
        }
    }

    @Override
    public void write(Path filePath) {
        try {
            if (this.deadline != null) {
                this.deadline.writeToFile(filePath);
            }
        } catch (IOException writeException) {
            throw new RuntimeException("Problem writing to file.");
        }
    }
}
