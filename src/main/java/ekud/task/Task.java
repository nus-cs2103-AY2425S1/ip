package ekud.task;

import java.util.HashMap;
import java.util.Locale;

import ekud.components.Parser;
import ekud.components.TaskList;
import ekud.exceptions.EkudException;
import ekud.ui.Ui;

/**
 * An abstract class that represents a task; cannot be instantiated directly.
 * <p/>
 * Each task minimally has a description and completion status.
 *
 * @author uniqly
 */
public abstract class Task {
    /**
     * Represents the priority of the task.
     */
    public enum Priority {
        HIGH, LOW;

        private boolean isCorrectKeyword(String keyword) throws EkudException {
            String emptyKeywordResponse = """
                    You've got to be kidding me! Seriously? You put nothing as the priority.
                    You could have input 'low' or 'high' but choose to put nothing! wow.""";

            if (keyword == null) {
                throw new EkudException(emptyKeywordResponse);
            }

            String upperCase = keyword.toUpperCase(Locale.ENGLISH);
            return this.toString().equals(upperCase);
        }

        public static Priority getPriority(String type) throws EkudException {
            String errorResponseFormat = """
                    Look at mister smarty pants over here telling me "I want '%s' priority".
                    Do you realise how insane you sound? Just to let you know there is only
                    'high' or 'low' priority.""";

            if (HIGH.isCorrectKeyword(type)) {
                return HIGH;
            } else if (LOW.isCorrectKeyword(type)) {
                return LOW;
            } else {
                throw new EkudException(String.format(errorResponseFormat, type));
            }
        }
    }

    private static final String PRINT_FORMAT_LOW_PRIORITY = "[%s] %s";
    private static final String PRINT_FORMAT_HIGH_PRIORITY = "[%s] %s *HIGH PRIORITY*";
    private static final String SAVE_FORMAT = "%d | %s | %s";

    /** The description of the task */
    protected String description;

    /** Boolean flag of the completion status */
    protected boolean isDone;

    /** priority of the task */
    protected Priority priority;

    /**
     * Constructs an abstract Task containing only a description.
     *
     * @param description The description of the event.
     * @throws EkudException If description is null or empty.
     */
    public Task(String description) throws EkudException {
        if (description == null || description.isEmpty()) {
            throw new EkudException(getEmptyDescriptionErrorMessage());
        }
        this.description = description;
        isDone = false;
        priority = Priority.LOW;
    }

    /**
     * Returns the Task corresponding to a given task's save-string. If a given taskSaveString is invalid,
     * a warning is printed and null is returned.
     *
     * @param taskSaveString The task's save-string.
     * @param ui The ui which prints the warning for an invalid taskSaveString.
     * @return Task corresponding to taskSaveString.
     * @see Ui
     * @see ekud.components.Storage#loadTasks(TaskList, Ui)
     */
    public static Task getTaskFromSave(String taskSaveString, Ui ui) {
        boolean isNonEmptySaveString = (taskSaveString != null && !taskSaveString.isEmpty());
        assert isNonEmptySaveString : "taskSaveString should be non-empty";
        assert ui != null : "ui should not be null";

        try {
            taskSaveString = taskSaveString.trim(); // removes extra new lines
            String[] args = taskSaveString.split("(\\s\\|\\s)"); // splits string along < | > delimiter

            String type = args[0];
            boolean isDone = args[1].equals("1");
            Priority priority = Priority.getPriority(args[2]);
            // CHECKSTYLE.OFF: Indentation
            Task task = switch (type) {
                case "T" -> new TodoTask(args[3]);
                case "D" -> new DeadlineTask(args[3], args[4]);
                case "E" -> new EventTask(args[3], args[4], args[5]);
                default -> null;
            };
            // CHECKSTYLE.ON: Indentation

            if (task != null) {
                task.isDone = isDone;
                task.priority = priority;
            }

            return task;

        } catch (IndexOutOfBoundsException | EkudException e) {
            String message = String.format(
                        "Warning: ekud.task.Task entry { %s } is missing required arguments or is"
                                + "incorrectly formatted\nRemoving ekud.task entry...",
                        taskSaveString);
            ui.addToBuffer(message);
            return null;
        }
    }

