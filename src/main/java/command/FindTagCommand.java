package command;

import chatterboxexceptions.ChatterboxExceptions;
import gui.GuiResponses;
import parser.Parser;
import tags.TagList;
import tasks.TaskList;

/**
 * Represents the command to find all task under a tag.
 */
public class FindTagCommand extends Command {
    public FindTagCommand() {

    }

    @Override
    public String execute(String input, GuiResponses guiResponses,
                          TagList tagList,
                          TaskList taskList, Parser parser) throws ChatterboxExceptions.ChatterBoxNoInput,
            ChatterboxExceptions.ChatterBoxMissingParameter, ChatterboxExceptions.ChatterBoxInvalidInput {
        // input will be in format "findtag <tag>"
        String tagName = parser.findTagParseTagName(input).trim().toLowerCase();
        if (tagName.isEmpty()) {
            throw new ChatterboxExceptions.ChatterBoxMissingParameter("Tag name missing");
        }
        if (tagName.contains(" ")) {
            throw new ChatterboxExceptions.ChatterBoxInvalidInput("Tags cannot have whitespace");
        }
        if (!tagList.containsTag(tagName)) {
            return guiResponses.tagNotFoundMsg(tagName);
        }
        return guiResponses.getTaggedTasks(tagList.getTag(tagName)
                .getTaggedTasks());


    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof FindTagCommand;
    }
}
