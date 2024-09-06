package grok;

import java.util.Scanner;

import commands.Command;
import exceptions.GrokInvalidUserInputException;
import parser.Parser;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

/**
 * The main coordinator engine behind the application
 * - Coordinates the actions of the Ui, storage, task list, and parser
 * - Endlessly waits for commands until the bye command is encountered
 */
public class Grok {
    private static final String TEXT_FILE_DIRECTORY = "./data/duke.txt";
    private final Storage storage;
    private final Ui ui;
    private final TaskList taskList;
    private final Parser parser;
//    private final Scanner scanner;

    /**
     * The main coordinator of this application. Contains the boot-up sequence to start different classes and
     * initialize the application, and coordinate user inputs from user to relevant classes.
     */
    public Grok() {
        storage = new Storage(TEXT_FILE_DIRECTORY);
        taskList = new TaskList(storage.parseTextStorage());
        ui = new Ui();
        parser = new Parser();
//        scanner = new Scanner(System.in);
//
//        ui.printWelcomeMessage();
//        while (true) {
//            try {
//
//            } catch (GrokInvalidUserInputException e) {
//                ui.printErrorMessage(e.getMessage());
//            }
//        }
    }

    public String processResponse(String input) {
        try {
            Command c = parser.parseUserInput(input, taskList);
            c.execute(taskList, ui, storage);
            if (c.isExit()) {

            }
        } catch (GrokInvalidUserInputException e) {
            return e.getMessage();
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello!");
    }
}
