public class ByeCommand extends Command{

    public ByeCommand() {
        super(false);
    }

    public void execute(TaskList taskList) {
        UI.printExit();
    }
}
