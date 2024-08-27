public class ToDoCommand extends Command {

    private String fullCommand;

    public ToDoCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TalkieMissingArgumentException {
        String[] parts = fullCommand.split(" ", 2); // Split into type and the rest of the input

        if (parts.length == 2) {
            String details = parts[1]; // rest of the input (eg. from, to details)
            Task newToDo = new ToDo(details.trim());
            tasks.addTask(newToDo);
            ui.addMessage(newToDo, tasks.size());
        } else {
            throw new TalkieMissingArgumentException(parts[0], "The 'description' of todo cannot be empty.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
