package seedu.maxine;

import java.util.Scanner;

import seedu.maxine.command.Command;
import seedu.maxine.parser.Parser;
/**
 * The Maxine class represents a command-line task management application.
 * It handles user interaction, parses commands, and manages tasks using
 * various components such as the UI, storage, and task lists.
 */
public class Maxine {
    private static Scanner scanner = new Scanner(System.in);
    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private Parser parser;
    private Command command;

    /**
     * Constructs new instance of Maxine class
     */
    public Maxine() {
        ui = new Ui();
        tasks = new TaskList();
        parser = new Parser();
        storage = new Storage("data/maxine.txt");
        command = new Command(storage, ui, tasks);
    }

    public String getResponse(String input) {
        return parser.parse(input);
    }

    /**
     * Asks user for inputs, parses the inputs and sends response accordingly
     */
    public void run() {
        try {
            ui.greet();
            storage.load();
            while (command.getStatus()) {
                String answer = ask();
                parser.parse(answer);
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Returns the user's input after converting the response to lower case
     * @return The user's answer
     */
    public String ask() {
        System.out.print("What can I do for you today? : ");
        String answer = scanner.nextLine().toLowerCase();
        return answer;
    }

    /**
     * This is the main method
     * @param args Command-line arguments passed to the program
     */
    public static void main(String[] args) {
        new Maxine().run();
    }
}
