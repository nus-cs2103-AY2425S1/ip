package duke;
/**
 * Helper to parse through a given command.
 */
public class Parser {

    private static final int INDEX_INVALID = -1;
    private static final int INDEX_OFFSET_BY = 4;
    private static final int INDEX_OFFSET_FROM = 6;
    private static final int INDEX_OFFSET_TO = 4;

    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_FIND = "find";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_DATE_SEPARATOR = "/";

    private static final String REPLY_BYE = "Bye. Hope to see you again soon!";
    private static final String REPLY_EMPTY_TASK_DESCRIPTION = "The description of a task cannot be empty.";
    private static final String REPLY_MISSING_DEADLINE = "A deadline needs an end date.";
    private static final String REPLY_MISSING_EVENT_DATE = "An event needs both a start and end date or time.";
    private static final String REPLY_INVALID_FIND_KEYWORDS = "Cannot find an empty string.";
    private static final String REPLY_INVALID_COMMAND = "I don't recognise that command.";

    /**
     * Interprets a given command, and returns interpreted reply from Duck.
     *
     * @param taskList TaskList of existing commands.
     * @param userCommand String command to be interpreted
     * @return String reply from Duck.
     * @throws DuckException if file is corrupted.
     * */
    public static String parseCommand(TaskList taskList, String userCommand) throws DuckException {
        if (checkByeCommand(userCommand)) {
            return executeByeCommand(taskList);

        } else if (checkListCommand(userCommand)) {
            return taskList.getAllTasks();

        } else if (checkMarkCommand(userCommand)) {
            return markTask(taskList, userCommand);

        } else if (checkUnmarkCommand(userCommand)) {
            return unmarkTask(taskList, userCommand);

        } else if (checkDeleteCommand(userCommand)) {
            return deleteTask(taskList, userCommand);

        } else if (checkFindCommand(userCommand)) {
            return findMatchingTasks(taskList, userCommand);

        } else if (checkTodoCommand(userCommand)) {
            return addTodoTask(taskList, userCommand);

        } else if (checkDeadlineCommand(userCommand)) {
            return addDeadlineTask(taskList, userCommand);

        } else if (checkEventCommand(userCommand)) {
            return addEventTask(taskList, userCommand);

        } else {
            return REPLY_INVALID_COMMAND;
        }
    }

    private static boolean checkByeCommand(String userCommand) {
        return userCommand.trim().equalsIgnoreCase(COMMAND_BYE);
    }
    private static boolean checkListCommand(String userCommand) {
        return userCommand.trim().equalsIgnoreCase(COMMAND_LIST);
    }

    private static boolean checkMarkCommand(String userCommand) {
        boolean isCorrectLength = (userCommand.length() >= COMMAND_MARK.length());
        boolean hasMarkCommand = (userCommand.substring(0, COMMAND_MARK.length()).equalsIgnoreCase(COMMAND_MARK));
        return isCorrectLength && hasMarkCommand;
    }

    private static boolean checkUnmarkCommand(String userCommand) {
        boolean isCorrectLength = (userCommand.length() >= COMMAND_UNMARK.length());
        boolean hasUnmarkCommand = (userCommand.substring(0, COMMAND_UNMARK.length()).equalsIgnoreCase(COMMAND_UNMARK));
        return isCorrectLength && hasUnmarkCommand;
    }

    private static boolean checkDeleteCommand(String userCommand) {
        boolean isCorrectLength = (userCommand.length() >= COMMAND_DELETE.length());
        boolean hasDeleteCommand = (userCommand.substring(0, COMMAND_DELETE.length()).equalsIgnoreCase(COMMAND_DELETE));
        return isCorrectLength && hasDeleteCommand;
    }

    private static boolean checkFindCommand(String userCommand) {
        boolean isCorrectLength = (userCommand.length() >= COMMAND_FIND.length());
        boolean hasFindCommand = (userCommand.substring(0, COMMAND_FIND.length()).equalsIgnoreCase(COMMAND_FIND));
        return isCorrectLength && hasFindCommand;
    }

