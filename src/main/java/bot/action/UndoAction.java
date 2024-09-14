package bot.action;

import java.util.EmptyStackException;

import bot.exceptions.BotException;
import bot.exceptions.InvalidTaskIdException;
import bot.tasks.TaskList;

/**
 * Action that undo the last change to <code>TaskList</code>.
 *
 * @author mongj
 */
public class UndoAction extends Action {
    /**
     * Undo the last change to the <code>TaskList</code>.
     *
     * @param taskList to use.
     * @return a message corresponding to the action executed.
     */
    @Override
    public String execute(TaskList taskList) throws InvalidTaskIdException {
        try {
            Action action = Undo.getLast();
            return action.execute(taskList);
        } catch (EmptyStackException e) {
            return "There is no more action to undo";
        } catch (BotException e) {
            return e.getMessage();
        }
    }
}
