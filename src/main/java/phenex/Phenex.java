package phenex;

import phenex.command.Command;
import phenex.command.TerminatingCommand;
import phenex.exception.PhenexException;
import phenex.storage.Storage;
import phenex.task.TaskList;
import phenex.ui.Ui;
import phenex.util.Parser;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Scanner;

/**
 * Phenex class encapsulating the Phenex chatbot.
 */
public class Phenex {
    /** Encapsulates the Ui of Phenex. */
    private Ui ui;
    /** Encapsulates the task list of Phenex. */
    private TaskList tasks;
    /** Encapsulates the storage of Phenex. */
    private Storage storage;
    /** Encapsulates the parser of Phenex. */
    private Parser parser;

    private final Path DEFAULT_FILEPATH = Paths.get(System.getProperty("user.home"),
                                                                "Downloads",
                                                                       "CS2103T_AY2425",
                                                                       "iP",
                                                                       "data",
                                                                       "phenex.txt");

    public Phenex(Path filePath) {
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(this.storage);
        this.ui = new Ui();
        this.parser = new Parser();

    }

    public Phenex() {
        this.storage = new Storage(DEFAULT_FILEPATH);
        this.tasks = new TaskList(this.storage);
        this.ui = new Ui();
        this.parser = new Parser();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }

    public static void main(String[] args) {
        String home = System.getProperty("user.home");
        Path filePath = Paths.get(home, "Downloads", "CS2103T_AY2425", "iP", "data", "phenex.txt");
        Phenex phenex = new Phenex(filePath);

        phenex.ui.greet();

        Scanner scanner = new Scanner(System.in);
        String userInput;
        Command command = null;

        while (scanner.hasNext()) {
            // scan inputs
            userInput = scanner.nextLine();

            phenex.ui.printLine();

            try {
                command = phenex.parser.parseCommandFromLine(userInput);
                command.execute(phenex.tasks, phenex.ui, phenex.storage);
            } catch (PhenexException e) {
                Ui.printExceptionMessage(e);
            }

            phenex.ui.printLine();
            
            if (command instanceof TerminatingCommand) {
                break;
            }
        }

        scanner.close();
    }
}
