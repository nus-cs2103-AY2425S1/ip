package bob;

import bob.command.Command;
import bob.exception.*;

import java.io.IOException;

public class Bob {
    private static final Storage STORAGE = new Storage("data/Bob.txt");
    private static final Ui UI = new Ui();
    private static final Parser PARSER = new Parser();
    private static TaskList tasks;

    public static void main(String[] args) {
        UI.printGreeting();

        try {
            tasks = STORAGE.load();
        } catch (IOException | BobException e) {
            UI.printError(e.getMessage());
            tasks = new TaskList();
        }

        boolean isExit = false;
        while (!isExit) {
            String input = UI.readInput();
            try {
                Command command = PARSER.parse(input);
                command.execute(tasks, UI, STORAGE);
                isExit = command.isExit();
            } catch (BobException e) {
                UI.printError(e.getMessage());
            }
        }
    }
}