    /**
     * Creates a Task based on a given set of tokens.
     *
     * @param tokens A {@link HashMap} that maps tokens to {@link String} values.
     * @return {@link Task} based on tokens
     * @throws EkudException If the tokens map has missing or invalid tokens.
     * @see EkudException
     */
    public static Task getTaskFromTokens(HashMap<String, String> tokens) throws EkudException {
        assert tokens != null : "tokens should not be null";

        String invalidTypeErrorMessage = "Wow! What is this is type of task?"
                + "\n I'm not sure how to process this";

        String type = tokens.get(Parser.COMMAND_TOKEN).toLowerCase();
        String description = tokens.get(Parser.ARGUMENT_TOKEN);

        // CHECKSTYLE.OFF: Indentation
        return switch (type) {
            case "todo" -> new TodoTask(description);
            case "deadline" -> new DeadlineTask(description, tokens.get(DeadlineTask.BY_TOKEN));
            case "event" -> new EventTask(description, tokens.get(EventTask.FROM_TOKEN),
                    tokens.get(EventTask.TO_TOKEN));
            default -> throw new EkudException(invalidTypeErrorMessage);
        };
        // CHECKSTYLE.ON: Indentation
    }

    /**
     * Returns an error message {@link String} corresponding to this type of Task.
     *
     * @return Error message String.
     */
    public abstract String getEmptyDescriptionErrorMessage();

    /**
     * Returns a formatted {@link String} corresponding that will be processed by EKuD's storage
     * when saving, editing, or deleting.
     *
     * @return The tasks' save-string.
     * @see ekud.components.Storage
     */
    public String getSaveTaskString() {
        int statusInt = (isDone ? 1 : 0);
        return String.format(SAVE_FORMAT, statusInt, priority.toString(), description);
    }

    /**
     * Sets {@link #priority} based on a given {@link Priority}.
     * @param priority the {@link Priority} to set.
     */
    public void setPriority(Priority priority) {
        assert priority != null : "priority should not be null";

        this.priority = priority;
    }

    /**
     * Returns {@code true} if the task is completed.
     *
     * @return If the task is completed.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns {@code true} if {@link #priority} is {@link Priority#HIGH}.
     *
     * @return if the task is high priority.
     */
    public boolean isHighPriority() {
        return priority == Priority.HIGH;
    }


    /**
     * Returns the status icon for the task; "X" if completed, else " ".
     *
     * @return Status icon {@link String}.
     */
    private String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns a label corresponding to the {@link #priority} of the task. If {@link Priority#LOW} return the
     * empty String.
     *
     * @return a {@link String} label corresponding to the priority of the task.
     */
    private String getPriorityLabel() {
        assert priority != null : "priority should not be null";

        return switch (priority) {
            // CHECKSTYLE.OFF: Indentation
            case HIGH -> "(HIGH PRIORITY)";
            case LOW -> "";
            // CHECKSTYLE.ON: Indentation
        };
    }

    /**
     * Sets the task as being completed.
     *
     * @see Task#markAsUndone()
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Sets the task as being incomplete.
     *
     * @see Task#markAsDone()
     */
    public void markAsUndone() {
        isDone = false;
    }

    /**
     * Returns {@code true} if the description contains the keyword.
     *
     * @param keyword The {@link String} to look for.
     * @return If the description contains the keyword.
     */
    public boolean hasMatchingDescription(String keyword) {
        return (description.contains(keyword));
    }

    /**
     * Returns the {@link String} representation of the Task.
     *
     * @return A String representation of the task.
     */
    @Override
    public String toString() {
        // formats ekud.task as "[statusIcon] description"
        if (isHighPriority()) {
            return String.format(PRINT_FORMAT_HIGH_PRIORITY, getStatusIcon(), description);
        } else {
            return String.format(PRINT_FORMAT_LOW_PRIORITY, getStatusIcon(), description);
        }
    }
}
