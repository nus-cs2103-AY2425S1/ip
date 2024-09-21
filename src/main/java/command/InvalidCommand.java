package command;

import chatterboxexceptions.ChatterboxExceptions;
import gui.GuiResponses;
import parser.Parser;
import tags.TagList;
import tasks.TaskList;

/**
 * Represents an invalid command that can be executed by the user.
 */
public class InvalidCommand extends Command {
    public InvalidCommand() {
    }

    @Override
    public String execute(String input, GuiResponses guiResponses,
                          TagList tagList,
                          TaskList taskList, Parser parser) throws ChatterboxExceptions.ChatterBoxNoInput,
            ChatterboxExceptions.ChatterBoxMissingParameter, ChatterboxExceptions.ChatterBoxInvalidInput {
        return guiResponses.getInvalidCommandMessage();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof InvalidCommand;
    }
}
