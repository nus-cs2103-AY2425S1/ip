package gopher.task;

import gopher.exception.EmptyTaskDescriptionException;
import gopher.exception.MissingTokenException;
import gopher.exception.UnknownCommandException;

import java.lang.StringBuilder;
import java.time.LocalDateTime;

import gopher.parser.Parser;

/**
 * Represents the task that the user needs to track.
 */
public abstract class Task {
    /**
     * Represents the todo Task.
     * Only includes task description.
     */
    private static class ToDo extends Task {
        public ToDo(String name) {
            super(name);
        }

        @Override
        public String getSaveMessage() {
            return String.format("T | %s | %s",
                    getStatusIcon(),
                    this.name);
        }

        @Override
        public String toString() {
            return String.format("[T]%s", super.toString());
        }
    }

    /**
     * Represents deadline task.
     * Includes task description and due date.
     */
    private static class Deadline extends Task {
        private LocalDateTime dueDate;

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

    /**
     * Represents event task.
     * Include task description, start date and end date.
     */
    private static class Event extends Task {
        private LocalDateTime startDate;
        private LocalDateTime endDate;

        public Event(String name, String startDate, String endDate) {
            super(name);
            this.startDate = Parser.parseDateString(startDate);
            this.endDate = Parser.parseDateString(endDate);
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

    /** Name of the Task */
    protected String name;

    /** Whether the task is done */
    protected boolean isDone;

    private Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Factory method which creates different types of task
     * depending on the input command.
     *
     * @param command task creation command
     * @return task of the correct type
     * @throws UnknownCommandException if command is not recognized
     * @throws EmptyTaskDescriptionException if task description is empty
     * @throws MissingTokenException if tokens is missing for the given task type
     */
    public static Task of(String command) throws UnknownCommandException,
            EmptyTaskDescriptionException, MissingTokenException {
        String[] tokens = command.split(" ");

        String taskType = tokens[0];
        if (!taskType.equalsIgnoreCase("todo")
                && !taskType.equalsIgnoreCase("deadline")
                && !taskType.equalsIgnoreCase("event")) {
            throw new UnknownCommandException(taskType);
        }

        if (tokens.length < 2) {
            throw new EmptyTaskDescriptionException(taskType);
        }

        StringBuilder taskName = new StringBuilder();
        if (taskType.equalsIgnoreCase("todo")) {
            for (int i = 1; i < tokens.length; i++) {
                taskName.append(tokens[i]);
                if (i < tokens.length - 1) {
                    taskName.append(" ");
                }
            }
            return new ToDo(taskName.toString());
        } else if (taskType.equalsIgnoreCase("deadline")) {
            int byTokenIndex = -1;
            for (int i = 1; i < tokens.length; i++) {
                if (tokens[i].equalsIgnoreCase("/by")) {
                    byTokenIndex = i;
                }
            }

            if (byTokenIndex == -1) {
                throw new MissingTokenException(taskType, "/by");
            }

            for (int i = 1; i < byTokenIndex; i++) {
                taskName.append(tokens[i]);
                if (i < byTokenIndex - 1) {
                    taskName.append(" ");
                }
            }

            StringBuilder dueDate = new StringBuilder();
            for (int i = byTokenIndex + 1; i < tokens.length; i++) {
                dueDate.append(tokens[i]);
                if (i < tokens.length - 1) {
                    dueDate.append(" ");
                }
            }

            return new Deadline(taskName.toString(), dueDate.toString());
        } else if (taskType.equalsIgnoreCase("event")) {
            int fromTokenIndex = -1;
            int toTokenIndex = -1;
            for (int i = 1; i < tokens.length; i++) {
                if (tokens[i].equalsIgnoreCase("/from")) {
                    fromTokenIndex = i;
                }
                if (tokens[i].equalsIgnoreCase("/to")) {
                    toTokenIndex = i;
                }
            }

            if (fromTokenIndex == -1) {
                throw new MissingTokenException(taskType, "/from");
            }

            if (toTokenIndex == -1) {
                throw new MissingTokenException(taskType, "/to");
            }

            for (int i = 1; i < fromTokenIndex; i++) {
                taskName.append(tokens[i]);
                if (i < fromTokenIndex - 1) {
                    taskName.append(" ");
                }
            }

            StringBuilder startDate = new StringBuilder();
            for (int i = fromTokenIndex + 1; i < toTokenIndex; i++) {
                startDate.append(tokens[i]);
                if (i < toTokenIndex - 1) {
                    startDate.append(" ");
                }
            }

            StringBuilder endDate = new StringBuilder();
            for (int i = toTokenIndex + 1; i < tokens.length; i++) {
                endDate.append(tokens[i]);
                if (i < tokens.length - 1) {
                    endDate.append(" ");
                }
            }
            return new Event(taskName.toString(),
                    startDate.toString(),
                    endDate.toString());
        } else {
            throw new UnknownCommandException(taskType);
        }
    }

    /**
     * Gets the save file message representation of this task.
     *
     * @return save string format of the task
     */
    public abstract String getSaveMessage();

    /**
     * Marks the given task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the given task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Gets the stats icon of the task.
     *
     * @return stats icon of the task
     */
    protected String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    @Override
    public String toString() {
        return String.format("[%s] %s",
                getStatusIcon(),
                this.name);
    }
}
