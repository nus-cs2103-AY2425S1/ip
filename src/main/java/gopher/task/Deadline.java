package gopher.task;

import java.time.LocalDateTime;

import gopher.exception.InvalidTokenException;
import gopher.parser.Parser;

/**
 * Represents deadline task.
 * Includes task description and due date.
 */
public class Deadline extends Task {
    private LocalDateTime dueDate;

    /**
     * Constructor for Deadline class
     *
     * @param name name of the task
     * @param dueDate deadline of the task
     */
    public Deadline(String name, String dueDate) {
        super(name);
        this.dueDate = Parser.parseDateString(dueDate);
    }

    @Override
    public void update(String[] tokens) throws InvalidTokenException {
        // Parse tokens into individual task fields
        String[] parsedResult = Parser.parseUpdateDeadlineTaskCommand(tokens);

        // Unpack parsed results
        String taskName = parsedResult[0];
        String dueDateString = parsedResult[1];

        // Set new task name
        if (!taskName.isEmpty()) {
            this.name = taskName;
        }

        // Set new task due date
        if (!dueDateString.isEmpty()) {
            this.dueDate = Parser.parseDateString(dueDateString);
        }
    }

    @Override
    public String getSaveMessage() {
        return String.format("D | %s | %s | %s",
                getStatusIcon(),
                this.name,
                Parser.parseLocalDateTime(this.dueDate));
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by %s)",
                super.toString(),
                Parser.displayDate(this.dueDate));
    }
}
