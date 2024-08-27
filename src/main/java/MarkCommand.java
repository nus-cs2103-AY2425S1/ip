import java.io.IOException;

public class MarkCommand extends Command {

    private String fullCommand;

    public MarkCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)
            throws TalkieInvalidArgumentException, TalkieMissingArgumentException, TalkieNoTaskFoundException {
        String[] temp = fullCommand.split(" ");

        // Check if the user included an argument
        if (temp.length == 1) {
            throw new TalkieMissingArgumentException(temp[0], "The 'mark' command requires an integer as argument");

            // Check if user included the correct int argument
        } else if (this.isInteger(temp[1])) {
            int index = Integer.parseInt(fullCommand.split(" ")[1]);

            // Check if the task is in the list
            if (index <= tasks.size()) {
                Task task = tasks.getTask(index);
                task.markAsDone();
                ui.markMessage(task);
            } else {
                throw new TalkieNoTaskFoundException();
            }

        } else {
            throw new TalkieInvalidArgumentException(temp[0], "The 'mark' command requires an integer as argument");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    private boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
