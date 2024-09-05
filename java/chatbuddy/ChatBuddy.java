package chatbuddy;

import chatbuddy.command.Command;
import chatbuddy.exception.ChatBuddyException;
import chatbuddy.parser.Parser;
import chatbuddy.storage.Storage;
import chatbuddy.task.TaskList;
import chatbuddy.ui.Ui;

/**
 * The main class for the ChatBuddy program.
 * Handles the initialization of UI, storage, and task list components, and runs the chatbot.
 */
public class ChatBuddy {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a ChatBuddy object with the specified file path.
     *
     * @param filePath The path of the file to store task data.
     */
    public ChatBuddy(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (ChatBuddyException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Starts the ChatBuddy chatbot, reading and executing commands from the user
     * until the user issues the "bye" command.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (ChatBuddyException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * The main entry point of the program.
     */
    public static void main(String[] args) {
        new ChatBuddy("data/tasks.txt").run();
    }
}
