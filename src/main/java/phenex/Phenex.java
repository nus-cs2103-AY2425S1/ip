package phenex;

import phenex.command.Command;
import phenex.command.TerminatingCommand;
import phenex.task.TaskList;
import phenex.ui.Ui;
import phenex.storage.Storage;
import phenex.util.Parser;
import phenex.exception.PhenexException;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;


public class Phenex {
    /** Encapsulates the Ui of Phenex */
    private Ui ui;
    /** Encapsulates the task list of Phenex */
    private TaskList tasks;
    /** Encapsulates the storage of Phenex */
    private Storage storage;
    /** Encapsulates the parser of Phenex */
    private Parser parser;

    public enum CommandType {
        COMMAND_MARK, COMMAND_UNMARK, COMMAND_DELETE
    }

    public Phenex(Path filePath) {
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(this.storage);
        this.ui = new Ui();
        this.parser = new Parser();

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
