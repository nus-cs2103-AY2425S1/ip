package command;

import chatterboxexceptions.ChatterboxExceptions;
import gui.GuiResponses;
import parser.Parser;
import tags.TagList;
import tasks.TaskList;

public class DeleteCommand extends Command{
    public DeleteCommand() {
    }

    @Override
    public  String execute(String input, GuiResponses guiResponses,
                           TagList tagList,
                           TaskList taskList, Parser parser) throws ChatterboxExceptions.ChatterBoxNoInput,
            ChatterboxExceptions.ChatterBoxMissingParameter {
        int index = parser.extractNum(input) -1;
        return guiResponses.delTaskMsg(taskList.deleteTask(index), taskList.size());
    }
}
