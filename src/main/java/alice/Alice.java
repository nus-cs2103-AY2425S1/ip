package alice;

import alice.command.*;
import alice.storage.*;
import alice.ui.*;
import java.util.*;

public class Alice {
    private static final String NAME = "Alice";
    private static final String DATA_DIRECTORY_PATH = "./data";
    private static final String TASKS_FILE_NAME = "tasks.jsonl";

    private final Ui ui;
    private final Storage storage;
    private final TaskList taskManager;

    public Alice() {
        this.ui = new Ui();
        this.storage = new Storage(DATA_DIRECTORY_PATH, TASKS_FILE_NAME);
        this.taskManager = new TaskList(this.storage);
    }

    private void greet() {
        ui.say(String.format("Hello! I'm %s. What can I do for you?", NAME));
    }

    private void bye() {
        ui.say("Bye. Hope to see you again soon!");
    }

    private void echo(String line) {
        // echo user inputs
        ui.say(String.format("%s", line));
    }

    private void listen() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String input = line.trim().toUpperCase();
            if (input.startsWith("BYE")) {
                break;
            }

            try {
                Command command = Command.fromInput(line, ui, taskManager);
                command.execute(line);
            } catch (IllegalArgumentException e) {
                // default behavior
                echo(line);
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        Alice alice = new Alice();

        alice.greet();
        alice.listen();
        alice.bye();
    }
}
