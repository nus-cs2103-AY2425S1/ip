package Task;

import CommandLine.Line;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDateTime deadline;
    private static Line line = new Line();

    private Deadline(String name, TaskType taskType, LocalDateTime deadline) {
        super(name, taskType);
        this.deadline = deadline;
    }

    /**
     * Returns the task type as a string
     * @return task type as a string
     */
    @Override
    public String getTaskTypeAsString(){
            return "D";
    }

    /**
     * Creates a deadline with given string.
     *
     * @param name the string containing information about the task type
     * @param taskType the type of task
     * @throws TaskCreationException if error occurs while creating task
     * @return Deadline instance
     */
//    deadline return book /by 2/12/2019 1800
    public static Deadline of(String name, TaskType taskType) throws TaskCreationException {
        try {
            String[] parts = name.split("/by", 2);
            String taskName = parts[0].trim();
            String taskDeadline = parts[1].trim();
            assert !taskName.isEmpty() : "Task name should not be empty";
            assert !taskDeadline.isEmpty() : "Task deadline should not be empty";
            System.out.println("Deadline is");
            System.out.println(taskDeadline);
            LocalDateTime deadline = formatter(taskDeadline);
            return new Deadline(taskName, taskType, deadline);
        } catch (ArrayIndexOutOfBoundsException e) {
            line.drawLine();
            System.out.println("      Error ");
            line.drawLine();
            throw new TaskCreationException();
        } catch (DateTimeParseException e) {
            line.drawLine();
            System.out.println("      Invalid deadline format. Expected format: 'task description /by date/time' ");
            line.drawLine();
            throw new TaskCreationException();
        }
    }

    /**
     * Returns the deadline formatted as a string.
     *
     * @return the deadline as a string
     */
    public String getDeadline() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
        return this.deadline.format(formatter);
    }

    /**
     * Returns a deadline with given string.
     *
     * @return returns the task and its given information as a string
     */
    @Override
    public String getTaskName() {
        return super.getTaskName() + " (by: " + this.getDeadline() + ")";
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
