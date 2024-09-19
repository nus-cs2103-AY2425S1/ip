package xbot.command;

import xbot.TaskList;
import xbot.exception.XBotException;
import xbot.storage.Storage;
import xbot.ui.Ui;

/**
 * Handles the "mark" command.
 */
public class MarkCommand implements Command {
    @Override
    public String execute(TaskList list, Ui ui, Storage storage, String rest) throws XBotException {
        String output = markDone(list, rest);
        storage.saveTask(list);
        return output;
    }

    /**
     * Marks the task at the specified position in the list as complete / done.
     * The task number should be a 1-based index as given by the user.
     *
     * @param rest The 1-based index of the task to be mark as done.
     * @throws XBotException If the task number is invalid.
     */
    public String markDone(TaskList list, String rest) throws XBotException {
        try {
            int taskNumber = Integer.parseInt(rest.trim());
            if (taskNumber <= 0 || taskNumber > list.size()) {
                throw new XBotException("Ah mm... Im sorry but I dont think this task number exist :0");
            }
            return list.get(taskNumber - 1).markAsDone();
        } catch (NumberFormatException e) {
            throw new XBotException("Mmm... I only understand numbers... >_< \n" +
                    "please check the task number you inputted is a number! " +
                    "(e.g. mark 1)");
        }
    }
}

