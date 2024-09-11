package nimbus;

import java.io.IOException;
import java.util.Scanner;

import nimbus.exception.WrongDateTimeFormatException;
import nimbus.ui.Parser;
import nimbus.ui.Storage;
import nimbus.ui.TaskList;
import nimbus.ui.Ui;

/**
 * Main class that starts the project
 */
public class Nimbus {
    private Storage storage;
    private Ui ui;
    private TaskList taskList = new TaskList();
    private Parser parser;

    /**
     * Creates a nimbus object and runs the chatbot
     *
     * @param filepath where the file is supposed to be at
     */
    public Nimbus(String filepath) {
        this.storage = new Storage(filepath);
        storage.createFile();
        storage.loadFile(taskList);
        this.ui = new Ui(taskList);
        this.parser = new Parser(taskList);
    }

    /**
     * Starts the program by loading the UI
     *
     * @throws WrongDateTimeFormatException if wrong format for date and time
     * @throws IOException if file is not found
     */
    private void run() throws WrongDateTimeFormatException, IOException {
        Ui.showWelcome();
        String userInput = "";

        Scanner scanner = new Scanner(System.in);
        while (!userInput.equals("bye")) {
            userInput = scanner.nextLine();
            this.parser.handleInput(userInput);
        }
        scanner.close();
    }

    /**
     * Starts the project
     *
     * @param args arguments that is not necessary for this project
     * @throws IOException if file is not found
     * @throws WrongDateTimeFormatException if wrong format for date and time
     */
    public static void main(String[] args)
            throws IOException, WrongDateTimeFormatException {
        new Nimbus("nimbus.txt").run();
    }

    /**
     * Returns the Nimbus response when user has entered an input
     * @param input string input by user
     * @return string response from Nimbus
     */
    public String getResponse(String input) {
        try {
            return this.parser.handleInput(input);
        } catch (IOException e) {
            return e.getMessage();
        }
    }
}
