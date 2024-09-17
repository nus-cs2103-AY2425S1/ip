package yappingbot.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import yappingbot.exceptions.YappingBotIncorrectCommandException;
import yappingbot.exceptions.YappingBotInvalidSaveFileException;
import yappingbot.stringconstants.ReplyTextMessages;
import yappingbot.tasks.tasklist.TaskTypes;


/**
 * Event variant of the Task that can be created.
 * Includes task name, beginning date of event, ending date of event, and if task is marked done.
 */
public class Event extends Task {
    private LocalDate startTime;
    private LocalDate endTime;

    /**
     * Constructor for a blank Event task (Task name "Untitled", unmarked).
     */
    public Event() {
        this("Untitled", false);
    }

    /**
     * Creates an Event task.
     * Event start and end date will default to the current local date of creation.
     *
     * @param taskName String name of this task.
     * @param taskDone Boolean of whether the task is marked or unmarked as done.
     */
    public Event(String taskName, boolean taskDone) {
        super(taskName, taskDone);
        super.setTaskType(TaskTypes.EVENT);
        this.startTime = LocalDate.now();
        this.endTime = LocalDate.now();
    }

    /**
     * Creates a Event task.
     *
     * @param taskName String name of this task.
     * @param taskDone Boolean of whether the task is marked or unmarked as done.
     * @param startTime String of the start date, in format "YYYY-MM-DD".
     * @param endTime String of the end date, in format "YYYY-MM-DD".
     */
    public Event(String taskName, boolean taskDone, String startTime, String endTime)
    throws YappingBotIncorrectCommandException  {
        super(taskName, taskDone);
        super.setTaskType(TaskTypes.EVENT);

        assert startTime != null;
        assert endTime != null;
        this.setStartTime(startTime);
        this.setEndTime(endTime);
    }

    /**
     * Returns starting date of event.
     *
     * @return starting date as String formatted "YYYY-MM-DD".
     */
    public String getStartTime() {
        assert startTime != null;
        return startTime.toString();
    }

    /**
     * Sets the starting date.
     * String provided must be of format "YYYY-MM-DD".
     *
     * @param startTime String of the starting date in "YYYY-MM-DD" format
     * @throws YappingBotIncorrectCommandException If the provided date String is not valid format.
     */
    public void setStartTime(String startTime) throws YappingBotIncorrectCommandException {
        assert startTime != null;
        try {
            this.startTime = LocalDate.parse(startTime);
        } catch (DateTimeParseException e) {
            throw new YappingBotIncorrectCommandException(
                    ReplyTextMessages.TIME_PARSE_HINT, e.getMessage()
            );
        }
    }

    /**
     * Returns ending date of event.
     *
     * @return ending date as String formatted "YYYY-MM-DD".
     */
    public String getEndTime() {
        assert endTime != null;
        return endTime.toString();
    }

    /**
     * Sets the ending date.
     * String provided must be of format "YYYY-MM-DD".
     *
     * @param endTime String of the ending date in "YYYY-MM-DD" format
     * @throws YappingBotIncorrectCommandException If the provided date String is not valid format.
     */
    public void setEndTime(String endTime) throws YappingBotIncorrectCommandException {
        assert endTime != null;
        try {
            this.endTime = LocalDate.parse(endTime);
        } catch (DateTimeParseException e) {
            throw new YappingBotIncorrectCommandException(
                    ReplyTextMessages.TIME_PARSE_HINT, e.getMessage()
            );
        }
    }

    @Override
    public String getTaskTypeSymbol() {
        return "E";
    }

    @Override
    public String toString() {
        assert getTaskName() != null;
        assert startTime != null;
        assert endTime != null;
        return String.format("%s (from: %s to: %s)",
                super.getTaskName(),
                this.startTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                this.endTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
        );
    }

    @Override
    public String serialize() {
        assert startTime != null;
        assert endTime != null;
        return String.format("%s:%s:%s",
                super.serialize(),
                this.getStartTime().replaceAll(":", "/colon"),
                this.getEndTime().replaceAll(":", "/colon")
        );
    }

    @Override
    public void deserialize(String[] stringDataSlices) throws YappingBotInvalidSaveFileException {
        assert stringDataSlices != null;
        if (stringDataSlices.length < 5) {
            throw new YappingBotInvalidSaveFileException(
                    ReplyTextMessages.INVALID_SAVE_FILE_EXCEPTION_MISSING_VALUES
            );
        }
        try {
            super.deserialize(stringDataSlices);
            this.setStartTime(stringDataSlices[3].replaceAll("/colon", ":"));
            this.setEndTime(stringDataSlices[4].replaceAll("/colon", ":"));
        } catch (IllegalArgumentException e) {
            throw new YappingBotInvalidSaveFileException(e.getMessage());
        }
    }

    @Override
    public boolean isStringFoundInTask(String searchString) {
        assert searchString != null;
        // abuse the shortcircuiting
        return (super.isStringFoundInTask(searchString)
                || getStartTime().contains(searchString)
                || getEndTime().contains(searchString));
    }
}
