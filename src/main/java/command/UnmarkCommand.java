package command;

import chatterboxexceptions.ChatterboxExceptions;
import gui.GuiResponses;
import parser.Parser;
import tags.TagList;
import tasks.TaskList;

public class UnmarkCommand extends Command {
    public UnmarkCommand() {
    }

    /**
     * Executes the command
     *
     * @param input        the input from the user
     * @param guiResponses the gui responses object used to output the response
     * @param tagList      tagList object
     * @param taskList
     * @param parser
     * @return the gui response after executing the command
     */
    @Override
    public String execute(String input, GuiResponses guiResponses,
                          TagList tagList, TaskList taskList, Parser parser) throws ChatterboxExceptions.ChatterBoxNoInput, ChatterboxExceptions.ChatterBoxMissingParameter {
        int index = parser.extractNum(input) -1;
        return guiResponses.unmarkMsg(taskList.unmarkTask(index));
    }
}
