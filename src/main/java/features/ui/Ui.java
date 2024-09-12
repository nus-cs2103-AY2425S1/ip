package features.ui;

import config.Config;
import features.command.Command;
import utils.Utils;

import java.util.Scanner;

/**
 * Represents the user interface for interacting with the system.
 */
public class Ui {
    private Scanner sc;

    /**
     * Constructs a Ui instance with the specified Scanner for reading user input.
     *
     * @param sc the Scanner to read user input
     */
    public Ui(Scanner sc) {
        this.sc = sc;
    }

    /**
     * Reads a command from the user input.
     *
     * @return a Command object created from the user's input
     */
    public Command readCommand() {
        return new Command(sc.nextLine());
    }

    /**
     * Updates the given Command with a new name read from the user input.
     *
     * @param cmd the Command to be updated
     */
    public void updateCommand(Command cmd) {
        cmd.setName(sc.nextLine());
    }

    /**
     * Displays the introductory information to the user.
     * This includes the logo and introductory text defined in the Config class.
     */
    public void intro() {
        System.out.println(Config.logo);
        Utils.printItem(Config.intro);
    }

    /**
     * Displays the concluding information to the user.
     * This includes the outro text defined in the Config class.
     */
    public void outro() {
        Utils.printItem(Config.outro);
    }
}
