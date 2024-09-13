package gopher.task;

import java.time.LocalDateTime;

import gopher.parser.Parser;

/**
 * Represents event task.
 * Include task description, start date and end date.
 */
public class Event extends Task {
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    /**
     * Constructor of Event class
     *
     * @param name name of task
     * @param startDate start date of the task
     * @param endDate end date of the task
     */
    public Event(String name, String startDate, String endDate) {
        super(name);
        this.startDate = Parser.parseDateString(startDate);
        this.endDate = Parser.parseDateString(endDate);
    }

    @Override
    public void update(String[] tokens) {
        // Determine the positions of from & to tokens if they exist in the command tokens
        int fromTokenIndex = -1;
        int toTokenIndex = -1;
        for (int i = 2; i < tokens.length; i++) {
            if (tokens[i].equalsIgnoreCase("/from")) {
                fromTokenIndex = i;
            }
            if (tokens[i].equalsIgnoreCase("/to")) {
                toTokenIndex = i;
            }
        }

        // Update task name based on the position of /from and /to token
        StringBuilder taskName = new StringBuilder();
        int taskNameIndexLimit = fromTokenIndex != -1
                ? fromTokenIndex
                : toTokenIndex != -1
                ? toTokenIndex
                : tokens.length;
        for (int i = 2; i < taskNameIndexLimit; i++) {
            taskName.append(tokens[i]);
            if (i < taskNameIndexLimit - 1) {
                taskName.append(" ");
            }
        }
        if (!taskName.isEmpty()) {
            this.name = taskName.toString();
        }

        // Update start date if /from token exists in the command tokens
        if (fromTokenIndex != -1) {
            StringBuilder startDateString = new StringBuilder();
            int startDateIndexLimit = toTokenIndex != -1
                    ? toTokenIndex
                    : tokens.length;
            for (int i = taskNameIndexLimit + 1; i < startDateIndexLimit; i++) {
                startDateString.append(tokens[i]);
                if (i < startDateIndexLimit - 1) {
                    startDateString.append(" ");
                }
            }
            this.startDate = Parser.parseDateString(startDateString.toString());
        }

        // Update end date if /to token exists in the command tokens
        if (toTokenIndex != -1) {
            StringBuilder endDateString = new StringBuilder();
            for (int i = toTokenIndex + 1; i < tokens.length; i++) {
                endDateString.append(tokens[i]);
                if (i < tokens.length - 1) {
                    endDateString.append(" ");
                }
            }
            this.endDate = Parser.parseDateString(endDateString.toString());
        }
    }

    @Override
    public String getSaveMessage() {
        return String.format("E | %s | %s | %s | %s",
                getStatusIcon(),
                this.name,
                Parser.parseLocalDateTime(this.startDate),
                Parser.parseLocalDateTime(this.endDate));
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from %s to %s)",
                super.toString(),
                Parser.displayDate(this.startDate),
                Parser.displayDate(this.endDate));
    }
}
