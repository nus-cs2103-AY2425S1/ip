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
            ChatterboxExceptions.ChatterBoxMissingParameter {
        // input will be in format "findtag <tag>"
        String tagName = parser.findTagParseTagName(input).trim().toLowerCase();
        if (!tagList.containsTag(tagName)) {
            return guiResponses.tagNotFoundMsg(tagName);
        }
        System.out.println(tagList.getTag(tagName).getTaggedTasks());;
        return guiResponses.getTaggedTasks(tagList.getTag(tagName)
                .getTaggedTasks());


    }
}
