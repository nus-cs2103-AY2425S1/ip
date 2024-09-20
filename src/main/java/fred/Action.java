package fred;

public class Action {
    private Command command;
    private int taskNumber;
    private TaskType taskType;
    private String taskDetails;
    private String keyword;
    private String tag;

    public Action(Command command) {
        this.command = command;
    }

    public Action(Command command, int taskNumber) {
        this.command = command;
        this.taskNumber = taskNumber;
    }

    public Action(Command command, TaskType taskType, String taskDetails) {
        this.command = command;
        this.taskType = taskType;
        this.taskDetails = taskDetails;
    }

    public Action(Command command, String keyword) {
        this.command = command;
        this.keyword = keyword;
    }

    public Action(Command command, int taskNumber, String tag) {
        this.command = command;
        this.taskNumber = taskNumber;
        this.tag = tag;
    }

    public Command getCommand() {
        return command;
    }

    public int getTaskNumber() {
        return taskNumber;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public String getTaskDetails() {
        return taskDetails;
    }

    public String getKeyword() {
        return keyword;
    }

    public String getTag() {
        return tag;
    }
}

