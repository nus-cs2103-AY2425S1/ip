package duke;

import java.util.Scanner;

/**
 * The Duke class represents the main entry point for the Duke application.
 * It initializes the necessary components and handles the main interaction loop.
 */
public class Duke {

    private Ui ui;
    private Storage storage;
    private Parser parser;
    private TaskList taskList;

    /**
     * Constructs a new Duke instance.
     * Initializes the user interface, storage, parser, and task list.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage("data/", "duke.txt");
        this.parser = new Parser();
        this.taskList = new TaskList();
        this.storage.loadFile(this.taskList, this.parser);
    }

    /**
     * Starts the Duke application. This method handles user input in a loop until
     * the "bye" command is given, which ends the session.
     */
    public void run() {
        this.ui.greet();
        Scanner sc = new Scanner(System.in);
        String command;
        while (sc.hasNext()) {
            command = sc.nextLine();
            if ("bye".equals(command.split(" ", 2)[0])) {
                this.ui.bye();
                break;
            }
            this.parser.parseCommand(command, this.taskList, this.storage, this.ui);
        }
        sc.close();
    }

    /**
     * The main method, which serves as the entry point for the Duke application.
     * It initializes a new Duke instance and starts the application.
     *
     * @param args the command-line arguments (not used)
     * @throws DukeException if an error occurs during the execution of Duke
     */
    public static void main(String[] args) throws DukeException {
        Duke duke = new Duke();
        duke.run();
    }
}