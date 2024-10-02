package moimoi;

import java.util.ArrayList;

import moimoi.util.Parser;
import moimoi.util.Storage;
import moimoi.util.TaskList;
import moimoi.util.command.Command;
import moimoi.util.exception.MoiMoiException;
import moimoi.util.task.Task;

/**
 * Represents the chatbot program.
 */
public class MoiMoi {

    private String initialMessage = "Hello, master! How may I help you today? ><";
    private Storage storage;
    private TaskList tasks;

    /**
     * Initializes the chatbot program, with the specified storage path.
     *
     * @param path Path of the storage data file.
     */
    public MoiMoi(String path) {
        try {
            this.storage = new Storage(path);
            this.tasks = new TaskList(this.storage.load());
        } catch (MoiMoiException e) {
            this.initialMessage = e.getMessage() + "\n" + this.initialMessage;
            this.tasks = new TaskList(new ArrayList<Task>());
        }
    }

    /**
     * Returns the program start-up message.
     *
     * @return Program start-up message.
     */
    public String getInitialMessage() {
        return this.initialMessage;
    }

    /**
     * Returns response to the specified input. The response is to be handled by the GUI.
     *
     * @param input User input.
     * @return Response to be handled by the GUI.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(this.storage, this.tasks);
        } catch (MoiMoiException e) {
            return e.getMessage();
        }
    }

}
