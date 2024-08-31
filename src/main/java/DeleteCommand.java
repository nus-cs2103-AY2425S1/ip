public class DeleteCommand extends Command {
    int selection;

    public DeleteCommand(int selection) {
        this.selection = selection;
    }

    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        Task removedTask = tasklist.removeAt(selection);
        output.append("Noted! I've removed this task :").append("\n").append(removedTask.toString()).append("\n")
                .append("Now you have ").append(tasklist.size()).append(" tasks in your list.\n");
        try {
            storage.save(tasklist.getTaskList());
            ui.printString(output.toString());
        } catch (AsuraException e) {
            ui.showError(e.getMessage());
        }
    }

    public boolean isExit() {
        return false;
    }
}
