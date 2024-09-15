package calebyyy.commands;

import calebyyy.Calebyyy;
import calebyyy.TaskList;
import calebyyy.Ui;

/**
 * Represents a command to find tasks with a keyword.
 */
public class FindCommand extends Command {

    /**
     * Constructor for FindCommand.
     *
     * @param calebyyy The main Calebyyy object.
     * @param ui The Ui object responsible for user interaction.
     * @param taskList The TaskList object responsible for storing tasks.
     */
    public FindCommand(Calebyyy calebyyy, Ui ui, TaskList taskList) {
        super(calebyyy, ui, taskList);
    }

    /**
     * Finds tasks with a keyword.
     *
     * @param input The user input.
     */
    @Override
    public void execute(String input) {
        String keyword = input.substring(input.indexOf(' ') + 1);
        taskList.findKeyword(keyword);
    }
}
