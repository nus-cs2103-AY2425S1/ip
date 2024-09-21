package bob;

import bob.command.Command;
import bob.exception.BobException;

import java.io.IOException;

/**
 * The main class of BobBot.
 * BobBot is a chatbot that helps users manage tasks.
 */
public class Bob {
    private final Storage storage;
    private final Ui ui;
    private final Parser parser;
    private TaskList tasks;

    /**
     * Constructs a Bob instance that stores data at the given filePath.
     *
     * @param filePath where this instance of BobBot stores its data
     */
    public Bob(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            tasks = new TaskList(storage.load());
        } catch (BobException | IOException e) {
            ui.printError(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.printGreeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readInput();
                Command c = parser.parse(input);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (BobException e) {
                ui.printError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Bob("data/Bob.txt").run();
    }
}
