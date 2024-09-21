package command;

import chatterboxexceptions.ChatterboxExceptions;
import gui.GuiResponses;
import parser.Parser;
import tags.TagList;
import tasks.TaskList;

/**
 * Represents a mark command that can be executed by the user
 */
public class MarkCommand extends Command {
    public MarkCommand() {
    }

    /**
     * Executes the mark command.
     *
     * @param input the input from the user.
     * @param guiResponses the gui responses object used to output the response.
     * @param tagList TagList object.
     * @param taskList TaskList object.
     * @param parser Parser object.
     * @return the gui response after executing the command.
     */
    @Override
    public String execute(String input, GuiResponses guiResponses,
                          TagList tagList, TaskList taskList, Parser parser)
            throws ChatterboxExceptions.ChatterBoxNoInput,
            ChatterboxExceptions.ChatterBoxMissingParameter, ChatterboxExceptions.ChatterBoxInvalidInput {
        int index = parser.extractNum(input) - 1;
        if (index < 0 || index >= taskList.size()) {
            return guiResponses.getInvalidIndexMessage();
        }
        return guiResponses.markMsg(taskList.markTask(index));
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof MarkCommand;
    }
}
