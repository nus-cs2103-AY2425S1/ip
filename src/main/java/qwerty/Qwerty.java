package qwerty;

import qwerty.command.Command;
import qwerty.ui.Ui;

/**
 * This class encapsulates a task helper chatbot.
 */
public class Qwerty {

    /** True if the bot is currently chatting and accepting input */
    private boolean isChatting;
    /** Component dealing with the user interface (print statements). */
    private Ui ui;
    /** List of tasks entered by the user */
    private TaskList tasks;
    /** Component that manages read/write to hard drive */
    private Storage storage;

    /**
     * Creates a new Qwerty instance.
     */
    public Qwerty() {
        this.isChatting = true;
        this.ui = new Ui();
        this.tasks = new TaskList();
        this.storage = new Storage("savefile.txt");
    }

    /**
     * Starts the chatbot.
     */
    public void start() {
        // Initialise the chatbot
        isChatting = true;
        storage.loadTasks(tasks);
        ui.showGreeting();
    }

    /**
     * Handle the given user input. Attempts to create a corresponding command and execute it.
     *
     * @param rawInput String input to execute.
     */
    public void handleUserInput(String rawInput) {
        if (!isChatting) {
            return;
        }

        ui.showUserMessage(rawInput);
        try {
            Command command = Parser.parse(rawInput);
            command.execute(tasks, ui, storage);
            storage.saveTasks(tasks);
            isChatting = !command.isExitCommand();
        } catch (QwertyException e) {
            ui.showError(e.getMessage());
        }
    }
}
