package command;

import chatterboxexceptions.ChatterboxExceptions;
import gui.GuiResponses;
import parser.Parser;
import tags.TagList;
import tasks.TaskList;

public class ByeCommand extends Command {

    public ByeCommand() {
    }

    @Override
    public String execute(String input, GuiResponses guiResponses,
                          TagList tagList, TaskList taskList, Parser parser) throws ChatterboxExceptions.ChatterBoxNoInput, ChatterboxExceptions.ChatterBoxMissingParameter {
        return guiResponses.goodBye();
    }
}
