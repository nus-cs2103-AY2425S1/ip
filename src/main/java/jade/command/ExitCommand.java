package jade.command;

import static jade.ui.Ui.INDENT;

public class ExitCommand extends Command {

    @Override
    public String run() {
        String message = INDENT + "Bye. Hope to see you again soon!";
        return displayMessage(message);
    }
}
