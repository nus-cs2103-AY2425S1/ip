package ekud.task;

import java.util.HashMap;

import ekud.components.TaskList;
import ekud.exceptions.EkudException;
import ekud.ui.Ui;

/**
 * Represents a task. Each task minimally has a description and completion status.
 *
 * @author uniqly
 */
public abstract class Task {
    /** The description of the task */
    protected String description;

    /** Boolean flag of the completion status */
    protected boolean isDone;

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
            // CHECKSTYLE.OFF: Indentation
            Task task = switch (type) {
                case "T" -> new TodoTask(args[2]);
                case "D" -> new DeadlineTask(args[2], args[3]);
                case "E" -> new EventTask(args[2], args[3], args[4]);
                default -> null;
            };
            // CHECKSTYLE.ON: Indentation

            if (task != null) {
                task.isDone = isDone;
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

        String type = tokens.get("command").toLowerCase();
        String argument = tokens.get("argument");

        // CHECKSTYLE.OFF: Indentation
        return switch (type) {
            case "todo" -> new TodoTask(argument);
            case "deadline" -> new DeadlineTask(argument, tokens.get("/by"));
            case "event" -> new EventTask(argument, tokens.get("/from"), tokens.get("/to"));
            default -> throw new EkudException("Wow! What is this type of ekud.task?"
                    + "\nI'm not sure how to process this");
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
        return String.format("%d | %s", statusInt, description);
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
     * Returns the status icon for the task; "X" if completed, else " ".
     *
     * @return Status icon {@link String}.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
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
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}
