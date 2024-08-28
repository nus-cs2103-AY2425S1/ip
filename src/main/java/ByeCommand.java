public class ByeCommand extends Command {
    ByeCommand() {
        super(CommandVerb.BYE);
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showBye();
    };

    public boolean isExit() {
        return true;
    }
}
