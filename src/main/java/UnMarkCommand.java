import java.io.IOException;

public class UnMarkCommand extends Command {

    private String fullCommand;

    public UnMarkCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)
            throws TalkieInvalidArgumentException, TalkieMissingArgumentException, TalkieNoTaskFoundException {
        String[] temp = fullCommand.split(" ");

        // Check if the user included an argument
        if (temp.length == 1) {
            throw new TalkieMissingArgumentException(temp[0], "The 'unmark' command requires an integer as argument");

            // Check if the user included the correct int argument
        } else if (this.isInteger(temp[1])) {
            int index = Integer.parseInt(fullCommand.split(" ")[1]);

            // Check if the task index is valid in the task list
            if (index <= tasks.size()) {
                Task task = tasks.getTask(index);
                task.markAsNotDone();
                ui.unMarkMessage(task);
            } else {
                throw new TalkieNoTaskFoundException();
            }

        } else {
            throw new TalkieInvalidArgumentException(temp[0], "The 'unmark' command requires an integer as argument");
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
