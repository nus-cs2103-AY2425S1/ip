package bigdog;

import java.time.format.DateTimeParseException;

/**
 * The {@code Bigdog} class represents the main application that manages tasks.
 * It handles user input, processes commands, interacts with storage, and manages the task list.
 */
public class Bigdog {

    /** Storage for tasks into external file */
    private Storage storage;
    /** TaskList to manage tasks */
    private TaskList tasks;
    /** Ui to handle interaction with users */
    private Ui ui;

    /**
     * Constructs a Bigdog object.
     * Initializes the storage, task list, and UI components using the specified file path.
     *
     * @param file the file path where tasks are stored and loaded from.
     */
    public Bigdog(String file) {
        this.storage = new Storage(file);
        this.tasks = new TaskList(this.storage.load());
        this.ui = new Ui();
    }

    /**
     * Runs the main event loop of the Bigdog application.
     * This method continuously reads user input, parses it, and executes the corresponding commands.
     * It handles exceptions that may arise during the execution of commands and ensures that tasks
     * are saved after each iteration of the loop.
     */
    public void run() {

        ui.greet();
        boolean toContinue = true;


        while (toContinue) {
            try {
                String userInput = ui.readInput();
                String[] commands = Parser.parse(userInput);
                switch (commands[0]) {
                case "bye":
                    toContinue = false;
                    ui.bye();
                    break;
                case "list":
                    this.tasks.show();
                    break;
                case "mark":
                    ui.print(this.tasks.mark(Integer.parseInt(commands[1])));
                    break;
                case "unmark":
                    ui.print(this.tasks.unmark(Integer.parseInt(commands[1])));
                    break;
                case "delete":
                    ui.print(this.tasks.delete(Integer.parseInt(commands[1])));
                    break;
                case "todo":
                    ui.print(this.tasks.add(Todo.of(commands[1])));
                    break;
                case "deadline":
                    ui.print(this.tasks.add(Deadline.of(commands[1])));
                    break;
                case "event":
                    ui.print(this.tasks.add(Event.of(commands[1])));
                    break;
                case "find":
                    ui.print(this.tasks.find(commands[1]));
                    break;
                default:
                    ui.print("Unknown command. Please try again.");
                }
            } catch (BigdogException
                     | DateTimeParseException
                     | NumberFormatException
                     | IndexOutOfBoundsException e) {
                ui.print(e.getMessage());
            } finally {
                storage.save(this.tasks.get());
            }
        }
    }

    /**
     * The main method that serves as the entry point for the Bigdog application.
     * It creates an instance of the Bigdog class and calls the run method.
     *
     * @param args command-line arguments (not used).
     */
    public static void main(String[] args) {

        new Bigdog("./src/main/Bigdog.txt").run();

    }

}
