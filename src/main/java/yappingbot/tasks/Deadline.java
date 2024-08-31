package yappingbot.tasks;

import static yappingbot.stringconstants.ReplyTextMessages.INVALID_SAVE_FILE_EXCEPTION_MISSING_VALUES;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import yappingbot.exceptions.YappingBotIncorrectCommandException;
import yappingbot.exceptions.YappingBotInvalidSaveFileException;
import yappingbot.stringconstants.ReplyTextMessages;

public class Deadline extends Task {
    private LocalDate deadline;

    public Deadline() {
        this("", false);
    }

    public Deadline(String taskName, boolean taskDone) {
        super(taskName, taskDone);
        super.setTaskType(TaskTypes.DEADLINE);
        this.deadline = LocalDate.now();
    }

    public Deadline(String taskName, boolean taskDone, String deadline)
    throws YappingBotIncorrectCommandException {
        super(taskName, taskDone);
        super.setTaskType(TaskTypes.DEADLINE);
        this.setDeadline(deadline);
    }

    public String getDeadline() {
        return deadline.toString();
    }

    public void setDeadline(String deadline) throws YappingBotIncorrectCommandException {
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
        return String.format(
                "%s (by: %s)",
                super.getTaskName(),
                this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    @Override
    public String serialize() {
        return String.format("%s:%s",
                super.serialize(),
                this.getDeadline().replaceAll(":", "/colon"));
    }

    @Override
    public void deserialize(String[] stringDataSlices) throws YappingBotInvalidSaveFileException {
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
}

