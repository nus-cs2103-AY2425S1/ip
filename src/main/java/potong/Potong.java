package potong;

import potong.command.Command;
import potong.command.ExitCommand;
import potong.exceptions.IllegalInputPotongException;
import potong.exceptions.PotongException;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Class to encapsulate a chatbot to store tasks.
 */
public class Potong {

    private static final String LINE = "_________________________";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initialise the chatbot.
     * @throws FileNotFoundException If the saved file is not present.
     * @throws IllegalInputPotongException If the task input is wrong.
     */
    public Potong() throws FileNotFoundException, IllegalInputPotongException {
        ui = new Ui();
        tasks = new TaskList();
        storage = new Storage(tasks);
    }

    /**
     * Start up the chatbot for user.
     */
    public void run() {
        ui.showIntroduction();
        while (true) {
            String userInput = ui.getUserCommand();
            Command c = Parser.parse(userInput);
            assert c != null;
            try {
                String message = c.execute(tasks, storage, ui);
                ui.print(message);
            } catch (PotongException | IOException e) {
                throw new RuntimeException(e);
            }
            if (c instanceof ExitCommand) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        try {
            new Potong().run();
        } catch (FileNotFoundException | IllegalInputPotongException e) {
            throw new RuntimeException(e);
        }
    }
}
