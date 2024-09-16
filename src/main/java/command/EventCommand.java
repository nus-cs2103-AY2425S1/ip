package command;

import chatterboxexceptions.ChatterboxExceptions;
import gui.GuiResponses;
import parser.Parser;
import tags.TagList;
import tasks.TaskList;

import java.time.LocalDateTime;

public class EventCommand extends Command{
    public EventCommand() {
    }

    @Override
    public  String execute(String input, GuiResponses guiResponses,
                           TagList tagList,
                           TaskList taskList, Parser parser) throws ChatterboxExceptions.ChatterBoxNoInput,
            ChatterboxExceptions.ChatterBoxMissingParameter {
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
}
