package command;

import chatterboxexceptions.ChatterboxExceptions;
import gui.GuiResponses;
import parser.Parser;
import tags.TagList;
import tasks.TaskList;

/**
 * Represents a todo command that can be executed by the user.
 */
public class TodoCommand extends Command {
    public TodoCommand() {
    }


    @Override
    public String execute(String input, GuiResponses guiResponses,
                          TagList tagList,
                          TaskList taskList, Parser parser)
            throws ChatterboxExceptions.ChatterBoxNoInput,
            ChatterboxExceptions.ChatterBoxMissingParameter, ChatterboxExceptions.ChatterBoxInvalidInput {
        taskList.addTodo(parser.parseTodo(input));
        return guiResponses.addTaskMsg("Todo", taskList.size());
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof TodoCommand;
    }
}
