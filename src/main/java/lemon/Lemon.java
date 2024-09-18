package lemon;

import java.util.Scanner;

import lemon.app.Launcher;
import lemon.command.Command;

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
            } catch (IllegalArgumentException e) {
                ui.printInvalidCommand();
            } catch (Exception e) {
                ui.printUnexpectedException(e, "UNEXPECTED ERROR FOUND");
            }
        }
    }
    public void intro() {
        ui.printIntroMsg();
    }

    public void stop() {
        boolean isSaved = storage.saveTasks(tasks);
        if (Launcher.IS_GUI) {
            Launcher.exit();
        } else {
            isRunning = false;
        }
        //TODO: Add Storage failed to save exceptions to remove boolean
    }

    public Ui getUi() {
        return ui;
    }

    public TaskList getTasks() {
        return tasks;
    }


    /**
     * JavaFx Implementation
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
}
