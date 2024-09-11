package lbot;

import lbot.command.Command;
import lbot.exception.FileException;
import lbot.exception.LBotException;
import lbot.helper.Parser;
import lbot.helper.Storage;
import lbot.helper.TaskList;
import lbot.helper.Ui;


/**
 * <h1>LBot</h1>
 * The LBot program is a greenfield project that
 * is being built to learn more about Software Engineering principles.
 *
 * @author Lewis Lye
 * @version 0.1
 * @since 2024-08-20
 */
public class LBot {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Public constructor for LBot class.
     * Loads {@link Storage}, {@link Ui} and {@link TaskList}.
     *
     * @param filePath The location where user wishes to save their tasks.
     */
    public LBot(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.readTasksFromFile());
        } catch (FileException e) {
            tasks = new TaskList();
        }
    }

    //===============================================================
    // JavaFX related methods
    //===============================================================

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            assert c != null : "Command should not be null.";
            return c.execute(ui, storage, tasks);
        } catch (LBotException e) {
            return ui.sayException(e.getMessage());
        }
    }

    public String getGreeting() {
        return ui.sayHello();
    }
}

