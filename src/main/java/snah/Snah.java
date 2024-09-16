package snah;

import java.util.Scanner;

import snah.commands.Command;
import snah.errors.ParsingException;
import snah.util.Parser;
import snah.util.Storage;
import snah.util.Ui;

/**
 * Main class for Snah chatbot
 */
public class Snah {

    private Storage storage;
    private TaskList tasksList;
    private Ui ui;

    /**
     * Constructor for Snah chatbot Initializes the required dependencies for the
     * chatbot
     */
    public Snah() {
        storage = new Storage();
        tasksList = new TaskList(storage);
        ui = new Ui();
    }

    /**
     * Main method for Snah chatbot
     * @param args
     */
    public static void main(String[] args) {
    }

    public String getResponse(String input) {
        try {
            Command currentCommand = Parser.getCommand(input);
            String response = currentCommand.execute(tasksList, storage);
            tasksList.save(storage);
            return response;
        } catch (ParsingException e) {
            return e.getMessage();
        }
    }

    /**
     * Entry point for the CLI chat loop Reads user input and processes the commands
     */
    public void chatLoop() {

        boolean isContinueChat = true;
        Scanner scanner = new Scanner(System.in);

        while (isContinueChat) {
            String input = scanner.nextLine();
            ui.start();
            String response = getResponse(input);
            ui.print(response);
            ui.end();

            if (Parser.getCommand(input).isExit()) {
                isContinueChat = false;
            }

            tasksList.save(storage);
        }
        scanner.close();
    }
}
