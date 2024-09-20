package command;

import java.time.LocalDateTime;

import chatterboxexceptions.ChatterboxExceptions;
import gui.GuiResponses;
import parser.Parser;
import tags.TagList;
import tasks.TaskList;

/**
 * Represents a command to add a deadline task.
 */
public class DeadlineCommand extends Command {
    public DeadlineCommand() {
    }

    /**
     * Executes the command to add a deadline task.
     * @param input The input string.
     * @param guiResponses The gui response object.
     * @param tagList The tag list object.
     * @param taskList The task list object.
     * @param parser The parser object.
     * @return The response message.
     * @throws ChatterboxExceptions.ChatterBoxNoInput If no input is provided.
     * @throws ChatterboxExceptions.ChatterBoxMissingParameter If a parameter is missing.
     */
    @Override
    public String execute(String input, GuiResponses guiResponses,
                                   TagList tagList,
                           TaskList taskList, Parser parser) throws ChatterboxExceptions.ChatterBoxNoInput,
            ChatterboxExceptions.ChatterBoxMissingParameter, ChatterboxExceptions.ChatterBoxInvalidInput {
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

    @Override
    public boolean equals(Object obj) {
        return obj instanceof DeadlineCommand;
    }
}
