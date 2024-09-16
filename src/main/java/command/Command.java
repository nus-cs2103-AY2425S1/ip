package command;

import chatterboxexceptions.ChatterboxExceptions;
import gui.GuiResponses;
import parser.Parser;
import tags.TagList;
import tasks.TaskList;

public abstract class Command {

    public Command() {
    }

    /**
     * Executes the command
     *
     * @param input        the input from the user
     * @param guiResponses the gui responses object used to output the response
     * @param tagList
     * @param taskList
     * @param parser
     * @return the gui response after executing the command
     */
    public abstract String execute(String input, GuiResponses guiResponses,
                                   TagList tagList, TaskList taskList, Parser parser) throws ChatterboxExceptions.ChatterBoxNoInput, ChatterboxExceptions.ChatterBoxMissingParameter;
}
