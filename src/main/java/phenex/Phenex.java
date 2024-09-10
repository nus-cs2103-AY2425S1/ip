package phenex;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import phenex.command.Command;
import phenex.exception.PhenexException;
import phenex.storage.Storage;
import phenex.task.TaskList;
import phenex.ui.Ui;
import phenex.util.Parser;


/**
 * Phenex class encapsulating the Phenex chatbot.
 */
public class Phenex {

    private static final Path DEFAULT_FILEPATH = Paths.get(System.getProperty("user.home"),
                                                    "Downloads",
                                                           "CS2103T_AY2425",
                                                           "iP",
                                                           "data",
                                                           "phenex.txt");

    /** Encapsulates the Ui of Phenex. */
    private Ui ui;
    /** Encapsulates the task list of Phenex. */
    private TaskList tasks;
    /** Encapsulates the storage of Phenex. */
    private Storage storage;
    /** Encapsulates the parser of Phenex. */
    private Parser parser;


    /**
     * Creates a Phenex object with a specified filePath.
     * @param filePath the filePath to be used.
     */
    public Phenex(Path filePath) {
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(this.storage);
        this.ui = new Ui();
        this.parser = new Parser();

    }

    /**
     * Creates a Phenex object with the default filePath.
     */
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
        try {
            Command command = this.parser.parseCommandFromLine(input);
            return command.execute(this.tasks, this.ui, this.storage);
        } catch (PhenexException e) {
            Ui.printExceptionMessage(e);
            return e.getMessage();
        }
    }

    public static void main(String... args) {
        String home = System.getProperty("user.home");
        Path filePath = Paths.get(home, "Downloads", "CS2103T_AY2425", "iP", "data", "phenex.txt");
        Phenex phenex = new Phenex(filePath);

        phenex.ui.greet();

        Scanner scanner = new Scanner(System.in);
        boolean programIsExecuting = true;
        while (scanner.hasNext() && programIsExecuting) {
            String userInput = scanner.nextLine();

            phenex.ui.printLine();

            try {
                Command command = phenex.parser.parseCommandFromLine(userInput);
                command.execute(phenex.tasks, phenex.ui, phenex.storage);
                if (command.isTerminatingCommand()) {
                    programIsExecuting = false;
                }
            } catch (PhenexException e) {
                Ui.printExceptionMessage(e);
            }

            phenex.ui.printLine();
        }

        scanner.close();
    }
}
