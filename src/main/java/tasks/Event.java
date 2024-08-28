package tasks;

import exceptions.YappingBotIncorrectCommandException;
import exceptions.YappingBotInvalidSaveFileException;
import stringconstants.ReplyTextMessages;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Event(String taskName, boolean taskDone) {
        super(taskName, taskDone);
        super.setTaskType(TaskTypes.EVENT);
        this.startTime = LocalDateTime.now();
        this.endTime = LocalDateTime.now();
    }
    public Event(String taskName, boolean taskDone, String startTime, String endTime) throws YappingBotIncorrectCommandException  {
        super(taskName, taskDone);
        super.setTaskType(TaskTypes.EVENT);
        this.setStartTime(startTime);
        this.setEndTime(endTime);
    }

    public Event() {
        this("untitled",false);
    }

    public String getStartTime() {
        return startTime.toString();
    }

    public void setStartTime(String startTime) throws YappingBotIncorrectCommandException {
        try {
            this.startTime = LocalDateTime.parse(startTime);
        } catch (DateTimeParseException e) {
            throw new YappingBotIncorrectCommandException(ReplyTextMessages.TIME_PARSE_HINT, e.getMessage());
        }
    }

    public String getEndTime() {
        return endTime.toString();
    }

    public void setEndTime(String endTime) throws YappingBotIncorrectCommandException {
        try {
            this.endTime = LocalDateTime.parse(endTime);
        } catch (DateTimeParseException e) {
            throw new YappingBotIncorrectCommandException(ReplyTextMessages.TIME_PARSE_HINT, e.getMessage());
        }
    }

    @Override
    public String getTaskTypeSymbol() {
        return "E";
    }

    @Override
    public String toString() {
        return String.format("%s (from: %s to: %s)",
                super.getTaskName(),
                this.startTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                this.endTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
        );
    }

    @Override
    public String serialize() {
        return String.format("%s:%s:%s",
                super.serialize(),
                this.getStartTime().replaceAll(":", "/colon"),
                this.getEndTime().replaceAll(":", "/colon")
        );
    }
    @Override
    public void deserialize(String[] sString) throws YappingBotInvalidSaveFileException {
        if (sString.length < 5) {
            throw new YappingBotInvalidSaveFileException(ReplyTextMessages.INVALID_SAVE_FILE_EXCEPTION_MISSING_VALUES);
        }
        try {
            super.deserialize(sString);
            this.setStartTime(sString[3].replaceAll("/colon", ":"));
            this.setEndTime(sString[4].replaceAll("/colon", ":"));
        } catch (IllegalArgumentException e) {
            throw new YappingBotInvalidSaveFileException(e.getMessage());
        }
    }
}
