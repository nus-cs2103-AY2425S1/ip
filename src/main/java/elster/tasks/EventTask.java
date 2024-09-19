package elster.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import elster.Elseption;

/**
 * Event tasks are tasks that have a start and end time on top of the base task functionalities
 */
public class EventTask extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Constructor for an event task.
     *
     * @param description Description of the event task.
     * @param start Start time of the event task.
     * @param end End time of the event task.
     */
    public EventTask(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Factory method for an event task.
     * Parses input string to create an event with a description, a start time and an end time.
     *
     * @param input Input from terminal to be parsed.
     * @return Created event task.
     */
    public static EventTask of(String input) throws Elseption {
        LocalDateTime start;
        LocalDateTime end;

        if (input.strip().equals("event")) {
            throw new Elseption("Elster \"kindly\" requests you to have details for your task");

        } else if (!input.contains("/from")) {
            throw new Elseption("Elster \"kindly\" requests you to have a start time with /from");

        } else if (!input.contains("/to")) {
            throw new Elseption("Elster \"kindly\" requests you to have a end time with /to");
        }

        try {
            String startStr = input.substring(input.indexOf("/from") + 6 , input.indexOf("/to")).strip();
            String endStr = input.substring(input.indexOf("/to") + 4).strip();

            if (startStr.length() == 10 && endStr.length() == 10) {
                start = LocalDate.parse(startStr).atTime(23, 59);
                end = LocalDate.parse(endStr).atTime(23, 59);

            } else {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                start = LocalDateTime.parse(startStr, formatter);
                end = LocalDateTime.parse(endStr, formatter);
            }

        } catch (Exception e) {
            throw new Elseption("Elster \"gently\" requests a yyyy-mm-dd or yyyy-mm-dd HH:mm format");
        }

        return new EventTask(
                input.substring(6 , input.indexOf("/from")).strip(),
                start,
                end
        );
    }

    @Override
    public String toString() {
        StringBuilder resultStr = new StringBuilder();
        if (this.status) {
            resultStr
                    .append("[E][X] ")
                    .append(this.description)
                    .append(" (from: ")
                    .append(start)
                    .append(" to: ")
                    .append(end)
                    .append(")");
        } else {
            resultStr
                    .append("[E][ ] ")
                    .append(this.description)
                    .append(" (from: ")
                    .append(start)
                    .append(" to: ")
                    .append(end)
                    .append(")");
        }
        for (String tag : tags) {
            resultStr.append(" #").append(tag);
        }
        return resultStr.toString();
    }

    /**
     * Returns a string representation of the event task suitable for writing to the save file.
     *
     * @return String representation of event formatted for file writing.
     */
    @Override
    public String toFileString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        if (this.status) {
            return "E | 1 | " + this.description + " | " + this.start.format(formatter)
                    + " | " + this.end.format(formatter) + " | " + this.tags;
        } else {
            return "E | 0 | " + this.description + " | " + this.start.format(formatter)
                    + " | " + this.end.format(formatter) + " | " + this.tags;
        }
    }
}
