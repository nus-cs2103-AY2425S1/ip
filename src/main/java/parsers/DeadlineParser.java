package parsers;

import tasks.Deadline;
import tasks.Task;

import exceptions.TarsException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DeadlineParser extends Parser {
    @Override
    public Task parse(String[] taskInfo) {
        if (taskInfo.length <= 1) {
            throw new TarsException("Add a name to your deadline");
        }

        String[] split = taskInfo[1].split("/", 2);

        String name = split[0].trim();
        String[] date = split.length > 1
                ? split[1].split(" ", 2)
                : null;
        
        if (name.isEmpty()) {
            throw new TarsException("Try again. Next time tell me what your deadline is");
        }

        LocalDate deadlineDate = validateCommand(date);


        return new Deadline(name, deadlineDate);
    }
    
    public LocalDate validateCommand(String[] deadlineCommand) {
        if (deadlineCommand == null) {
            throw new TarsException("Add a /by command and a deadline date");
        }

        switch(deadlineCommand.length) {
            case 1:
                if (deadlineCommand[0].equals("by")) {
                    throw new TarsException("Finish the command by adding a deadline date");
                } else {
                    throw new TarsException("Add the /by command");
                }

            case 2:
                if (deadlineCommand[0].equals("by")) {
                    if (deadlineCommand[1].isEmpty()) {
                        throw new TarsException("Finish the command by adding a deadline date");
                    }
                } else {
                    throw new TarsException("Add the /by command");
                }
        }
        LocalDate date;
        try {
            date = LocalDate.parse(deadlineCommand[1], Parser.FORMATTER);
        } catch (DateTimeParseException e) {
            throw new TarsException("Date in wrong format. It should be in dd-mm-yy format");
        }

        return date;

    }
}
