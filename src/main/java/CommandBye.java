public class CommandBye extends Command {
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        ui.goodbyeMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
