package command;

import chatterboxexceptions.ChatterboxExceptions;
import gui.GuiResponses;
import parser.Parser;
import tags.TagList;
import tasks.Task;
import tasks.TaskList;

/**
 * Represents the command to delete a tag.
 */
public class RemoveTagCommand extends Command {
    public RemoveTagCommand() {

    }

    @Override
    public String execute(String input, GuiResponses guiResponses,
                          TagList tagList,
                          TaskList taskList, Parser parser) throws ChatterboxExceptions.ChatterBoxNoInput, ChatterboxExceptions.ChatterBoxMissingParameter, ChatterboxExceptions.ChatterBoxInvalidInput {
        // input will be in format "removeTag /i <index> /t <tag>"
        int index = parser.parseRemoveTagIndex(input) - 1;
        if (index < 0 || index >= taskList.size()) {
            return guiResponses.getInvalidIndexMessage();
        }
        String tagName = parser.parseRemoveTagName(input).trim().toLowerCase();
        //remove tag from both taglist and task
        Task taggedTask = taskList.getTask(index);
        if (!tagList.containsTag(tagName)) {
            return guiResponses.tagNotFoundMsg(tagName);
        }
        tagList.getTag(tagName).untagTask(taggedTask);
        if (tagList.getTag(tagName).getTaggedTasks().isEmpty()) {
            tagList.removeTag(tagName);
        }
        taggedTask.removeTag(tagList.getTag(tagName));
        return guiResponses.untagTagMsg(taggedTask, tagName);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof RemoveTagCommand;
    }
}
