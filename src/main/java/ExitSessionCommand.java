public class ExitSessionCommand extends Command {
    public ExitSessionCommand() {
       // Empty constructor
    }
    @Override
    public void execute(TaskList tasks, TaskFileManager manager, UserInterface ui) {
        ui.showExitMessage();
    }

    @Override
    public boolean shouldContinue() {
        return false;
    }
}
