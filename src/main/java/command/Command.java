package command;

import parser.Parser;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * Represents the different commands in which the user can input.
 * Classes that inherit from this class should override the
 * execute method.
 */
abstract public class Command {
    /**
     * Returns the string of the reply according to the command given.
     * Each subtype of command handles the different possible errors.
     *
     * @param parts Command inputted by the user.
     * @param list List of the tasks.
     * @param ui Ui for common chatbot replies.
     * @param storage Storage to save or read the tasks into the list.
     * @param parser Parser to parse the command inputted by the user.
     * @return String of the reply according to the command given.
     *
     */
    public abstract String execute(String[] parts, TaskList list, Ui ui, Storage storage, Parser parser);

}