    private static boolean checkTodoCommand(String userCommand) {
        boolean isCorrectLength = (userCommand.length() >= COMMAND_TODO.length());
        boolean hasTodoCommand = (userCommand.substring(0, COMMAND_TODO.length()).equalsIgnoreCase(COMMAND_TODO));
        return isCorrectLength && hasTodoCommand;
    }

    private static boolean checkDeadlineCommand(String userCommand) {
        boolean isCorrectLength = (userCommand.length() >= COMMAND_DEADLINE.length());
        boolean hasDeadlineCommand = (userCommand.substring(0, COMMAND_DEADLINE.length()).equalsIgnoreCase(COMMAND_DEADLINE));
        return isCorrectLength && hasDeadlineCommand;
    }

    private static boolean checkEventCommand(String userCommand) {
        boolean isCorrectLength = (userCommand.length() >= COMMAND_EVENT.length());
        boolean hasEventCommand = (userCommand.substring(0, COMMAND_EVENT.length()).equalsIgnoreCase(COMMAND_EVENT));
        return isCorrectLength && hasEventCommand;
    }

    private static String executeByeCommand(TaskList taskList) throws DuckException {
        taskList.save();
        return REPLY_BYE;
    }
    private static String markTask(TaskList taskList, String userCommand) {
        int taskIndex = Integer.parseInt(userCommand.substring(COMMAND_MARK.length() + 1));
        return taskList.mark(taskIndex);
    }

    private static String unmarkTask(TaskList taskList, String userCommand) {
        int taskIndex = Integer.parseInt(userCommand.substring(COMMAND_UNMARK.length() + 1));
        return taskList.unmark(taskIndex);
    }

    private static String deleteTask(TaskList taskList, String userCommand) {
        int taskIndex = Integer.valueOf(userCommand.substring(COMMAND_DELETE.length() + 1));
        return taskList.delete(taskIndex);
    }

    private static String addTodoTask(TaskList taskList, String userCommand) {
        if (userCommand.length() == COMMAND_TODO.length()) {
            return REPLY_EMPTY_TASK_DESCRIPTION;
        }
        return taskList.add(new Todo(userCommand.substring(COMMAND_TODO.length() + 1)));
    }

    private static String addDeadlineTask(TaskList taskList, String userCommand) {
        if (userCommand.length() == COMMAND_DEADLINE.length()) {
            return REPLY_EMPTY_TASK_DESCRIPTION;
        }
        String taskDetails = userCommand.substring(COMMAND_TODO.length() + 1);
        int dateIndex = taskDetails.indexOf(COMMAND_DATE_SEPARATOR);
        if ((dateIndex == INDEX_INVALID) || (taskDetails.substring(dateIndex + INDEX_OFFSET_BY).isEmpty())) {
            return REPLY_MISSING_DEADLINE;
        }
        return taskList.add(new Deadline(taskDetails.substring(0, dateIndex),
                taskDetails.substring(dateIndex + INDEX_OFFSET_BY)));
    }

    private static String addEventTask(TaskList taskList, String userCommand) {
        if (userCommand.length() == COMMAND_EVENT.length()) {
            return REPLY_EMPTY_TASK_DESCRIPTION;
        }
        String taskDetails = userCommand.substring(COMMAND_EVENT.length() + 1);
        int start = taskDetails.indexOf(COMMAND_DATE_SEPARATOR);
        int end = taskDetails.substring(start + 1).indexOf(COMMAND_DATE_SEPARATOR);
        if ((start == INDEX_INVALID) || (end == INDEX_INVALID)) {
            return REPLY_MISSING_EVENT_DATE;
        }
        return taskList.add(new Event(taskDetails.substring(0, start),
                taskDetails.substring(start + INDEX_OFFSET_FROM, start + end),
                taskDetails.substring(start + 1 + end + INDEX_OFFSET_TO)));
    }

    private static String findMatchingTasks(TaskList taskList, String userCommand){
        if (userCommand.length() == COMMAND_FIND.length()) {
            return REPLY_INVALID_FIND_KEYWORDS;
        }
        return taskList.find(userCommand.substring(COMMAND_FIND.length() + 1));
    }


}
