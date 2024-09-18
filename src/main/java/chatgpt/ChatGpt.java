package chatgpt;

import chatgpt.command.Command;
import chatgpt.command.Parser;
import chatgpt.exception.ChatBotException;
import chatgpt.storage.Storage;
import chatgpt.task.TaskList;
import chatgpt.ui.Ui;

/**
 * The ChatGpt class is the main class where the application is run.
 * It initialises a Storage for file read/write, Ui for display and inputs
 * and TaskList to store the data locally for manipulation.
 */
public class ChatGpt {
    /** Storage to read and write files **/
    private Storage storage;
    /** Ui for displaying and reading user inputs **/
    private Ui ui;
    /** Stores the list of task **/
    private TaskList tasks;

    private boolean hasErrorLoading = false;

    /**
     * Constructor for the chatbot, initialised with the given filePath as the path
     * to the save file. Save file is also read and stored to the TaskList.
     *
     * @param filePath
     */
    public ChatGpt(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (ChatBotException e) {
            hasErrorLoading = true;
            tasks = new TaskList();
        }
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (ChatBotException e) {
            return ui.showError(e.getMessage());
        }
    }

    public boolean hasErrorLoading() {
        return this.hasErrorLoading;
    }

    public static String getWelcome() {
        return Ui.showWelcome();
    }

    public static String getLoadingError(){
        return Ui.showLoadingError();
    }
}
