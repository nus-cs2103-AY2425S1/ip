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
            ui.show("Failed to create saveFile, contact the developer!");
        } catch (StrugglingException e) {
            ui.show(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * For ui testing purpose.
     */
    public static void main(String[] args) {
        new Struggling("data/Struggling.txt").run();
    }

    public String getResponse(String input) {
        try {
            assert tasks != null : "Invalid tasks object";
            assert ui != null : "Invalid ui object";
            assert storage != null : "Invalid storage object";

            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
        } catch (IllegalArgumentException e) {
            ui.showError("OOPS!!! I'm sorry, but I don't know what that means :-(");
        } catch (StrugglingException e) {
            ui.showError(e.getMessage());
        } catch (IOException e) {
            ui.showError("Fail to save task, please contact developer!");
        }

        return ui.getMessage();
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

