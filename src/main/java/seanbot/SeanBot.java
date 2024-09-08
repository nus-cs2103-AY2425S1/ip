package seanbot;

import java.util.Scanner;
import java.io.*;

/**
 * The main class for the SeanBot application.
 */
public class SeanBot {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Constructs a SeanBot instance.
     * Initializes the UI, storage, parser, and task list components.
     *
     * @param filePath The file path where the task list is stored.
     */
    public SeanBot() {
        ui = new Ui();
        storage = new Storage("data/seanbot.txt");
        parser = new Parser();
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showError("Error loading tasks from file.");
            tasks = new TaskList();
        }
    }

    /**
     * Runs the SeanBot application.
     * Continuously reads user input, processes commands, and displays the results until the user exits the application.
     */
    public void run() {
        ui.showWelcomeMessage();
        Scanner scanner = new Scanner(System.in);
    
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = scanner.nextLine();
                parser.parse(userInput, tasks, ui, storage);
                if (userInput.equals("bye")) {
                    isExit = true;
                }
            } catch (SeanBotException | IOException e) {
                ui.showError(e.getMessage());
            }
        }
    
        scanner.close();
    }

    /**
     * The main method of the SeanBot application.
     */
    public static void main(String[] args) {
        new SeanBot().run();
    }
    
    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(output);
        PrintStream oldOut = System.out;
        System.setOut(stream);

        try {
            parser.parse(input, tasks, ui, storage);
        } catch (SeanBotException | IOException e) {
            System.setOut(oldOut);
            return "Error: " + e.getMessage();
        }
        System.out.flush();
        System.setOut(oldOut);
        return output.toString();
    }
}