package taskalyn;

/**
 * Manages scanning of input and parsing of commands.
 */
public class Parser {
    private TaskManager taskManager;
    private Ui ui;

    /**
     * Constructs the Parser object with ui and taskmanager.
     *
     * @param ui Ui object to manage user interaction.
     * @param taskManager TaskManager object to manage tasks.
     */
    public Parser(Ui ui, TaskManager taskManager) {
        this.ui = ui;
        this.taskManager = taskManager;
    }

    /**
     * Parses user input commands and handles task management.
     *
     * @param taskManager TaskManager object to manage tasks.
     * @return String stating the reply from Taskalyn.
     */
    public String parse(TaskManager taskManager, String input) {
        while (true) {
            String[] items = input.split(" ", 2);
            String command = items[0];

            try {
                switch (command) {
                case "bye":
                    return handleByeCommand();
                case "list":
                    return handleListCommand();
                case "find":
                    return handleFindCommand(items);
                case "delete":
                    return handleDeleteCommand(items);
                case "mark":
                    return handleMarkCommand(items);
                case "unmark":
                    return handleUnmarkCommand(items);
                case "todo":
                    return handleTodoCommand(items);
                case "deadline":
                    return handleDeadlineCommand(items);
                case "event":
                    return handleEventCommand(items);
                default:
                    return "Sorry bro, no clue what you're saying!";
                }
            } catch (NoSuchTaskException | CommandFormatException e) {
                return e.getMessage();
            }
        }
    }

    /**
     * Returns a String with the deadline date.
     *
     * @param items The user input split by whitespace.
     * @return String of the deadline date.
     * @throws CommandFormatException
     */
    private static String[] getDeadlineString(String[] items) throws CommandFormatException {
        String[] deadlineString = items[1].split(" /by ", 2);
        if (deadlineString.length != 2) {
            throw new CommandFormatException("Aw... your deadline command must contain the task, "
                    + "/by, and the deadline.");
        }

        String datePattern = "\\d{4}-\\d{2}-\\d{2} \\d{4}";
        if (!deadlineString[1].matches(datePattern)) {
            throw new CommandFormatException("Aw... the date format must be yyyy-MM-dd HHmm");
        }
        return deadlineString;
    }

    /**
     * Returns the bye message as a String.
     *
     * @return String saying bye.
     */
    private String handleByeCommand() {
        return ui.showByeMessage();
    }

    /**
     * Returns the list of Tasks as a String.
     *
     * @return String containing list of tasks.
     */
    private String handleListCommand() {
        return taskManager.listTasks();
    }

    /**
     * Returns the tasks filtered by keyword.
     *
     * @param items The user input split by whitespace.
     * @return String stating the list of tasks with keyword.
     * @throws CommandFormatException
     */
    private String handleFindCommand(String[] items) throws CommandFormatException {
        if (items.length != 2) {
            throw new CommandFormatException("Aw... find command must have just 2 arguments:"
                    + " the command, and the keyword.");
        } else {
            return taskManager.searchTasksByKeyword(items[1]);
        }
    }

    /**
     * Returns a message String when a task is deleted.
     *
     * @param items The user input split by whitespace.
     * @return String saying that task is deleted.
     * @throws CommandFormatException Command Format is incorrect.
     * @throws NoSuchTaskException No such task exists.
     * @throws IndexOutOfBoundsException The list index is out of bounds.
     */
    private String handleDeleteCommand(String[] items) throws CommandFormatException, NoSuchTaskException,
            IndexOutOfBoundsException {
        if (items.length != 2) {
            throw new CommandFormatException("Aw... delete command must have just 2 arguments: the "
                    + "command, and the task number.");
        }
        try {
            Integer i = Integer.parseInt(items[1]);
            if (i > 0 && i < taskManager.getTaskSize() + 1) {
                return taskManager.deleteTask(i);
            } else {
                throw new NoSuchTaskException("Aw, that task doesn't exist. Try again!");
            }
        } catch (NumberFormatException e) {
            throw new CommandFormatException("Aw... delete command must be followed by an integer");
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchTaskException("Aw, that task doesn't exist. Try again!");
        } catch (Exception e) {
            throw new CommandFormatException("Aw... delete command must have just 2 arguments: the "
                    + "command, and the task number.");
        }
    }

    /**
     * Returns a message String saying that a task has been marked as complete.
     *
     * @param items The user input split by whitespace.
     * @return String saying that task is marked as complete.
     * @throws CommandFormatException Command Format is incorrect.
     * @throws NoSuchTaskException No such task exists.
     * @throws IndexOutOfBoundsException The list index is out of bounds.
     */
    private String handleMarkCommand(String[] items) throws CommandFormatException, NoSuchTaskException,
            IndexOutOfBoundsException {
        if (items.length != 2) {
            throw new CommandFormatException("Aw... mark command must have just 2 arguments: the command,"
                    + " and the task number.");
        }
        try {
            Integer i = Integer.parseInt(items[1]);
            if (i > 0 && i <= taskManager.getTaskSize() + 1) {
                return taskManager.markTaskAsComplete(i);
            } else {
                throw new NoSuchTaskException("Aw, that task doesn't exist. Try again!");
            }
        } catch (NumberFormatException e) {
            throw new CommandFormatException("Aw... mark command must be followed by an integer");
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchTaskException("Aw, that task doesn't exist. Try again!");
        }
    }

