package llama;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import llama.commands.Command;
import llama.data.Storage;
import llama.data.TaskList;
import llama.exceptions.LlamaException;
import llama.parser.Parser;
import llama.ui.Ui;

/**
 * Main class for program. Responsible for starting and ending the program
 */
public class Llama {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private Storage storage;
    private Ui ui;
    private TaskList taskList;

    /**
     * Constructor for Llama
     */
    public Llama() {
        this.ui = new Ui();
        this.storage = new Storage();
        try {
            this.taskList = storage.load();
        } catch (IOException e) {
            ui.displayString(e.getMessage()); // TODO: Make own exception for custom message
        }
    }

    /**
     * Method to start running the program
     */
    public void run() {
        Scanner sc = new Scanner(System.in);

        // Initializing message
        this.ui.displayWelcome();

        // Get user input
        boolean shouldContinue = true;
        while (shouldContinue) {
            String input = this.ui.getUserInput(sc);


            try {
                Command command = Parser.parse(input);
                // Command returns false if program should stop running
                shouldContinue = command.execute(this.taskList, this.ui, this.storage);
            } catch (IOException | LlamaException e) {
                ui.displayString(e.getMessage());
            }
        }

        sc.close();
    }

    public static void main(String[] args) {
        new Llama().run();
    }
}
