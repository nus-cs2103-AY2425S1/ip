package command;

import chatterboxexceptions.ChatterboxExceptions;
import gui.GuiResponses;
import parser.Parser;
import tags.TagList;
import tasks.TaskList;

/**
 * Represents a delete command that can be executed by the user
 */
public class DeleteCommand extends Command {
    public DeleteCommand() {
    }

    /**
     * Deletes a task from the task list.
     *
     * @param input        The input string.
     * @param guiResponses The GUI responses.
     * @param tagList      The tag list.
     * @param taskList     The task list.
     * @param parser       The parser.
     * @return The response message.
     * @throws ChatterboxExceptions.ChatterBoxNoInput If the input is empty.
     * @throws ChatterboxExceptions.ChatterBoxMissingParameter If the input is missing a parameter.
     */
    @Override
    public String execute(String input, GuiResponses guiResponses,
                           TagList tagList,
                           TaskList taskList, Parser parser) throws ChatterboxExceptions.ChatterBoxNoInput,
            ChatterboxExceptions.ChatterBoxMissingParameter {
        int index = parser.extractNum(input) - 1;
        return guiResponses.delTaskMsg(taskList.deleteTask(index), taskList.size());
    }
}
