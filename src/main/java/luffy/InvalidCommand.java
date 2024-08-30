package luffy;

public class InvalidCommand extends Command {

    @Override
    public void executeCmd(LuffyUI ui, Storage taskStorage, TaskList taskList) {
        ui.showErrorMessage("Invalid luffy.Command.");

    }

}