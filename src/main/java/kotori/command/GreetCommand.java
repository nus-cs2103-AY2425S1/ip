package kotori.command;

import kotori.ui.Ui;

/**
 * This represents a command of greeting the user.
 **/

public class GreetCommand extends Command {
    /**
     * Activate the command and greet the user.
     * */
    @Override
    public String execute() {
        return Ui.printGreeting();
    }
}
