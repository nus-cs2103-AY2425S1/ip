package nextgpt;

import java.io.IOException;

import nextgpt.command.Command;

/**
 * Driver class for Next GPT chatbot.
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
