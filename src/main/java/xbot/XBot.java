package xbot;

import java.io.IOException;

import xbot.parser.Parser;
import xbot.storage.Storage;
import xbot.ui.Ui;

/**
 * The XBot class is the entry point of the chatbot application.
 * It initializes the main components of the chatbot, such as the task list,
 * storage, user interface, and parser. The class contains the main loop
 * that continuously processes user input until the user exits the program.
 */
public class XBot {
    private static TaskList list = new TaskList();
    private static Storage storage = new Storage();
    private static Ui ui = new Ui();
    private static Parser parser = new Parser();

    /**
     * The main method that runs the XBot application.
     * It loads tasks from the storage, shows the welcome message,
     * and processes user commands in a loop until the user exits the program.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
//        try {
//            System.out.println("Hello!");
//            storage.loadTask();
//        } catch (IOException e) {
//            System.out.println("Error loading tasks: " + e.getMessage());
//        }
//
//        ui.showWelcome();
//        String input = ui.readCommand();
//        while (!input.equalsIgnoreCase("bye")) {
//            try {
//                parser.processInput(input, list, ui, storage);
//            } catch (XBotException e) {
//                ui.mainErrorMessage(e);
//            }
//            input = ui.readCommand();
//        }
//        ui.showBye();
//        ui.close();
    }
    public String getResponse(String input) {
        String output;
        try {
            storage.loadTask();
        } catch (IOException e) {
            return ("Error loading tasks: " + e.getMessage());
        }

//        ui.showWelcome();
//        if (!input.equalsIgnoreCase("bye")) {
            try {
                output = parser.processInput(input, list, ui, storage);
            } catch (XBotException e) {
                output = ui.mainErrorMessage(e);
            }
//        }
//        ui.showBye();
//        ui.close();
        return output;
    }
}
