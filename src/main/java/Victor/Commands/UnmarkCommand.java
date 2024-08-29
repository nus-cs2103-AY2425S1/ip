package Victor.Commands;

import Victor.Messages.ReturnMessage;

import java.nio.file.Path;

public class UnmarkCommand extends Command {
    private String taskNumber;

    public UnmarkCommand(String[] additionalInput) {
        super(additionalInput);
        if (additionalInput.length == 1) {
            this.taskNumber = "-1";
        } else {
            this.taskNumber = additionalInput[1];
        }

    }
    @Override
    public ReturnMessage execute() {
        try {
            return new ReturnMessage(super.taskList.unmarkDone(Integer.parseInt(taskNumber)));
        } catch (NumberFormatException e) {
            return new ReturnMessage("  ~  I don't think there's a task with that number!");
        }
    }

    @Override
    public void write(Path filePath) {
        if (!taskNumber.trim().equals("-1")) {
            super.taskList.writeToFile(filePath, true);
        }
    }
}
