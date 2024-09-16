package nuffle;

import nuffle.command.Command;
import nuffle.exception.NuffleException;
import nuffle.parser.Parser;
import nuffle.ui.Ui;
import nuffle.storage.Storage;
import nuffle.task.TaskList;

import java.io.IOException;

public class Nuffle {
    private Storage storage;

    private TaskList inputList;

    private Ui ui;


    public Nuffle(String filePath) {
        try {
            this.storage = new Storage(filePath);
            this.inputList = new TaskList(this.storage.load());
            this.ui = new Ui();

            // ensure that the storage, task list object is not null
            assert this.storage != null;
            assert this.inputList != null;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Processes the user input and returns the appropriate response as a string.
     * Validates that the input is not null or empty.
     *
     * @param input The user's input string.
     * @return The response to the user's input.
     * @throws IOException If an I/O error occurs while saving tasks.
     */
    public String getResponse(String input) throws IOException {
        assert input != null && !input.trim().isEmpty() : "User input should not be null or empty";
        return responseHandler(input);
    }

    /**
     * Handles the response to the user's input by parsing the input and executing the corresponding command.
     * If an unknown command is encountered, an exception is caught, and an error message is generated.
     * The task list is saved after processing the command.
     *
     * @param userInput The user's input string.
     * @return The response after processing the command.
     * @throws IOException If an I/O error occurs while saving tasks.
     */
    public String responseHandler(String userInput) throws IOException {
        assert userInput != null && !userInput.trim().isEmpty() : "User input should not be null or empty";
        String response;

        try {
            Command userCommand = Parser.parseCommand(userInput);
            response = userCommand.execute(inputList, storage, ui);
        } catch (NuffleException e) {
            response = ui.exceptionErrorMessage(e);
        }

        storage.save(inputList.getInputList());
        return response;
    }
    public static void main(String[] args) {
        new Nuffle("./data/Nuffle.txt");
    }
}