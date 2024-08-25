package sumode;

import java.io.IOException;
import java.util.Scanner;

import sumode.exception.AlreadyMarkedException;
import sumode.exception.AlreadyUnmarkedException;
import sumode.exception.NonExistentTaskException;
import sumode.exception.UnknownCommandException;
import sumode.exception.WrongSyntaxForCommandException;
import sumode.util.Command;
import sumode.util.Parser;
import sumode.util.Storage;
import sumode.util.SumoTaskList;
import sumode.util.Ui;

/**
 * Main class where user will run the Main from
 */
public class SumoDE {

    private Storage storage = null;
    private SumoTaskList tasks;
    private final Ui ui;

    /**
     * Constructor for SumoDE
     * @param filePath FilePath to save data.
     */
    public SumoDE(String filePath) {
        //handle Ui
        this.ui = new Ui();

        // handle Storage
        try {
            this.storage = new Storage(filePath, this.ui);
        } catch (IOException e) {
            // Note: this will only happen when file don't exist and we cannot create new file in the path.
            // New File will be created when file doesn't exist in first place.
            ui.unknownSaveError();
        }

        //handle SumoTaskList
        if (this.storage == null) {
            this.tasks = new SumoTaskList(this.ui); // we will use the version where we cannot save
        } else {
            try {
                this.tasks = new SumoTaskList(this.storage, this.ui);
            } catch (IOException e) {
                //unlikely will happen since we already successfully initialise storage
                ui.unknownSaveError();
                this.tasks = new SumoTaskList(this.ui); // we will use the version where we cannot save
            }
        }
    }

    /**
     * Runs a task-management chatbot SumoDE
     */
    public void run() {

        this.ui.greet();

        Scanner sc = new Scanner(System.in);
        boolean terminate = false;

        while (!terminate) {
            String input = sc.nextLine();

            // Splitting command and action
            String[] splitString = Parser.splitCommandAndAction(input);
            String commandString = splitString[0];
            String item = splitString[1];
            Command command;

            // find correct matching command
            try {
                command = Command.valueOf(commandString.toUpperCase());
                terminate = this.tasks.execute(command, item);
            } catch (IllegalArgumentException e) {
                ui.unknownCommand(commandString);
            } catch (WrongSyntaxForCommandException | UnknownCommandException
                     | NonExistentTaskException | AlreadyUnmarkedException
                     | AlreadyMarkedException e) {
                ui.handleError(e);
            } finally {
                if (!terminate) {
                    ui.next();
                }
            }
        }

        // loop ended, cleaning up
        ui.bye();
        sc.close();
    }

    public static void main(String[] args) {
        SumoDE sumoDE = new SumoDE("data\\taskSaved.txt");
        sumoDE.run();
    }
}
