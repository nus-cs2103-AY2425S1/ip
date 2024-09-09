package joe;

public final class Constants {
    private Constants() {
    }

    // Commands
    public static final String EXIT_COMMAND = "bye";
    public static final String LIST_COMMAND = "list";
    public static final String TASK_MARK_COMPLETE_COMMAND = "mark";
    public static final String TASK_UNMARK_COMPLETE_COMMAND = "unmark";
    public static final String TASK_DELETE_COMMAND = "delete";
    public static final String TASK_TODO_COMMAND = "todo";
    public static final String TASK_DEADLINE_COMMAND = "deadline";
    public static final String TASK_EVENT_COMMAND = "event";
    public static final String HELP_COMMAND = "help";
    public static final String BY_COMMAND = "/by";
    public static final String FROM_COMMAND = "/from";
    public static final String TO_COMMAND = "/to";
    public static final String FIND_COMMAND = "find";

    // Command lengths for parsing
    public static final int TASK_TODO_COMMAND_LENGTH = TASK_TODO_COMMAND.length();
    public static final int TASK_DEADLINE_COMMAND_LENGTH = TASK_DEADLINE_COMMAND.length();
    public static final int TASK_EVENT_COMMAND_LENGTH = TASK_EVENT_COMMAND.length();
    public static final int BY_COMMAND_LENGTH = BY_COMMAND.length();
    public static final int FROM_COMMAND_LENGTH = FROM_COMMAND.length();
    public static final int TO_COMMAND_LENGTH = TO_COMMAND.length();
    public static final int FIND_COMMAND_LENGTH = FIND_COMMAND.length();

    public static final String COMPLETED_TASK = "[X]";
    public static final String INCOMPLETE_TASK = "[ ]";
    public static final String TASK_TODO = "T";
    public static final String TASK_DEADLINE = "D";
    public static final String TASK_EVENT = "E";
}
