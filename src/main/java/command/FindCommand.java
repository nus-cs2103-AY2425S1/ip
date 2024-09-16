package command;

import chatterboxexceptions.ChatterboxExceptions;
import gui.GuiResponses;
import parser.Parser;
import tags.TagList;
import tasks.Task;
import tasks.TaskList;

import java.util.ArrayList;

public class FindCommand extends Command {
    public FindCommand() {
    }

    @Override
    public  String execute(String input, GuiResponses guiResponses,
                           TagList tagList,
                           TaskList taskList, Parser parser) throws ChatterboxExceptions.ChatterBoxNoInput,
            ChatterboxExceptions.ChatterBoxMissingParameter {
        String keywords = parser.parseFind(input).trim();

        ArrayList<Task> matches = taskList.findTasks(keywords);
        return guiResponses.getSearchList(matches);
    }
}
