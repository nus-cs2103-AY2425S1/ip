package fred;

/**
 * Represents an action with various attributes such as command, task number, task type, details, keyword, and tag.
 */
public class Action {
    private Command command;
    private int taskNumber;
    private TaskType taskType;
    private String taskDetails;
    private String keyword;
    private String tag;

    /**
     * Constructs an Action with a specified command.
     *
     * @param command the command for this action
     */
    public Action(Command command) {
        this.command = command;
    }

    /**
     * Constructs an Action with a specified command and task number.
     *
     * @param command    the command for this action
     * @param taskNumber the task number associated with this action
     */
    public Action(Command command, int taskNumber) {
        this.command = command;
        this.taskNumber = taskNumber;
    }

    /**
     * Constructs an Action with a specified command, task type, and task details.
     *
     * @param command     the command for this action
     * @param taskType    the type of task associated with this action
     * @param taskDetails the details of the task
     */
    public Action(Command command, TaskType taskType, String taskDetails) {
        this.command = command;
        this.taskType = taskType;
        this.taskDetails = taskDetails;
    }

    /**
     * Constructs an Action with a specified command and keyword.
     *
     * @param command the command for this action
     * @param keyword the keyword associated with this action
     */
    public Action(Command command, String keyword) {
        this.command = command;
        this.keyword = keyword;
    }

    /**
     * Constructs an Action with a specified command, task number, and tag.
     *
     * @param command    the command for this action
     * @param taskNumber the task number associated with this action
     * @param tag        the tag associated with this action
     */
    public Action(Command command, int taskNumber, String tag) {
        this.command = command;
        this.taskNumber = taskNumber;
        this.tag = tag;
    }

    /**
     * Returns the command associated with this action.
     *
     * @return the command for this action
     */
    public Command getCommand() {
        return command;
    }

    /**
     * Returns the task number associated with this action.
     *
     * @return the task number for this action
     */
    public int getTaskNumber() {
        return taskNumber;
    }

    /**
     * Returns the task type associated with this action.
     *
     * @return the task type for this action
     */
    public TaskType getTaskType() {
        return taskType;
    }

    /**
     * Returns the task details associated with this action.
     *
     * @return the task details for this action
     */
    public String getTaskDetails() {
        return taskDetails;
    }

    /**
     * Returns the keyword associated with this action.
     *
     * @return the keyword for this action
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * Returns the tag associated with this action.
     *
     * @return the tag for this action
     */
    public String getTag() {
        return tag;
    }
}
