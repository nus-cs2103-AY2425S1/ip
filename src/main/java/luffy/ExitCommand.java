package luffy;

public class ExitCommand extends Command {

    @Override
    public void executeCmd(LuffyUI ui, Storage taskStorage, TaskList taskList) {
        ui.showExitMessage();
        System.exit(0);
    }

}
