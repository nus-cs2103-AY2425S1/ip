package PurrfessorDipsy.Command;

import PurrfessorDipsy.TaskList.TaskList;
import PurrfessorDipsy.Ui.Ui;

public class ByeCommand extends Command {

    public ByeCommand(String userInput, TaskList tasks, Ui ui) {
        super(userInput, tasks, ui);
    }

    public static boolean isExit(Command command) {
        return command instanceof ByeCommand;
    }

    @Override
    public void execute() {
        ui.printExitMessage();
        System.exit(0);
    }
}
