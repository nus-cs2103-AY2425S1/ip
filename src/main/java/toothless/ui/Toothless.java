package toothless.ui;

import toothless.command.Command;
import toothless.command.Parser;
import toothless.exceptions.ToothlessExceptions;
import toothless.storage.Storage;
import toothless.task.TaskList;

/**
 * Toothless represents a simple chat application.
 */
public class Toothless {

    private static final String DIVIDER = "*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n";
    private final Storage storage;
    private final TaskList taskList;
    private final Ui ui;

    /**
     * Constructor for Toothless.
     * Initializes the task list.
     */
    private Toothless() {
        this.storage = new Storage();
        this.taskList = new TaskList(storage.loadTasks());
        this.ui = new Ui();
    }

    /**
     * Begins the chat application.
     * The user can add tasks to the task list.
     */
    public void beginChat() throws ToothlessExceptions {

        ui.welcome();

        while (true) {
            System.out.println("You :");
            String userInput = ui.readCommand();
            Command command = Parser.getCommandType(userInput);
            System.out.println("\n" + DIVIDER);

            if (userInput.equals("bye")) {
                ui.bye();
                break;
            }

            if (userInput.equals("hi")) {
                ui.greeting();
                continue;
            }

            try {
                command.executeCommand(taskList, ui, storage);
            } catch (ToothlessExceptions e) {
                System.out.println(e.getMessage());
            }
        }

    }

    /**
     * Main method for Toothless.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Toothless toothless = new Toothless();
        try {
            toothless.beginChat();
        } catch (ToothlessExceptions e) {
            System.out.println(e.getMessage());
        }
    }
}
