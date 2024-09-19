package duke;

import java.time.format.DateTimeParseException;
import java.time.LocalDate;

/**
 * Helps parse through a given command.
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
    private static final String COMMAND_UNDO = "undo";
    private static final String COMMAND_DATE_SEPARATOR = "/";

    private static final String REPLY_BYE = "Bye. Hope to see you again soon!";
    private static final String REPLY_INVALID_TASK_NUMBER = "Invalid task number given.";
    private static final String REPLY_MISSING_TASK_NUMBER = "This command requires a task number to execute.";
    private static final String REPLY_EMPTY_TASK_DESCRIPTION = "The description of a task cannot be empty.";
    private static final String REPLY_MISSING_DEADLINE_DETAILS = "A deadline needs a description and "
            + "a date in YYYY-MM-DD format.";
    private static final String REPLY_MISSING_EVENT_DETAILS = "An event needs a description, and "
            + "both a start and end date or time in YYYY-MM-DD format.";
    private static final String REPLY_INVALID_EVENT_DATES = "The start date of an event must be "
            + "earlier than it's end date.";
    private static final String REPLY_INVALID_FIND_KEYWORDS = "Cannot find an empty string.";
    private static final String REPLY_INVALID_COMMAND = "I don't recognise that command.";
    private static final String REPLY_NO_PREVIOUS_COMMAND = "There is no previous command to undo.";

    /**
     * Interprets a given command, and returns interpreted reply from Duck.
     *
     * @param taskList TaskList of existing commands.
     * @param userCommand String command to be interpreted.
     * @return String reply from Duck.
     * @throws DuckException if file is corrupted.
     * */
    public static String parseCommand(TaskList taskList, String userCommand) throws DuckException {
        assert !userCommand.isEmpty() : "Command cannot be empty";

        if (isByeCommand(userCommand)) {
            return executeByeCommand(taskList);

        } else if (isListCommand(userCommand)) {
            return taskList.listAllTasks();

        } else if (isMarkCommand(userCommand)) {
            return markTask(taskList, userCommand);

        } else if (isUnmarkCommand(userCommand)) {
            return unmarkTask(taskList, userCommand);

        } else if (isDeleteCommand(userCommand)) {
            return deleteTask(taskList, userCommand);

        } else if (isFindCommand(userCommand)) {
            return findMatchingTasks(taskList, userCommand);

        } else if (isTodoCommand(userCommand)) {
            return addTodoTask(taskList, userCommand);

        } else if (isDeadlineCommand(userCommand)) {
            return addDeadlineTask(taskList, userCommand);

        } else if (isEventCommand(userCommand)) {
            return addEventTask(taskList, userCommand);

        } else if (isUndoCommand(userCommand)) {
            return undoLastCommand();

        } else {
            return REPLY_INVALID_COMMAND;
        }
    }

    /**
     * Checks if the user command is a 'bye' command.
     *
     * @param userCommand String command to be interpreted.
     * @return boolean representing if command is a 'bye' command.
     * */
    private static boolean isByeCommand(String userCommand) {
        return userCommand.equalsIgnoreCase(COMMAND_BYE);
    }

    /**
     * Checks if the user command is a 'list' command.
     *
     * @param userCommand String command to be interpreted.
     * @return boolean representing if command is a 'list' command.
     * */
    private static boolean isListCommand(String userCommand) {
        return userCommand.equalsIgnoreCase(COMMAND_LIST);
    }

    /**
     * Checks if the user command is a 'mark' command.
     *
     * @param userCommand String command to be interpreted.
     * @return boolean representing if command is a 'mark' command.
     * */
    private static boolean isMarkCommand(String userCommand) {
        if (userCommand.length() < COMMAND_MARK.length()) {
            return false;
        }
        return userCommand.substring(0, COMMAND_MARK.length()).equalsIgnoreCase(COMMAND_MARK);
    }

    /**
     * Checks if the user command is a 'unmark' command.
     *
     * @param userCommand String command to be interpreted.
     * @return boolean representing if command is a 'unmark' command.
     * */
    private static boolean isUnmarkCommand(String userCommand) {
        if (userCommand.length() < COMMAND_UNMARK.length()) {
            return false;
        }
        return userCommand.substring(0, COMMAND_UNMARK.length()).equalsIgnoreCase(COMMAND_UNMARK);
    }

    /**
     * Checks if the user command is a 'delete' command.
     *
     * @param userCommand String command to be interpreted.
     * @return boolean representing if command is a 'delete' command.
     * */
    private static boolean isDeleteCommand(String userCommand) {
        if (userCommand.length() < COMMAND_DELETE.length()) {
            return false;
        }
        return userCommand.substring(0, COMMAND_DELETE.length()).equalsIgnoreCase(COMMAND_DELETE);
    }

    /**
     * Checks if the user command is a 'find' command.
     *
     * @param userCommand String command to be interpreted.
     * @return boolean representing if command is a 'find' command.
     * */
    private static boolean isFindCommand(String userCommand) {
        if (userCommand.length() < COMMAND_FIND.length()) {
            return false;
        }
        return userCommand.substring(0, COMMAND_FIND.length()).equalsIgnoreCase(COMMAND_FIND);
    }

    /**
     * Checks if the user command is a 'todo' command.
     *
     * @param userCommand String command to be interpreted.
     * @return boolean representing if command is a 'todo' command.
     * */
    private static boolean isTodoCommand(String userCommand) {
        if (userCommand.length() < COMMAND_TODO.length()) {
            return false;
        }
        return userCommand.substring(0, COMMAND_TODO.length()).equalsIgnoreCase(COMMAND_TODO);
    }

    /**
     * Checks if the user command is a 'deadline' command.
     *
     * @param userCommand String command to be interpreted.
     * @return boolean representing if command is a 'deadline' command.
     * */
    private static boolean isDeadlineCommand(String userCommand) {
        if (userCommand.length() < COMMAND_DEADLINE.length()) {
            return false;
        }
        return userCommand.substring(0, COMMAND_DEADLINE.length()).equalsIgnoreCase(COMMAND_DEADLINE);
    }

    /**
     * Checks if the user command is an 'event' command.
     *
     * @param userCommand String command to be interpreted.
     * @return boolean representing if command is a 'event' command.
     * */
    private static boolean isEventCommand(String userCommand) {
        if (userCommand.length() < COMMAND_EVENT.length()) {
            return false;
        }
        return userCommand.substring(0, COMMAND_EVENT.length()).equalsIgnoreCase(COMMAND_EVENT);
    }

    /**
     * Checks if the user command is an 'undo' command.
     *
     * @param userCommand String command to be interpreted.
     * @return boolean representing if command is an 'undo' command.
     * */
    private static boolean isUndoCommand(String userCommand) {
        if (userCommand.length() < COMMAND_UNDO.length()) {
            return false;
        }
        return userCommand.substring(0, COMMAND_UNDO.length()).equalsIgnoreCase(COMMAND_UNDO);
    }

    /**
     * Executes 'bye' command by saving the current task list.
     *
     * @param taskList Current task list to be saved.
     * @return String response to inform user of successful 'bye' command executed.
     * */
    private static String executeByeCommand(TaskList taskList) throws DuckException {
        taskList.save();
        return REPLY_BYE;
    }

    /**
     * Marks the task at the index specified by the user.
     *
     * @param taskList Current task list.
     * @param userCommand String command from user.
     * @return String response to inform user of successful 'mark' command executed.
     * */
    private static String markTask(TaskList taskList, String userCommand) {
        if (userCommand.length() == COMMAND_MARK.length()) {
            return REPLY_MISSING_TASK_NUMBER;
        }

        int taskIndex = INDEX_INVALID;

        try {
            String taskIndexInStringFormat = userCommand.substring(COMMAND_MARK.length() + 1).trim();
            taskIndex = Integer.parseInt(taskIndexInStringFormat);
        } catch (NumberFormatException exception) {
            return REPLY_INVALID_TASK_NUMBER;
        }

        if ((taskIndex <= 0) || (taskIndex > taskList.getNumberOfTasks())) {
            return REPLY_INVALID_TASK_NUMBER;
        }

        Undo.saveCommand(COMMAND_MARK, taskIndex);
        return taskList.mark(taskIndex);
    }

    /**
     * Unmarks the task at the index specified by the user.
     *
     * @param taskList Current task list.
     * @param userCommand String command from user.
     * @return String response to inform user of successful 'unmark' command executed.
     * */
    private static String unmarkTask(TaskList taskList, String userCommand) {
        if (userCommand.length() == COMMAND_UNMARK.length()) {
            return REPLY_MISSING_TASK_NUMBER;
        }

        int taskIndex = INDEX_INVALID;

        try {
            String taskIndexInStringFormat = userCommand.substring(COMMAND_UNMARK.length() + 1).trim();
            taskIndex = Integer.parseInt(taskIndexInStringFormat);
        } catch (NumberFormatException exception) {
            return REPLY_INVALID_TASK_NUMBER;
        }

        if ((taskIndex <= 0) || (taskIndex > taskList.getNumberOfTasks())) {
            return REPLY_INVALID_TASK_NUMBER;
        }

        Undo.saveCommand(COMMAND_UNMARK, taskIndex);
        return taskList.unmark(taskIndex);
    }

    /**
     * Deletes the task at the index specified by the user.
     *
     * @param taskList Current task list.
     * @param userCommand String command from user.
     * @return String response to inform user of successful 'delete' command executed.
     * */
    private static String deleteTask(TaskList taskList, String userCommand) {
        if (userCommand.length() == COMMAND_DELETE.length()) {
            return REPLY_MISSING_TASK_NUMBER;
        }

        int taskIndex = INDEX_INVALID;

        try {
            String taskIndexInStringFormat = userCommand.substring(COMMAND_DELETE.length() + 1).trim();
            taskIndex = Integer.parseInt(taskIndexInStringFormat);
        } catch (NumberFormatException exception) {
            return REPLY_INVALID_TASK_NUMBER;
        }

        if ((taskIndex <= 0) || (taskIndex > taskList.getNumberOfTasks())) {
            return REPLY_INVALID_TASK_NUMBER;
        }

        Undo.saveCommand(COMMAND_DELETE, taskIndex);
        Undo.saveTask(taskList.get(taskIndex - 1));
        return taskList.delete(taskIndex);
    }

    /**
     * Adds new Todo task to the task list.
     *
     * @param taskList Current task list.
     * @param userCommand String command from user.
     * @return String response to inform user of successful addition of Todo task.
     * */
    private static String addTodoTask(TaskList taskList, String userCommand) {
        if (userCommand.length() == COMMAND_TODO.length()) {
            return REPLY_EMPTY_TASK_DESCRIPTION;
        }

        Undo.saveCommand(COMMAND_TODO, taskList.getNumberOfTasks() + 1);
        return taskList.add(new Todo(userCommand.substring(COMMAND_TODO.length() + 1)));
    }

    /**
     * Adds new Deadline task to the task list.
     *
     * @param taskList Current task list.
     * @param userCommand String command from user.
     * @return String response to inform user of successful addition of Deadline task.
     * */
    private static String addDeadlineTask(TaskList taskList, String userCommand) {
        if (userCommand.length() == COMMAND_DEADLINE.length()) {
            return REPLY_EMPTY_TASK_DESCRIPTION;
        }

        String taskDetails = userCommand.substring(COMMAND_DEADLINE.length() + 1);
        int dateIndex = taskDetails.indexOf(COMMAND_DATE_SEPARATOR);

        if ((dateIndex == 0) || (dateIndex == INDEX_INVALID)
                || (taskDetails.substring(dateIndex + INDEX_OFFSET_BY).isEmpty())) {
            return REPLY_MISSING_DEADLINE_DETAILS;
        }

        String date = taskDetails.substring(dateIndex + INDEX_OFFSET_BY);

        try {
            LocalDate.parse(date);
        } catch (DateTimeParseException exception) {
            return REPLY_MISSING_DEADLINE_DETAILS;
        }

        Undo.saveCommand(COMMAND_DEADLINE, taskList.getNumberOfTasks() + 1);
        return taskList.add(new Deadline(taskDetails.substring(0, dateIndex), date));
    }

    /**
     * Adds new Event task to the task list.
     *
     * @param taskList Current task list.
     * @param userCommand String command from user.
     * @return String response to inform user of successful addition of Event task.
     * */
    private static String addEventTask(TaskList taskList, String userCommand) {
        if (userCommand.length() == COMMAND_EVENT.length()) {
            return REPLY_EMPTY_TASK_DESCRIPTION;
        }

        String taskDetails = userCommand.substring(COMMAND_EVENT.length() + 1);
        int start = taskDetails.indexOf(COMMAND_DATE_SEPARATOR);
        int end = taskDetails.substring(start + 1).indexOf(COMMAND_DATE_SEPARATOR);
        if ((start == 0) || (start == INDEX_INVALID) || (end == INDEX_INVALID)) {
            return REPLY_MISSING_EVENT_DETAILS;
        }

        String startDate = taskDetails.substring(start + INDEX_OFFSET_FROM, start + end).trim();
        String endDate = taskDetails.substring(start + 1 + end + INDEX_OFFSET_TO).trim();

        try {
            LocalDate.parse(startDate);
            LocalDate.parse(endDate);
        } catch (DateTimeParseException exception) {
            return REPLY_MISSING_EVENT_DETAILS;
        }

        if (startDate.compareTo(endDate) > 0) {
            return REPLY_INVALID_EVENT_DATES;
        }

        Undo.saveCommand(COMMAND_EVENT, taskList.getNumberOfTasks() + 1);
        return taskList.add(new Event(taskDetails.substring(0, start), startDate, endDate));
    }

    /**
     * Finds all tasks matching keywords given by the user.
     *
     * @param taskList Current task list.
     * @param userCommand String command from user.
     * @return String response of all existing tasks that match the keywords given by the user.
     * */
    private static String findMatchingTasks(TaskList taskList, String userCommand){
        if (userCommand.length() == COMMAND_FIND.length()) {
            return REPLY_INVALID_FIND_KEYWORDS;
        }
        return taskList.find(userCommand.substring(COMMAND_FIND.length() + 1).trim());
    }

    /**
     * Undoes the last command.
     *
     * @return String response to inform user of successful undoing of last command.
     * */
    private static String undoLastCommand(){
        if (Undo.hasNoPreviousCommand()) {
            return REPLY_NO_PREVIOUS_COMMAND;
        }
        return Undo.undo();
    }


}
