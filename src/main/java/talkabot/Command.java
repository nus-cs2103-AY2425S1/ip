package talkabot;

import java.io.IOException;
import java.time.DateTimeException;

import talkabot.exceptions.InvalidEditException;
import talkabot.exceptions.InvalidScheduleException;
import talkabot.exceptions.TalkaBotException;
import talkabot.task.Deadline;
import talkabot.task.Event;
import talkabot.task.Task;
import talkabot.task.TaskList;
import talkabot.task.ToDo;

/**
 * Command class handles the different commands by the user.
 */
public class Command {
    private Ui ui;
    private Storage storage;
    private String commandType;

    /**
     * Constructs an instance of Command class.
     */
    public Command(Ui ui, Storage storage) {
        this.ui = ui;
        this.storage = storage;
    }

    /**
     * Handles list command to return current list of tasks.
     *
     * @param taskList Current list of tasks.
     * @return String representation of current list of tasks.
     */
    public String handleList(TaskList taskList) {
        this.commandType = "RetrieveCommand";
        return this.ui.displayList(taskList);
    }

    private boolean checkIfValid(String str, int len, int size) {
        try {
            return str.length() > len
                    && str.charAt(len - 1) == ' '
                    && Integer.parseInt(str.substring(len)) <= size
                    && Integer.parseInt(str.substring(len)) >= 1;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Handles mark command, marking the task and returning response.
     *
     * @param input User input.
     * @param taskList Current list of tasks.
     * @return String representation of response to task being marked.
     * @throws InvalidEditException if command does not indicate a valid task.
     * @throws IOException if issues are encountered while saving the task list.
     */
    public String handleMark(String input, TaskList taskList)
            throws InvalidEditException, IOException {
        int n;
        if (input.startsWith("m ")) {
            n = 2;
        } else {
            n = 5;
        }
        if (!checkIfValid(input, n, taskList.size())) {
            throw new InvalidEditException("mark");
        }
        this.commandType = "ChangeMarkCommand";
        Task task = taskList.get(Integer.parseInt(input.substring(n)) - 1);
        task.markAsDone();
        storage.save(taskList);
        return this.ui.mark(task);
    }

    /**
     * Handles unmark command, unmarking the task and returning response.
     *
     * @param input User input.
     * @param taskList Current list of tasks.
     * @return String representation of response to task being unmarked.
     * @throws InvalidEditException if command does not indicate a valid task.
     * @throws IOException if issues are encountered while saving the task list.
     */
    public String handleUnmark(String input, TaskList taskList)
            throws InvalidEditException, IOException {
        int n;
        if (input.startsWith("um ")) {
            n = 3;
        } else {
            n = 7;
        }
        if (!checkIfValid(input, n, taskList.size())) {
            throw new InvalidEditException("unmark");
        }
        this.commandType = "ChangeMarkCommand";
        Task task = taskList.get(Integer.parseInt(input.substring(n)) - 1);
        task.markAsUndone();
        storage.save(taskList);
        return this.ui.unmark(task);
    }

    /**
     * Handles delete command, deleting the task and returning response.
     *
     * @param input User input.
     * @param taskList Current list of tasks.
     * @return String representation of response to task being deleted.
     * @throws InvalidEditException if command does not indicate a valid task.
     * @throws IOException if issues are encountered while saving the task list.
     */
    public String handleDelete(String input, TaskList taskList)
            throws InvalidEditException, IOException {
        int n;
        if (input.startsWith("del ")) {
            n = 4;
        } else {
            n = 7;
        }
        if (!checkIfValid(input, n, taskList.size())) {
            throw new InvalidEditException("delete");
        }
        this.commandType = "DeleteCommand";
        int origSize = taskList.size();
        assert origSize > 0;
        Task task = taskList.delete(Integer.parseInt(input.substring(n)) - 1);
        assert taskList.size() == origSize - 1;
        storage.save(taskList);
        return this.ui.delete(task, taskList.size());
    }

    /**
     * Handles get day command, returning the task's important date(s) as days of the week.
     *
     * @param input User input.
     * @param taskList Current list of tasks.
     * @return String representation of task's important day(s).
     * @throws InvalidEditException if command does not indicate a valid task.
     */
    public String handleGetDay(String input, TaskList taskList) throws InvalidEditException {
        int n;
        if (input.startsWith("gd ")) {
            n = 3;
        } else {
            n = 8;
        }
        if (!checkIfValid(input, n, taskList.size())) {
            throw new InvalidEditException("get the day of");
        }
        this.commandType = "RetrieveCommand";
        Task task = taskList.get(Integer.parseInt(input.substring(n)) - 1);
        return this.ui.getDay(task);
    }

    /**
     * Handles find command, returning the tasks matching the keywords given.
     *
     * @param input User input.
     * @param taskList Current list of tasks.
     * @return String representation of tasks matching keywords.
     * @throws InvalidEditException if command does not include any keywords.
     */
    public String handleFind(String input, TaskList taskList) throws InvalidEditException {
        int n;
        if (input.startsWith("f ")) {
            n = 3;
        } else {
            n = 6;
        }
        if (input.length() < n) {
            throw new InvalidEditException("find");
        }
        this.commandType = "RetrieveCommand";
        return this.ui.returnMatches(taskList
                .find(input.substring(n - 1)));
    }

    /**
     * Handles to do command, adding task and returning response message.
     *
     * @param input User input.
     * @param taskList Current list of tasks.
     * @return String representation of response to adding task.
     * @throws InvalidScheduleException if input does not include task to be added.
     * @throws IOException if issues are encountered while saving task list.
     */
    public String handleToDo(String input, TaskList taskList)
            throws IOException, InvalidScheduleException {
        int n;
        if (input.startsWith("t ")) {
            n = 3;
        } else {
            n = 6;
        }
        if (input.length() < n) {
            throw new InvalidScheduleException();
        }
        this.commandType = "AddCommand";
        Task curr = new ToDo(input.substring(n - 1));
        taskList.add(curr);
        storage.save(taskList);
        return this.ui.addTask(curr, taskList.size());
    }

    /**
     * Handles deadline command, adding task and returning response message.
     *
     * @param input User input.
     * @param taskList Current list of tasks.
     * @return String representation of response to adding task.
     * @throws InvalidScheduleException if input does not include task to be added.
     * @throws IOException if issues are encountered while saving task list.
     */
    public String handleDeadline(String input, TaskList taskList)
            throws IOException, InvalidScheduleException {
        int n;
        if (input.startsWith("d ")) {
            n = 3;
        } else {
            n = 10;
        }
        if (input.length() < n) {
            throw new InvalidScheduleException();
        }
        this.commandType = "AddCommand";
        Task curr = new Deadline(Parser.getDeadline(input, n - 1));
        taskList.add(curr);
        storage.save(taskList);
        return this.ui.addTask(curr, taskList.size());
    }

    /**
     * Handles event command, adding task and returning response message.
     *
     * @param input User input.
     * @param taskList Current list of tasks.
     * @return String representation of response to adding task.
     * @throws InvalidScheduleException if input does not include task to be added.
     * @throws IOException if issues are encountered while saving task list.
     */
    public String handleEvent(String input, TaskList taskList)
            throws IOException, InvalidScheduleException {
        int n;
        if (input.startsWith("e ")) {
            n = 3;
        } else {
            n = 7;
        }
        if (input.length() < n) {
            throw new InvalidScheduleException();
        }
        this.commandType = "AddCommand";
        Task curr = new Event(Parser.getEvent(input, n - 1));
        taskList.add(curr);
        storage.save(taskList);
        return this.ui.addTask(curr, taskList.size());
    }

    /**
     * Handles TalkaBot Exceptions, returning the error message.
     *
     * @param e TalkaBot Exception.
     * @return Error message to be printed.
     */
    public String handleTalkaBotException(TalkaBotException e) {
        this.commandType = "Error";
        return this.ui.error(e.getMessage());
    }

    /**
     * Handles DateTimeExceptions, returning the error message.
     *
     * @param e DateTimeException.
     * @return Error message to be printed.
     */
    public String handleDateTimeException(DateTimeException e) {
        this.commandType = "Error";
        return this.ui
                .error("Sorry, I need a valid date format! For example: yyyy-mm-dd");
    }

    /**
     * Handles IOExceptions, returning the error message.
     *
     * @param e IOException.
     * @return Error message to be printed.
     */
    public String handleIoException(IOException e) {
        this.commandType = "Error";
        return this.ui.error(e.getMessage());
    }

    /**
     * Returns the command type of the latest command.
     *
     * @return String representation of command type of latest command.
     */
    public String getCommandType() {
        return this.commandType;
    }

    /**
     * Handles exit command, returning goodbye message.
     *
     * @return String representation of goodbye message.
     */
    public String handleBye() {
        this.commandType = "ByeCommand";
        return this.ui.getGoodbye();
    }

}
