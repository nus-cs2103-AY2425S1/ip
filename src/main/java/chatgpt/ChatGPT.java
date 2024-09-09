package chatgpt;

import chatgpt.command.Command;
import chatgpt.command.Parser;
import chatgpt.exception.ChatBotException;
import chatgpt.storage.Storage;
import chatgpt.task.TaskList;
import chatgpt.ui.Ui;

public class ChatGPT {

    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    /**
     * Constructor for the chatbot, initialised with the given filePath as the path
     * to the save file. Save file is also read and stored to the TaskList.
     *
     * @param filePath
     */
    public ChatGPT(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (ChatBotException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the application in a loop and checks on whether the application
     * should terminate or continue;
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (ChatBotException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }

        }
    }

    /**
     * Main function where the chatbot is initialised and the program is set to run.
     *
     * @param args passed to run the program (but is not set up)
     */
    public static void main(String[] args) {
        new ChatGPT("\\data\\data1.txt").run();
    }
}
