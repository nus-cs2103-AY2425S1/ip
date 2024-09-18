package taskType;

import bot.DateConverter;
import bot.Parser;

import java.util.Objects;

/**
 * The TaskBuilder class is responsible for creating different types of tasks (TODO, DEADLINE, EVENT)
 * based on user input. It is designed as a factory class and it handles the
 * construction of tasks and validates the task format and
 * parameters such as date and description.
 */
public class TaskBuilder {
    private String description;
    private Parser.TaskType taskType;
    private String by;
    private String from;
    private String to;

    public TaskBuilder(String description, Parser.TaskType taskType) {
        this.description = description;
        this.taskType = taskType;
    }


    /**
     * Sets the 'by' parameter for a DEADLINE task.
     *
     * @param by The deadline date/time string.
     * @return The current instance of TaskBuilder for method chaining.
     */
    public TaskBuilder by(String by) {
        if (Objects.equals(by, "")) {
            System.out.println("by cannot be empty");
            this.by = null;
            return this;
        }
        String date = DateConverter.convertDate(by);
        if (Objects.equals(date, "Invalid date format!")) {
            this.by = null;
            return this;
        }

        this.by = DateConverter.convertDate(by);
        return this;
    }

    //Set by but without the need to check for format of date
    public TaskBuilder specialBy(String by) {
        this.by = by;
        return this;
    }
    /**
     * Sets the 'from' parameter for an EVENT task.
     *
     * @param from The start date/time string for the event.
     * @return The current instance of TaskBuilder for method chaining.
     */
    public TaskBuilder from(String from) {
        if (Objects.equals(from, "")) {
            System.out.println("from cannot be empty");
            this.by = null;
            return this;
        }
        String date = DateConverter.convertDate(from);
        if (Objects.equals(date, "Invalid date format!")) {
            this.from = null;
            return this;
        }
        this.from = DateConverter.convertDate(from);
        return this;
    }

    public TaskBuilder specialFrom(String from) {
        this.from = from;
        return this;
    }

    public TaskBuilder specialTo(String to) {
        this.to = to;
        return this;
    }

    /**
     * Sets the 'to' parameter for an EVENT task.
     *
     * @param to The end date/time string for the event.
     * @return The current instance of TaskBuilder for method chaining.
     */
    public TaskBuilder to(String to) {
        if (Objects.equals(to, "")) {
            System.out.println("to cannot be empty");
            this.by = null;
            return this;
        }

        String date = DateConverter.convertDate(to);
        if (Objects.equals(date, "Invalid date format!")) {
            this.to = null;
            return this;
        }
        this.to = DateConverter.convertDate(to);
        return this;
    }

    /**
     * Builds the appropriate Task object based on the provided type and parameters.
     *
     * @return A Task object representing the built task (ToDo, Deadline, or Event).
     * @throws IllegalArgumentException if the task parameters are not properly specified.
     */
    public Task build() throws IllegalArgumentException {
        switch (taskType) {
            case TODO:
                if (!Objects.equals(description, "")) {
                    ToDo todo = new ToDo(description);
                    System.out.println(todo);
                    return todo;
                } else {
                    System.out.println("Todo requires a description!");
                    throw new IllegalArgumentException("Todo requires a description " +
                            "and in a proper format!");
                }
            case DEADLINE:
                if (by != null) {
                    Deadline deadline = new Deadline(description, by);
                    System.out.println(deadline);
                    return deadline;
                } else {
                    System.out.println("deadline requires a 'by' parameter man!");
                    throw new IllegalArgumentException("deadline requires a 'by' " +
                            "parameter and in a proper format man!");
                }
            case EVENT:
                if (from != null && to != null) {
                    Event event = new Event(description, from, to);
                    System.out.println(event);
                    return event;
                } else {
                    System.out.println("event requires 'from' and 'to' parameter");

                    throw new IllegalArgumentException("event requires 'from' and 'to' parameter " +
                            "and in a proper format");
                }
            default:
                throw new IllegalArgumentException("invalid task type.");
        }
    }
}
