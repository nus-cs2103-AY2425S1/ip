package froggy;

public class InvalidCommand extends Command {

    public InvalidCommand() {

    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        System.out.println("Input not recognised. Please input a valid action:");
        System.out.println("todo, event, deadline, mark, unmark, list, bye");
        ui.showLine();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
