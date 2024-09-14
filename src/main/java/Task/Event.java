package Task;

import CommandLine.Line;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private static Line line = new Line();

    private Event(String name, TaskType taskType, LocalDateTime startTime, LocalDateTime endTime) {
        super(name, taskType);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Returns the task type.
     * @return  the task type as a string
     */
    @Override
    public String getTaskTypeAsString(){
            return "E";
    }

    /**
     * Returns the start time of the event.
     * @return the start time as a string
     */
    public String getStart() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return this.startTime.format(formatter);
    }

    /**
     * Returns the end time of the event.
     * @return the ending time as a string
     */
    public String getEnd() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return this.endTime.format(formatter);
    }

    /**
     * Returns task description
     *
     * @return returns description of task
     */
    @Override
    public String getTaskDescription() {
        return super.getTaskName();
    }

    /**
     * Returns a task with given string.
     * @return  the task and its given information as a string
     */
    @Override
    public String getTaskName() {
        return super.getTaskName() + " (from: " + this.getStart() + " to: " + this.getEnd() + ")";
    }

    /**
     * Creates a task with given string.
     *
     * @param name the string containing information about the task type
     * @param taskType the type of task
     * @throws TaskCreationException if error occurs while creating task
     * @return Event instance
     */
    //    event class /from 2/12/2019 1600 /to 2/12/2019 1900
    public static Event of(String name, TaskType taskType) throws TaskCreationException {
        try {
            String[] parts = name.split("/from", 2);
            String taskName = parts[0].trim();
            assert !taskName.isEmpty() : "Task name should not be empty";
            String[] dateTimes = parts[1].trim().split(" /to ", 2);
            assert !dateTimes[0].isEmpty() : "Task start should not be empty";
            assert !dateTimes[1].isEmpty() : "Task end should not be empty";
            LocalDateTime start = formatter(dateTimes[0].trim());
            LocalDateTime end = formatter(dateTimes[1].trim());
            return new Event(taskName, taskType, start, end);
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            System.out.println("Invalid Event format. Expected format: 'task description /by date/time' ");
            throw new TaskCreationException();
        }
    }


    public static LocalDateTime formatter(String s) throws DateTimeParseException {
        DateTimeFormatter[] formatters = {
                DateTimeFormatter.ofPattern("d/M/yyyy HHmm"),
                DateTimeFormatter.ofPattern("dd/M/yyyy HHmm"),
                DateTimeFormatter.ofPattern("d/MM/yyyy HHmm"),
                DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"),
        };

        for (DateTimeFormatter formatter : formatters) {
            try {
                return LocalDateTime.parse(s, formatter);
            } catch (DateTimeParseException e) {
                // Continue to the next formatter
            }
        }
        throw new DateTimeParseException("Date/time format is invalid", s, 0);
    }
}