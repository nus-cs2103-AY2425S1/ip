package janet;

import javafx.application.Platform;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Represents the entry point of Janet.
 */
public class Janet {
    private TaskList tasks;
    private final Ui ui;
    private final Storage storage;

    Janet(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList();
    }

    /**
     * Returns the response based on the user input.
     *
     * @param input A string input from the user.
     * @return A string message.
     */
    public String getResponse(String input) {
        return runProgram(input);
    }

    /**
     * Returns a string message to welcome the user.
     * If 'janet.txt' does not exist yet, an additional loading error message will be appended.
     *
     * @return A string message.
     */
    public String startMessage() {
        // if janet.txt cannot be found, ui.showLoadingError() is appended to the message.
        String message = ui.showWelcome();
        try {
            tasks = new TaskList(storage.textFileToArrayList());
        } catch (JanetException e) {
            message += ui.showLoadingError();
        }
        return message;
    }


    /**
     * Returns the appropriate string message based on the user's command.
     *
     * @param userCommand A string input from the user.
     * @return A string response based on string input.
     */
    public String runProgram(String userCommand) {
        String response = "";
        String[] commandDetails = Parser.getCommandDetails(userCommand);    // converts user input into a String array
        try {
            Parser.userInputChecker(commandDetails, tasks.getNumberOfTasks());    // runs all the checks to verify user input

            CommandType commandType = Parser.getCommand(commandDetails);    // get the CommandType
            switch (commandType) {
            case BYE:
                response = ui.exitMessage();
                break;
            case LIST:
                response = ui.showTasks(tasks);
                break;
            case MARK:
                String markedResult = tasks.markAsDone(Integer.parseInt(commandDetails[1]));
                response = ui.showMarkedMessage(markedResult, tasks.getTask(Integer.parseInt(commandDetails[1]) - 1));
                break;
            case UNMARK:
                String unmarkResult = tasks.unmark(Integer.parseInt(commandDetails[1]));
                response = ui.showUnmarkedMessage(unmarkResult, tasks.getTask(Integer.parseInt(commandDetails[1]) - 1));
                break;
            case DELETE:
                response = ui.showDeleteTaskMessage(tasks.getTask(Integer.parseInt(commandDetails[1]) - 1),
                        tasks.getNumberOfTasks() - 1);
                tasks.deleteTask(Integer.parseInt(commandDetails[1]));
                break;
            case TODO:
                Task todo = new ToDo(userCommand);
                tasks.addTaskToList(todo);
                response = ui.showSuccessfulTaskAddition(todo, tasks.getNumberOfTasks());
                break;
            case DEADLINE:
                Task deadline = new Deadline(userCommand);
                tasks.addTaskToList(deadline);
                response = ui.showSuccessfulTaskAddition(deadline, tasks.getNumberOfTasks());
                break;
            case EVENT:
                Task event = new Event(userCommand);
                tasks.addTaskToList(event);
                response = ui.showSuccessfulTaskAddition(event, tasks.getNumberOfTasks());
                break;
            case FIND:
                String[] keywords = Arrays.copyOfRange(commandDetails, 1, commandDetails.length);
                response = ui.showFindMessage(tasks.findTasks(keywords));
                break;
            }
        } catch (JanetException e) {
            response  = e.getMessage();     // returns a message for any invalid user command
        }
        try {
            storage.saveToJanetTextFile(tasks.getListOfTasks());
        } catch (JanetException e) {
            response = ui.showSavingError();
        }
        return response;
    }
}
