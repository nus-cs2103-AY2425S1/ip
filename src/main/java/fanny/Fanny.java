package fanny;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.stream.Collectors;

import fanny.command.Command;
import fanny.parser.Parser;
import fanny.storage.Storage;
import fanny.task.TaskList;
import fanny.ui.Ui;

/**
 * The main class for the Fanny task management chatbot.
 * Fanny is a text-based task manager chatbot that allows users
 * to manage tasks, including to-dos, deadlines, and events.
 */
public class Fanny {

    /** List of tasks */
    private TaskList tasks;

    /** Handles user interface interaction */
    private Ui ui;

    /** Handles the storing and loading of tasks */
    private Storage storage;

    private static final String FILEPATH = "data/fanny.txt";

    public Fanny() {
        this(FILEPATH);
    }
    /**
     * Constructs a new Fanny object with the specified file path for storage.
     *
     * @param filePath The path of the file where tasks are stored.
     */
    public Fanny(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage);
    }

    /**
     * Starts the Fanny chatbot and runs the main event loop.
     */
    public void run() {
        ui.printHello();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.getUserInput();
                ui.showHorizontalLine();
                Command c = Parser.parse(input);
                c.executeCmd(tasks, ui);
                isExit = c.shouldExit();
            } catch (FannyException e) {
                ui.showMessage(e.getMessage());
            } finally {
                ui.showHorizontalLine();
            }
        }
    }

    /**
     * The main method to launch Fanny.
     *
     * @param args Arguments.
     */
    public static void main(String[] args) {
        new Fanny("./data/fanny.txt").run();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String generateResponse(String input) {
        String capturedOutput = captureCliMsg(() -> {
            try {
                Command c = Parser.parse(input);
                c.executeCmd(tasks, ui);
            } catch (FannyException e) {
                ui.showMessage(e.getMessage());
            }
        });

        return filterLines(capturedOutput);
    }

    /**
     * Captures the output of a UI action (that prints to System.out).
     *
     * @param msg The message to be printed on cli.
     * @return The captured output as a string.
     */
    public String captureCliMsg(Runnable msg) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;

        try {
            System.setOut(ps);
            msg.run();
        } finally {
            System.out.flush();
            System.setOut(old);
        }

        return baos.toString();
    }

    /**
     * Filters out horizontal lines and other unwanted characters from the message.
     *
     * @param message The message to filter.
     * @return The filtered message.
     */
    public String filterLines(String message) {
        return Arrays.stream(message.split(System.lineSeparator()))
                .filter(line -> !line.matches("[-_]+"))
                .collect(Collectors.joining(System.lineSeparator()));
    }

}
