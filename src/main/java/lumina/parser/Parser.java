package lumina.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import lumina.exception.LuminaException;
import lumina.task.DeadlineTask;
import lumina.task.EventTask;
import lumina.task.Task;
import lumina.task.TodoTask;

/**
 * Utility class for parsing dates and task data.
 */
public class Parser {

    /**
     * Parses a date string into a LocalDate object.
     *
     * @param date the date string to parse
     * @return the parsed LocalDate object
     * @throws LuminaException if the date string is in an invalid format
     */
    public LocalDate parseDateString(String date) throws LuminaException {
        LocalDate ret = null;

        try {
            ret = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new LuminaException("Invalid date format! Please try again");
        }

        return ret;
    }

    /**
     * Parses a data line from the storage file into a Task object.
     * The data line should be in the format: "`type` | `done` | `description` | `additionalData`"
     * The type can be "T" for TodoTask, "D" for DeadlineTask, or "E" for EventTask.
     *
     * @param line the data line to parse
     * @return the parsed Task object, or null if the line is invalid
     */
    public Task parseDataLine(String line) {
        String[] parts = line.split(" \\| ");
        LuminaException luminaException = new LuminaException(
                String.format("Corrupt data entry: %s", line)
        );

        Task task = null;

        try {
            if (parts.length < 3) {
                throw luminaException;
            }
            String type = parts[0].trim();
            boolean isDone = parts[1].trim().equals("1");
            String description = parts[2].trim();
            switch (type) {
            case "T":
                if (parts.length != 3) {
                    throw luminaException;
                }
                task = new TodoTask(description, isDone);
                break;
            case "D":
                if (parts.length != 4) {
                    throw luminaException;
                }
                String byDateTime = parts[3].trim();
                LocalDate byDateObject = parseDateString(byDateTime);
                task = new DeadlineTask(description, byDateObject, isDone);
                break;
            case "E":
                if (parts.length != 5) {
                    throw luminaException;
                }
                String startDateTime = parts[3].trim();
                String endDateTime = parts[4].trim();
                LocalDate startDateObject = parseDateString(startDateTime);
                LocalDate endDateObject = parseDateString(endDateTime);
                task = new EventTask(description, startDateObject, endDateObject, isDone);
                break;
            default:
                throw luminaException;
            }
        } catch (LuminaException e) {
            System.err.println(e.getMessage());
        }

        return task;
    }
}
