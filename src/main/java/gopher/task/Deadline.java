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
        // Determine the position of /by token in update command tokens
        // Check for any invalid tokens as well
        int byTokenIndex = -1;
        for (int i = 2; i < tokens.length; i++) {
            if (tokens[i].equalsIgnoreCase("/by")) {
                byTokenIndex = i;
            } else if (tokens[i].startsWith("/")) {
                throw new InvalidTokenException("deadline", tokens[i]);
            }
        }

        // Update task name based on the position of /by token
        StringBuilder taskName = new StringBuilder();
        int taskNameIndexLimit = byTokenIndex != -1 ? byTokenIndex : tokens.length;
        for (int i = 2; i < taskNameIndexLimit; i++) {
            taskName.append(tokens[i]);
            if (i < taskNameIndexLimit - 1) {
                taskName.append(" ");
            }
        }
        if (!taskName.isEmpty()) {
            this.name = taskName.toString();
        }

        // Update due date if /by token is found in command tokens
        if (byTokenIndex != -1) {
            StringBuilder dueDateString = new StringBuilder();
            for (int i = byTokenIndex + 1; i < tokens.length; i++) {
                dueDateString.append(tokens[i]);
                if (i < tokens.length - 1) {
                    dueDateString.append(" ");
                }
            }
            this.dueDate = Parser.parseDateString(dueDateString.toString());
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
