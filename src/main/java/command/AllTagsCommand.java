package command;

import chatterboxexceptions.ChatterboxExceptions;
import gui.GuiResponses;
import parser.Parser;
import tags.TagList;
import tasks.TaskList;

/**
 * Represents a command to display all tags.
 */
public class AllTagsCommand extends Command {
    public AllTagsCommand() {
    }

    /**
     * Executes the command to display all tags.
     *
     * @param input        The input string.
     * @param guiResponses The GUI response object.
     * @param tagList      The tag list object.
     * @param taskList     The task list object.
     * @param parser       The parser object.
     * @return The response string.
     * @throws ChatterboxExceptions.ChatterBoxNoInput If there is no input.
     * @throws ChatterboxExceptions.ChatterBoxMissingParameter If there is a missing parameter.
     */
    @Override
    public String execute(String input, GuiResponses guiResponses,
                           TagList tagList,
                           TaskList taskList, Parser parser) throws ChatterboxExceptions.ChatterBoxNoInput, ChatterboxExceptions.ChatterBoxMissingParameter, ChatterboxExceptions.ChatterBoxInvalidInput {
        return guiResponses.displayAllTags(tagList.getAllTags());
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof AllTagsCommand;
    }
}
