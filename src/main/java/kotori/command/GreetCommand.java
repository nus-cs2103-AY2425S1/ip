package kotori.command;

import kotori.Ui.Ui;

/**
 * This represents a command of greeting the user.
 **/

public class GreetCommand extends Command{

    /**
     * Activate the command and greet the user.
     * */
    @Override
    public void execute() {
        Ui.printGreeting();
    }
}
