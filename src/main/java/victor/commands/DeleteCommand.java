package victor.commands;

import java.nio.file.Path;

import victor.messages.ReturnMessage;

public class DeleteCommand extends Command {
    private String taskNumber;

    public DeleteCommand(String[] additionalInput) {
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
            return new ReturnMessage(super.taskList.deleteTask(Integer.parseInt(taskNumber)));
        } catch (NumberFormatException e) {
            return new ReturnMessage("  ~  Sorry, I don't think you entered a number for which"
                + " task to delete!");
        }
    }

    @Override
    public void write(Path filePath) {
        if (!taskNumber.trim().equals("-1")) {
            super.taskList.writeToFile(filePath, true);
        }
    }
}