    /**
     * Returns a message String saying that a task has been marked as incomplete.
     *
     * @param items The user input split by whitespace.
     * @return String saying that a task has been marked as incomplete.
     * @throws CommandFormatException Command format is incorrect.
     * @throws NoSuchTaskException No such task exists.
     * @throws IndexOutOfBoundsException The list index is out of bounds.
     */
    private String handleUnmarkCommand(String[] items) throws CommandFormatException, NoSuchTaskException,
            IndexOutOfBoundsException {
        if (items.length != 2) {
            throw new CommandFormatException("Aw... unmark command must have 2 arguments: the command and"
                    + " the task number.");
        }
        try {
            Integer i = Integer.parseInt(items[1]);
            if (i > 0 && i <= taskManager.getTaskSize() + 1) {
                return taskManager.markTaskAsIncomplete(i);
            } else {
                throw new NoSuchTaskException("Aw, that task doesn't exist. Try again!");
            }
        } catch (NumberFormatException e) {
            throw new CommandFormatException("Aw... unmark command must be followed by an integer");
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchTaskException("Aw, that task doesn't exist. Try again!");
        }
    }

    /**
     * Returns a message String saying that the TodoTask has been added.
     *
     * @param items The user input split by whitespace.
     * @return String saying TodoTask has been added.
     * @throws CommandFormatException Command format is incorrect.
     */
    private String handleTodoCommand(String[] items) throws CommandFormatException {
        if (items.length != 2) {
            throw new CommandFormatException("Aw... todo command must contain 2 arguments: todo and the "
                    + "task at hand!");
        }
        if (items[1].equals("")) {
            throw new CommandFormatException("Aw... task description cannot be empty!");
        }
        try {
            return taskManager.addTask(new TodoTask(items[1], false));
        } catch (Exception e) {
            throw new CommandFormatException("Aw... todo command must contain 2 arguments: todo and the "
                    + "task at hand!");
        }
    }

    /**
     * Returns a message String saying that the DeadlineTask has been added.
     *
     * @param items The user input split by whitespace.
     * @return String saying that DeadlineTask has been added.
     * @throws CommandFormatException Command format is incorrect.
     */
    private String handleDeadlineCommand(String[] items) throws CommandFormatException {
        if (items.length != 2) {
            throw new CommandFormatException("Aw... your deadline command is incomplete. Try this: "
                    + "deadline {task} /by {yyyy-MM-dd HHmm}");
        }
        try {
            if (!items[1].contains("/by")) {
                throw new CommandFormatException("Aw... your deadline command doesn't have a deadline date set!");
            }

            String[] deadlineString = getDeadlineString(items);
            return taskManager.addTask(new DeadlineTask(deadlineString[0], deadlineString[1], false));
        } catch (Exception e) {
            throw new CommandFormatException("Aw... your deadline command is incorrect. Try this: "
                    + "deadline {task} /by {yyyy-MM-dd HHmm}");
        }
    }

    /**
     * Returns a message String saying that EventTask has been added.
     *
     * @param items The user input split by whitespace.
     * @return String saying that EventTask has been added.
     * @throws CommandFormatException Command format is incorrect.
     */
    private String handleEventCommand(String[] items) throws CommandFormatException {
        if (items.length != 2) {
            throw new CommandFormatException("Aw your event command is incomplete. "
                    + "Try this: event {event} /from {from} /to {to}");
        }
        try {
            if (!items[1].contains("/from")) {
                throw new CommandFormatException("Aw... you might be missing the /from date!");
            }

            String[] eventString = items[1].split(" /from ", 2);
            if (eventString.length != 2) {
                throw new CommandFormatException("Aw... you might be missing the task description and /from date!");
            }

            String taskString = eventString[0];
            if (!eventString[1].contains("/to")) {
                throw new CommandFormatException("Aw... you might be missing the to date!");
            }

            String[] dates = eventString[1].split(" /to ", 2);
            if (dates.length != 2) {
                throw new CommandFormatException("Aw... you might be missing a from or to date!");
            }

            String fromDate = dates[0];
            String toDate = dates[1];

            return taskManager.addTask(new EventTask(taskString, fromDate, toDate, false));
        } catch (Exception e) {
            throw new CommandFormatException("Aw... your event command might be incorrect."
                    + " Try this: event {event} /from {from} /to {to}");
        }
    }
}
