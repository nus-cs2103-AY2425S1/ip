package command;

import chatterboxexceptions.ChatterboxExceptions;
import gui.GuiResponses;
import parser.Parser;
import tags.TagList;
import tasks.TaskList;

public class TodoCommand extends Command{
    public TodoCommand() {
    }


    @Override
    public String execute(String input, GuiResponses guiResponses,
                          TagList tagList,
                          TaskList taskList, Parser parser) throws ChatterboxExceptions.ChatterBoxNoInput, ChatterboxExceptions.ChatterBoxMissingParameter {
        taskList.addTodo(parser.parseTodo(input));
        return guiResponses.addTaskMsg("Todo", taskList.size());
    }
}
