package tars.parsers;

import tars.exceptions.TarsException;

import tars.tasks.Task;
import tars.tasks.Event;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class EventParser extends Parser {
    @Override
    public Task parse(String[] taskInfo) {
        if (taskInfo.length <= 1) {
            throw new TarsException("event? What is that even supposed to mean?\nAdd a name, start time and end time");
        }

        String[] split = taskInfo[1].split("/", 3);

        String name = split[0].trim();

        String[] startCommand = split.length > 1
                ? split[1].split(" ", 2)
                : null;

        String[] endCommand = split.length > 2
                ? split[2].split(" ", 2)
                : null;
        
        if (name.isEmpty()) {
            throw new TarsException("Try again. Next time tell me what your event is all about");
        }
        
        LocalDate[] dates = validateCommand(startCommand, endCommand);

        return new Event(name, dates[0], dates[1]);
    }
    
    public LocalDate[] validateCommand(String[] startCommand, String[] endCommand) {
        if (startCommand == null) {
            throw new TarsException("Add a /from command and a start date");
        } else {
            switch (startCommand.length) {
                case 1:
                    if (startCommand[0].equals("from")) {
                        throw new TarsException("Add a event start date");
                    } else {
                        throw new TarsException("Add the /from command");
                    }

                case 2:
                    if (startCommand[0].equals("from")) {
                        if (startCommand[1].isEmpty()) {
                            throw new TarsException("Add an event start date");
                        }
                    } else {
                        throw new TarsException("Add the /from command");
                    }
            }
        }

        if (endCommand == null) {
            throw new TarsException("Add a /to command and a end date");
        } else {
            switch (endCommand.length) {
                case 1:
                    if (endCommand[0].equals("to")) {
                        throw new TarsException("Add an event end date");
                    } else {
                        throw new TarsException("Add the /to command");
                    }

                case 2:
                    if (endCommand[0].equals("to")) {
                        if (endCommand[1].isEmpty()) {
                            throw new TarsException("Add an event end date");
                        }
                    } else {
                        throw new TarsException("Add the /to command");
                    }
            }
        }

        LocalDate startDate, endDate;


        try {
            startDate = LocalDate.parse(startCommand[1].trim(), FORMATTER);

        } catch (DateTimeParseException e) {

            throw new TarsException("Start date in wrong format. It should be in dd-mm-yy format");

        }

        try {
            endDate = LocalDate.parse(endCommand[1].trim(), FORMATTER);

        } catch (DateTimeParseException e) {
            throw new TarsException("End date in wrong format. It should be in dd-mm-yy format");

        }

        return new LocalDate[]{startDate, endDate};
        
    }
}
