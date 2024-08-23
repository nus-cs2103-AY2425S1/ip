public class ExitCommand extends Command {
    public ExitCommand() {
        super("bye", null);
    }

    @Override
    public void runCommandSpecificLogic(TaskList tasks, Storage storage) {
        Ui.printMessage("GOODBYE");
        super.setContinueLoop(false);
    }
}
