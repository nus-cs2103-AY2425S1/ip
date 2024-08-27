public class HelpCommand implements Command {

    public void execute(Storage storage, TaskList tasks, Ui ui) {
        ui.displayHelp();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
