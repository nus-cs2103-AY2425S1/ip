package command;

import java.util.ArrayList;

import chatterboxexceptions.ChatterboxExceptions;
import gui.GuiResponses;
import parser.Parser;
import tags.TagList;
import tasks.Task;
import tasks.TaskList;


/**
 * Represents a find command that can be executed by the user
 */
public class FindCommand extends Command {
    public FindCommand() {
    }

    @Override
    public String execute(String input, GuiResponses guiResponses,
                           TagList tagList,
                           TaskList taskList, Parser parser) throws ChatterboxExceptions.ChatterBoxNoInput,
            ChatterboxExceptions.ChatterBoxMissingParameter, ChatterboxExceptions.ChatterBoxInvalidInput {
        String keywords = parser.parseFind(input).trim();

        ArrayList<Task> matches = taskList.findTasks(keywords);
        return guiResponses.getSearchList(matches);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof FindCommand;
    }
}
