public class DeleteCommand extends Command {

    private String fullCommand;

    public DeleteCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)
            throws TalkieMissingArgumentException, TalkieNoTaskFoundException, TalkieInvalidArgumentException {
        String[] temp = this.fullCommand.split(" ");

        // Check if user included an argument
        if (temp.length == 1) {
            throw new TalkieMissingArgumentException(temp[0], "The 'delete' command requires an integer as argument");

            // Check if user included the correct int argument
        } else if (this.isInteger(temp[1])) {
            int index = Integer.parseInt(this.fullCommand.split(" ")[1]);

            // Check if the task is in the list
            if (index <= tasks.size()) {
                Task task = tasks.deleteTask(index);
                ui.deleteMessage(task, tasks.size());
            } else {
                throw new TalkieNoTaskFoundException();
            }
        } else {
            throw new TalkieInvalidArgumentException(temp[0], "The 'delete' command requires an integer as argument");
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
