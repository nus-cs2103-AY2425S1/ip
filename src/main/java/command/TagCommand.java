package command;

import chatterboxexceptions.ChatterboxExceptions;
import gui.GuiResponses;
import parser.Parser;
import tags.Tag;
import tags.TagList;
import tasks.TaskList;

/**
 * Represents the command to tag a task with a tag.
 */
public class TagCommand extends Command {
    public TagCommand() {
    }

    /**
     * Executes the command to tag a task with a tag.
     * @param input The input string from the user.
     * @param guiResponses The object that handles the responses to the user.
     * @param tagList The list of tags.
     * @param taskList The list of tasks.
     * @param parser The object that parses the input string.
     * @return The response to the user.
     * @throws ChatterboxExceptions.ChatterBoxNoInput If the input is empty.
     * @throws ChatterboxExceptions.ChatterBoxMissingParameter If the input is missing a parameter.
     */
    @Override
    public String execute(String input, GuiResponses guiResponses,
                           TagList tagList,
                           TaskList taskList, Parser parser) throws ChatterboxExceptions.ChatterBoxNoInput,
            ChatterboxExceptions.ChatterBoxMissingParameter {
        String result;
        String tagText = parser.parseTagText(input);
        int tagIndex = parser.parseTagIndex(input) - 1;
        Tag tag;
        if (tagList.containsTag(tagText)) {
            tag = tagList.getTag(tagText);
        } else {
            tag = new Tag(tagText);
            tagList.addTag(tag);
        }

        tag.tagTask(taskList.getTask(tagIndex));
        taskList.getTask(tagIndex).addTag(tag);
        result = guiResponses.tagTaskMsg(taskList.getTask(tagIndex), tagText);


        return result;
    }
}
