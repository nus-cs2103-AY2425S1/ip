package main.froggy;

public class InvalidCommand extends Command {

    public InvalidCommand() {

    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showLine();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
