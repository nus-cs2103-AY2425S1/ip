package Mentos;

import Mentos.Commands.Command;
import Mentos.MentosException.MentosException;
import Mentos.components.*;
import Mentos.task.TaskList;

public class Mentos {
    private TaskList tasks;
    private final Storage storage;

    private final GuiResponse gui;

    private final Parser parser = new Parser();

    public Mentos(String FILE_PATH) {
        this.storage = new Storage(FILE_PATH);
        this.gui = new GuiResponse();
        try {
            this.tasks = new TaskList(storage.loadTasksFromFile());
        } catch (MentosException error) {
            this.tasks = new TaskList();
        }
        assert this.tasks != null : "TaskList should be initialized.";
    }


    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        return responseHandler(input);
    }

    /**
     * Handles user input and returns the corresponding response for the chatbot.
     *
     * <p>This method processes the input command by calling the {@code taskHandler} to determine the action
     * to be performed (e.g., list, mark, unmark, delete, find, or adding a task). It generates an appropriate
     * response based on the action and updates the task list accordingly. The response is constructed using
     * the GUI and saved to the file.</p>
     *
     * @param input The user input command, which could be "bye", "list", "mark", "unmark", "delete", "find", or a task-related command.
     * @return A string representing the chatbot's response to the user, which may include task details, error messages, or confirmation messages.
     */

    public String responseHandler(String input) {
        String response = "";
        if (input.equals("bye")) {
            return this.gui.byeUser();
        }
        try {
            Command command = parser.taskHandler(input);
            response = command.execute(this.tasks);
            storage.saveTasksToFile(this.tasks);
            return response;
        } catch (Exception err) {
            return err.toString();
        }
    }
}

