package quack.command;

import quack.util.Ui;

/**
 * This class is responsible for handling the help command in Quack.
 */
public class HelpCommand extends Command {

    /** Ui to handle all user display interactions */
    private Ui ui;

    /**
     * Creates a HelpCommand object.
     * @param ui The ui object that handles user interface requests.
     */
    public HelpCommand(Ui ui) {
        this.ui = ui;
    }

    @Override
    public void prompt() {
        this.execute("");
    }

    @Override
    public void execute(String input) {

        ui.printHelpMessage();
        this.completeCommand();
    };
}
