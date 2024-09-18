package command;

import chatterboxexceptions.ChatterboxExceptions;
import gui.GuiResponses;
import parser.Parser;
import tags.TagList;
import tasks.TaskList;


/**
 * Represents a bye command that can be executed by the user
 */
public class ByeCommand extends Command {

    public ByeCommand() {
    }

    /**
     * Executes the command to exit the program.
     *
     * @param input        The input string.
     * @param guiResponses The GUI responses.
     * @param tagList      The tag list.
     * @param taskList     The task list.
     * @param parser       The parser.
     * @return The response to the user.
     * @throws ChatterboxExceptions.ChatterBoxNoInput If there is no input.
     * @throws ChatterboxExceptions.ChatterBoxMissingParameter If there is a missing parameter.
     */
    @Override
    public String execute(String input, GuiResponses guiResponses,
                          TagList tagList, TaskList taskList, Parser parser)
            throws ChatterboxExceptions.ChatterBoxNoInput, ChatterboxExceptions.ChatterBoxMissingParameter, ChatterboxExceptions.ChatterBoxInvalidInput {
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ByeCommand;
    }
}
