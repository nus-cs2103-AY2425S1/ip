package yappingbot.tasks;

import static yappingbot.stringconstants.ReplyTextMessages.INVALID_SAVE_FILE_EXCEPTION_MISSING_VALUES;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import yappingbot.exceptions.YappingBotIncorrectCommandException;
import yappingbot.exceptions.YappingBotInvalidSaveFileException;
import yappingbot.stringconstants.ReplyTextMessages;
import yappingbot.tasks.tasklist.TaskTypes;


/**
 * Deadline variant of the Task that can be created.
 * Includes task name, date that task must be completed, and if task is marked done.
 */
public class Deadline extends Task {
    private LocalDate deadline;

    /**
     * Constructor for a blank Deadline task (Task name "Untitled", unmarked).
     */
    public Deadline() {
        this("Untitled", false);
    }

    /**
     * Creates a Deadline task.
     * Deadline date will default to the current local date of creation.
     *
     * @param taskName String name of this task.
     * @param taskDone Boolean of whether the task is marked or unmarked as done.
     */
    public Deadline(String taskName, boolean taskDone) {
        super(taskName, taskDone);
        super.setTaskType(TaskTypes.DEADLINE);
        this.deadline = LocalDate.now();
    }

    /**
     * Creates a Deadline task.
     *
     * @param taskName String name of this task.
     * @param taskDone Boolean of whether the task is marked or unmarked as done.
     * @param deadline String of the deadline, in format "YYYY-MM-DD".
     */
    public Deadline(String taskName, boolean taskDone, String deadline)
    throws YappingBotIncorrectCommandException {
        super(taskName, taskDone);
        super.setTaskType(TaskTypes.DEADLINE);

        assert deadline != null;
        this.setDeadline(deadline);
    }

    /**
     * Returns deadline of task.
     *
     * @return String deadline in "YYYY-MM-DD" format
     */
    public String getDeadline() {
        assert deadline != null;
        return deadline.toString();
    }

    /**
     * Sets the deadline.
     * String provided must be of format "YYYY-MM-DD".
     *
     * @param deadline String of the deadline in "YYYY-MM-DD" format
     * @throws YappingBotIncorrectCommandException If the provided date String is not valid format.
     */
    public void setDeadline(String deadline) throws YappingBotIncorrectCommandException {
        assert deadline != null;
        try {
            this.deadline = LocalDate.parse(deadline);
        } catch (DateTimeParseException e) {
            throw new YappingBotIncorrectCommandException(
                    ReplyTextMessages.TIME_PARSE_HINT,
                    e.getMessage());
        }
    }



    @Override
    public String getTaskTypeSymbol() {
        return "D";
    }

    @Override
    public String toString() {
        assert deadline != null;
        return String.format(
                "%s (by: %s)",
                super.getTaskName(),
                this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    @Override
    public String serialize() {
        assert deadline != null;
        return String.format("%s:%s",
                super.serialize(),
                this.getDeadline().replaceAll(":", "/colon"));
    }

    @Override
    public void deserialize(String[] stringDataSlices) throws YappingBotInvalidSaveFileException {
        assert stringDataSlices != null;
        if (stringDataSlices.length < 4) {
            throw new YappingBotInvalidSaveFileException(
                    INVALID_SAVE_FILE_EXCEPTION_MISSING_VALUES);
        }
        try {
            super.deserialize(stringDataSlices);
            this.setDeadline(stringDataSlices[3].replaceAll("/colon", ":"));
        } catch (IllegalArgumentException e) {
            throw new YappingBotInvalidSaveFileException(e.getMessage());
        }
    }

    @Override
    public boolean isStringFoundInTask(String searchString) {
        // abuse the shortcircuiting
        return (super.isStringFoundInTask(searchString)
                || getDeadline().contains(searchString));
    }
}

