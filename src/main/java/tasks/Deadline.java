package tasks;

import exceptions.YappingBotInvalidSaveFileException;

import static stringconstants.ReplyTextMessages.INVALID_SAVE_FILE_EXCEPTION_MISSING_VALUES;

public class Deadline extends Task {
    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    // todo: use java LocalDateTime
    private String deadline;

    public Deadline(String taskName, boolean taskDone) {
        super(taskName, taskDone);
        super.setTaskType(TaskTypes.DEADLINE);
        this.deadline = "None";
    }
    public Deadline(String taskName, boolean taskDone, String deadline) {
        super(taskName, taskDone);
        super.setTaskType(TaskTypes.DEADLINE);
        this.deadline = deadline;
    }

    @Override
    public String getTaskTypeSymbol() {
        return "D";
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.getTaskName(), this.deadline);
    }

    @Override
    public String serialiaze() {
        return String.format("%s:%s",
                super.serialiaze(),
                deadline.replaceAll(":", "/colon")
        );
    }
    @Override
    public void deserialiaze(String[] sString) throws YappingBotInvalidSaveFileException {
        if (sString.length != 5) {
            throw new YappingBotInvalidSaveFileException(INVALID_SAVE_FILE_EXCEPTION_MISSING_VALUES);
        }
        try {
            deadline = sString[3].replaceAll("/colon", ":");
        } catch (IllegalArgumentException e) {
            throw new YappingBotInvalidSaveFileException(e.getMessage());
        }
    }
}

