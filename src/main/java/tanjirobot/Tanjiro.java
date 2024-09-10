package tanjirobot;

import java.io.IOException;

import storage.Storage;
import system.Parser;
import system.Ui;
import task.Task;

/**
 * The Tanjiro class represents the core of the Tanjiro Bot application.
 * It handles user input, processes commands, and returns appropriate responses.
 */
public class Tanjiro {
    /**
     * Handles the startup sequence of the Tanjiro Bot.
     * This method is called when the bot is first initialised.
     *
     * @return A greeting message.
     */
    public String onStartup() {
        Ui ui = new Ui();
        return ui.greet();
    }
    /**
     * Processes the user input and executes the corresponding command.
     * This method interprets the user's input using the Parser class,
     * performs the required operations, and returns a response.
     *
     * @param userInput Input entered by the user.
     * @return Response from the bot.
     * @throws IOException If there is an error during file operations.
     */
    public String run(String userInput) throws IOException {
        assert true;
        Ui ui = new Ui();
        Storage storage = new Storage();
        Parser parser = new Parser();

        storage.createFile();
        Task.init_list();

        StringBuilder response = new StringBuilder();

        if (parser.containList(userInput)) {
            response.append(parser.performListTasks());
        } else if (parser.containMark(userInput)) {
            response.append(parser.performMark(userInput));
        } else if (parser.containToDo(userInput)) {
            response.append(parser.performToDo(userInput));
        } else if (parser.containDeadline(userInput)) {
            response.append(parser.performDeadline(userInput));
        } else if (parser.containEvent(userInput)) {
            response.append(parser.performEvent(userInput));
        } else if (parser.containDelete(userInput)) {
            response.append(parser.performDelete(userInput));
        } else if (parser.containFind(userInput)) {
            response.append(parser.performFind(userInput));
        } else if (parser.containsView(userInput)) {
            response.append(parser.performView(userInput));
        } else if (parser.containBye(userInput)) {
            response.append(ui.goodbye());
        } else {
            response.append(ui.invalidInput());
        }

        assert !response.isEmpty();
        return response.toString();
    }

}
