public class ByeCommand implements Command {
    @Override
    public void execute(TaskList actions, Storage storage, Ui ui) {
        ui.showGoodbyeMessage();
    }
}

