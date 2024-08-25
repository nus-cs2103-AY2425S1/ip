public class HelpCommand implements Command {
    @Override
    public void execute(TaskList actions, Storage storage, Ui ui) {
        ui.showLine();
        ui.printHelp();
        ui.showLine();
    }
}

