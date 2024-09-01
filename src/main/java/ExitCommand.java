public class ExitCommand extends Command {
    public ExitCommand() {}

    void execute(TaskList taskList, Ui ui, Storage storage) {
        storage.writeToFile(taskList.getTaskCommands());
    }
}
