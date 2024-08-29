package Commands;

import Messages.ReturnMessage;

import java.nio.file.Path;

public class MarkCommand extends Command {
    private String taskNumber;

    public MarkCommand(String[] additionalInput) {
        super(additionalInput);
        if (additionalInput.length == 1) {
            this.taskNumber = "-1";
        } else {
            this.taskNumber = additionalInput[1];
        }
    }

    public MarkCommand(int task) {
        super(new String[] {});
        this.taskNumber = String.valueOf(task);
    }

    @Override
    public ReturnMessage execute() {
        try {
            return new ReturnMessage(super.taskList.markDone(Integer.parseInt(taskNumber)));
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
