package nextgpt;

import java.io.IOException;
import nextgpt.command.Command;

/**
 * Driver class for Next GPT chat bot.
 */
public class NextGPT {
    protected Storage storage;
    protected TaskList tasks;
    protected Ui ui;

    /**
     * Creates NextGPT given the file path of task list.
     *
     * @param filePath File path to local memory task list.
     */
    public NextGPT(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load_tasks());
        } catch(IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Driver function to run chatbot.
     */
    public void run() {
        boolean isExit = false;
        ui.openingMessage();
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (NextGPTException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Run chatbot.
     */
    //public static void main(String[] args) {
    //    new NextGPT("./data/tasks.txt").run();
    //}


    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (NextGPTException e) {
            return e.getMessage();
        }
    }

}
