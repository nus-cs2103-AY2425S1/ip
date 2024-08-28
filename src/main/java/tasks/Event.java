package tasks;

import exceptions.YappingBotInvalidSaveFileException;

import static stringconstants.ReplyTextMessages.INVALID_SAVE_FILE_EXCEPTION_MISSING_VALUES;

public class Event extends Task {
    // todo: use java LocalDateTime
    private String startTime;
    private String endTime;

    public Event(String taskName, boolean taskDone) {
        super(taskName, taskDone);
        super.setTaskType(TaskTypes.EVENT);
        this.startTime = "None";
        this.endTime = "None";
    }
    public Event(String taskName, boolean taskDone, String startTime, String endTime) {
        super(taskName, taskDone);
        super.setTaskType(TaskTypes.DEADLINE);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public String getTaskTypeSymbol() {
        return "E";
    }

    @Override
    public String toString() {
        return String.format("%s (from: %s to: %s)", super.getTaskName(), this.startTime, this.endTime);
    }

    @Override
    public String serialiaze() {
        return String.format("%s:%s:%s",
                super.serialiaze(),
                startTime.replaceAll(":", "/colon"),
                endTime.replaceAll(":", "/colon")
        );
    }
    @Override
    public String[] deserialiaze(String sString) throws YappingBotInvalidSaveFileException {
        String[] split_data = super.deserialiaze(sString);
        if (split_data.length != 5) {
            throw new YappingBotInvalidSaveFileException(INVALID_SAVE_FILE_EXCEPTION_MISSING_VALUES);
        }
        try {
            startTime = split_data[3].replaceAll("/colon", ":");
            endTime = split_data[4].replaceAll("/colon", ":");
        } catch (IllegalArgumentException e) {
            throw new YappingBotInvalidSaveFileException(e.getMessage());
        }
        return split_data;
    }
}
