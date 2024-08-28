package PurrfessorDipsy;

import PurrfessorDipsy.Command.*;
import PurrfessorDipsy.Exception.InvalidCommandException;
import PurrfessorDipsy.Exception.InvalidDateException;
import PurrfessorDipsy.Exception.UnknownCommandException;
import PurrfessorDipsy.Parser.Parser;
import PurrfessorDipsy.Storage.Storage;
import PurrfessorDipsy.TaskList.TaskList;
import PurrfessorDipsy.Ui.Ui;

public class PurrfessorDipsy {
    private final Ui ui;
    private final TaskList taskList;

    public PurrfessorDipsy() {
        this.ui = new Ui();
        this.taskList = Storage.load();  // Load tasks from storage when the application starts
    }

    public static void main(String[] launchArgs) {
        PurrfessorDipsy purrfessorDipsy = new PurrfessorDipsy();
        purrfessorDipsy.run();
    }

    public void run() {
        ui.printWelcomeMessage();
        runCommandLoopUntilExitCommand();
        exit();
    }

    private void runCommandLoopUntilExitCommand() {
        Command command = null;
        do {
            try {
                String userInput = ui.getInput();
                command = Parser.parseCommand(userInput, taskList, ui);
                try {
                    command.execute();
                } catch (InvalidCommandException e) {
                    ui.printErrorMessage(e.getMessage());
                } catch (InvalidDateException e) {
                    ui.printErrorMessage(e.getMessage());
                }
            } catch (UnknownCommandException e) {
                ui.printErrorMessage(e.getMessage());
            } catch (Exception e) {
                ui.printErrorMessage("An unexpected error occurred: " + e.getMessage());
            }
        } while (!ByeCommand.isExit(command));
    }

    private void exit() {
        ui.printExitMessage();
        System.exit(0);
    }
}
