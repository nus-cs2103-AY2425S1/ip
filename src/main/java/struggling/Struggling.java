package struggling;

import java.io.IOException;
import java.util.ArrayList;

import struggling.command.Command;
import struggling.task.Task;

/**
 * Struggling class creates a text-based chatbot.
 *
 * @author KengHian
 */
public class Struggling {
    private final ArrayList<Task> taskArr = new ArrayList<>();

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private String commandType;
    private boolean isExit;

    /**
     * Initializes Struggling object by initializing the
     * UI, Storage and TaskList objects.
     *
     * @param filePath Relative file path of save file.
     */
    public Struggling(String filePath) {
        try {
            ui = new Ui();
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showError("Failed to create saveFile, contact the developer!");
        } catch (StrugglingException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Creates an instance and run the chatbot for testing.
     */
    public static void main(String[] args) {
        new Struggling("data/Struggling.txt").run();
    }

    public String getGreeting() {
        try {
            assert ui != null : "Invalid ui object";
            ui.showWelcome();
        } catch (IllegalArgumentException e) {
            ui.showInvalid();
        } catch (StrugglingException e) {
            ui.showError(e.getMessage());
        }

        return ui.getMessage();
    }

    public String getResponse(String input) {
        try {
            assert tasks != null : "Invalid tasks object";
            assert ui != null : "Invalid ui object";
            assert storage != null : "Invalid storage object";

            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            this.isExit = c.isExit();
            commandType = c
                    .getClass()
                    .getSimpleName();
        } catch (IllegalArgumentException e) {
            ui.showInvalid();
        } catch (StrugglingException e) {
            ui.showError(e.getMessage());
        } catch (IOException e) {
            ui.showError("Fail to save task, please contact developer!");
        } catch (Exception e) {
            ui.showError("Unhandled exception, can't you even use the command correctly?");
        }

        if (commandType == null) {
            commandType = "InvalidCommand";
        }

        return ui.getMessage();
    }

    /**
     * Returns the type of command that was last
     * executed.
     */
    public String getCommandType() {
        return commandType != null ? commandType : "InvalidCommand";
    }

    public boolean getIsExit() {
        return this.isExit;
    }

    /**
     * Starts the chatbot for testing.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String cmd = ui.readCommand();
                Command c = Parser.parse(cmd);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (IllegalArgumentException e) {
                ui.showError("OOPS!!! I'm sorry, but I don't know what that means :-(");
            } catch (StrugglingException e) {
                ui.showError(e.getMessage());
            } catch (IOException e) {
                ui.showError("Fail to save task, please contact developer!");
            }
        }
    }
}

