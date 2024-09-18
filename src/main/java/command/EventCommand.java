package command;

import java.time.LocalDateTime;

import chatterboxexceptions.ChatterboxExceptions;
import gui.GuiResponses;
import parser.Parser;
import tags.TagList;
import tasks.TaskList;


/**
 * Represents the command to add an event task.
 */
public class EventCommand extends Command {
    public EventCommand() {
    }

    /**
     * Executes the command to add an event task.
     * @param input The input string.
     * @param guiResponses The gui response object.
     * @param tagList The tag list object.
     * @param taskList The task list object.
     * @param parser The parser object.
     * @return The response string.
     * @throws ChatterboxExceptions.ChatterBoxNoInput If no input is provided.
     * @throws ChatterboxExceptions.ChatterBoxMissingParameter If a parameter is missing.
     */
    @Override
    public String execute(String input, GuiResponses guiResponses,
                           TagList tagList,
                           TaskList taskList, Parser parser) throws ChatterboxExceptions.ChatterBoxNoInput, ChatterboxExceptions.ChatterBoxMissingParameter, ChatterboxExceptions.ChatterBoxInvalidInput {
        String[] eventParsed = parser.parseEvent(input);

        LocalDateTime startDate = parser.parseDateTime(eventParsed[1]); //from 4
        LocalDateTime endDate = parser.parseDateTime(eventParsed[2]);
        if (startDate == null || endDate == null) {

            taskList.addEvent(eventParsed[0].trim(), eventParsed[1], eventParsed[2]);

        } else {
            taskList.addEvent(eventParsed[0].trim(), startDate, endDate);
        }

        return guiResponses.addTaskMsg("Event", taskList.size());
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof EventCommand;
    }
}
