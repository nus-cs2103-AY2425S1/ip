public class ListCommand extends Command {
    ListCommand() {
        super(CommandVerb.LIST);
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showList(taskList);
    };

    public boolean isExit() {
        return false;
    }
}
