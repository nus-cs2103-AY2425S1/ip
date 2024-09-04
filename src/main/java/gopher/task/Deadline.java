package gopher.task;

import java.time.LocalDateTime;

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
