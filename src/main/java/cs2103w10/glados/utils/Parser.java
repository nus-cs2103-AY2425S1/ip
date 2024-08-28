package cs2103w10.glados.utils;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import cs2103w10.glados.enums.TaskType;

import cs2103w10.glados.exceptions.DateNotFoundException;
import cs2103w10.glados.exceptions.DateNotParsedException;
import cs2103w10.glados.exceptions.DateRangeNotFoundException;
import cs2103w10.glados.exceptions.DescriptionNotFoundException;
import cs2103w10.glados.exceptions.GladosException;
import cs2103w10.glados.exceptions.TaskNotFoundException;

public class Parser {
    
    /**
     * Parses a task based on its taskType and command line arguments.
     * 
     * @param taskType Type of task.
     * @param input Command line arguments passed by user.
     * @return ParsedInfo object with task description and corresponding dates.
     * @throws GladosException If arguments cannot be parsed correctly.
     */
    public static ParsedInfo parseTask(TaskType taskType, String input) throws GladosException {
        switch (taskType) {
        case TODO:
            String todoDescription = input.trim();
            checkDescription(todoDescription);
            return new ParsedInfo(todoDescription, new LocalDate[0]);

        case DEADLINE:
            checkDescription(input.trim());
            String[] deadlineInputs = input.split("/by");
            String deadlineDescription = deadlineInputs[0].trim();
            checkDescription(deadlineDescription);

            if (deadlineInputs.length != 2 || deadlineInputs[1].trim().equals("")) {
                throw new DateNotFoundException();
            }

            return new ParsedInfo(
                    deadlineInputs[0].trim(), 
                    new LocalDate[]{ 
                            parseDate(deadlineInputs[1].trim())});

        case EVENT:
            checkDescription(input.trim());
            String[] eventInputs = input.split("/from");
            String eventDescription = eventInputs[0].trim();
            checkDescription(eventDescription);
            
            if (eventInputs.length != 2) {
                throw new DateRangeNotFoundException();
            }

            String[] dateRange = eventInputs[1].split("/to");
            if (dateRange.length != 2 || dateRange[0].trim().equals("") || dateRange[1].trim().equals("")) {
                throw new DateRangeNotFoundException();
            }
            
            return new ParsedInfo(
                    eventInputs[0].trim(), 
                    new LocalDate[]{
                            parseDate(dateRange[0].trim()), 
                            parseDate(dateRange[1].trim())});
        default:
            throw new TaskNotFoundException();
        }
    }

    public static LocalDate parseDate(String input) throws DateNotParsedException {
        try {
            return LocalDate.parse(input);
        } catch (DateTimeParseException e) {
            throw new DateNotParsedException();
        }
    }

    private static void checkDescription(String description) throws DescriptionNotFoundException {
        if (description.equals("")) {
           throw new DescriptionNotFoundException();
        }
   }

}
