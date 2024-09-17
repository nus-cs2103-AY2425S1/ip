package snah.commands;

import snah.Snah;
import snah.TaskList;
import snah.util.Storage;

/**
 * Command to exit the chatbot Returns the exit string
 */
public class Bye extends Command {

    public Bye(String input) {
        super("");
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        return Snah.EXIT_STRING;
    }

}
