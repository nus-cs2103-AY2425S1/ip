package bot.action;

import bot.tasks.TaskList;

/**
 * Represents an action that exits the current program.
 *
 * @author mongj
 */
public class ExitAction extends Action {
    public ExitAction() {
        super.setIsTerminal(true);
    }
    /**
     * Exits the current program.
     *
     * @param taskList not used. This is kept for consistency with other actions
     * @return A goodbye message
     */
    @Override
    public String execute(TaskList taskList) {
        return "Bye!\n\nThis window will close in a few seconds.";
    }
}
