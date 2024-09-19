package lemon;

import java.util.Scanner;

import lemon.app.Launcher;
import lemon.command.Command;
import lemon.command.CommandType;

/**
 * Lemon chatbot that can be used to track tasks given to it
 * @author He Yiheng
 */
public class Lemon {
    private Ui ui = new Ui();
    private Storage storage = new Storage();
    private TaskList tasks = new TaskList();
    private boolean isInitialised = false;
    private Scanner systemScanner;
    private boolean isRunning = true;

    /**
     * Constructor for lemon
     */
    public Lemon() {
        systemScanner = new Scanner(System.in);
        tasks = new TaskList();
        isInitialised = storage.loadTasks(tasks);
    }

    /**
     * Execute lemon once initialization is done
     */
    public void executeLemon() {
        if (!isInitialised) {
            ui.printNotInitialisedCorrectly();
            return;
        }

        intro();

        while (isRunning) {
            ui.printBarMsg();
            String input = systemScanner.nextLine();
            ui.printBarMsg();
            try {
                Command command = Parser.parseInputIntoCommand(input);
                command.run(this);
                //storePreviousCommand(command);
            } catch (IllegalArgumentException e) {
                ui.printInvalidCommand();
            } catch (Exception e) {
                ui.printUnexpectedException(e, "UNEXPECTED ERROR FOUND");
            }
        }
    }

    /**
     * Call {@link Ui} to print out the intro message
     */
    public void intro() {
        ui.printIntroMsg();
    }

    /**
     * Stop and exit lemon
     */
    public void stop() {
        boolean isSaved = storage.saveTasks(tasks);
        if (Launcher.IS_GUI) {
            Launcher.exit();
        } else {
            isRunning = false;
        }
        //TODO: Add Storage failed to save exceptions to remove boolean
    }

    /**
     * Return the {@link Ui} stored in lemon
     * @return {@link Ui} reference
     */
    public Ui getUi() {
        return ui;
    }

    /**
     * Return the {@link TaskList} stored in lemon
     * @return {@link TaskList} reference
     */
    public TaskList getTasks() {
        return tasks;
    }

    /**
     * Return the previous {@link Command} executed by lemon
     * @return {@link Command} reference
     */
//    public Command getPreviousCommand() {
//        return previousCommand;
//    }

    /**
     * JavaFx implementation of receiving a response from lemon
     * @param input instruction provided by the user to be processed into a command
     */
    public void respond(String input) {
        try {
            Command command = Parser.parseInputIntoCommand(input);
            command.run(this);
        } catch (IllegalArgumentException e) {
            ui.printInvalidCommand();
        } catch (Exception e) {
            ui.printUnexpectedException(e, "UNEXPECTED ERROR FOUND");
        }
    }

//    private void storePreviousCommand(Command c) {
//        if (c.getCommandType() != CommandType.UNDO) {
//            previousCommand = c;
//        } else {
//            previousCommand = null;
//        }
//    }
}
