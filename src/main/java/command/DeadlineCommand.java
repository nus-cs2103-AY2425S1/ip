package command;

import chatterboxexceptions.ChatterboxExceptions;
import gui.GuiResponses;
import parser.Parser;
import tags.TagList;
import tasks.TaskList;

import java.time.LocalDateTime;

public class DeadlineCommand extends Command {
    public DeadlineCommand() {
    }

    @Override
    public  String execute(String input, GuiResponses guiResponses,
                                   TagList tagList,
                           TaskList taskList, Parser parser) throws ChatterboxExceptions.ChatterBoxNoInput,
                            ChatterboxExceptions.ChatterBoxMissingParameter {
        String[] parsed = parser.parseDeadline(input);


        LocalDateTime deadlineDate = parser.parseDateTime(parsed[1]);


        if (deadlineDate == null) {

            taskList.addDeadline(parsed[0], parsed[1]);

        } else {
            //add back by for string

            taskList.addDeadline(parsed[0], deadlineDate);
        }

        return guiResponses.addTaskMsg("Deadline", taskList.size());

    }
}
