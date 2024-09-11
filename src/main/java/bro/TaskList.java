package bro;

import java.util.ArrayList;

public class TaskList {
    private static final ArrayList<Task> list = new ArrayList<>();
    private final Ui ui;
    private final Parser parser;

    public TaskList(Ui ui, Parser parser) {
        this.ui = ui;
        this.parser = parser;
    }

    /**
     * Marks a task as done based on its index in the list and returns a confirmation message.
     *
     * @param i The 1-based index of the task to mark as done.
     * @return A confirmation message stating the task is marked.
     */
    public String markTask(int i) {
        assert (i > 0) : "task number to mark must be above 0";
        list.get(i - 1).mark();
        return ui.printMark(i, this);
    }

    /**
     * Marks a task as not done based on its index in the list and returns a confirmation message.
     *
     * @param i The 1-based index of the task to mark as not done.
     * @return A confirmation message stating the task is unmarked.
     */
    public String unmarkTask(int i) {
        assert (i > 0) : "task number to unmark must be above 0";
        list.get(i - 1).unmark();
        return ui.printUnmark(i, this);
    }

    /**
     * Deletes a task from the list based on its index and returns a confirmation message.
     *
     * @param i The 1-based index of the task to delete.
     * @return A confirmation message stating the task is deleted.
     */
    public String deleteTask(int i) {
        assert (i > 0) : "task number to delete must be above 0";
        String s = ui.printDelete(i, this);
        list.remove(i - 1);
        return s;
    }

    /**
     * Finds and returns a message with tasks that contain a specific keyword.
     *
     * @param word The keyword to search for in the tasks list.
     * @return Message with all tasks that contain the keyword.
     */
    public String findTasks(String word) {
        return ui.printByWord(this, word);
    }

    /**
     * Checks if the most recently added task is already present in the list of tasks.
     *
     * This method retrieves the most recent task from the list and checks if it
     * already exists within the current list before task was added. If the task is found,
     * a message is returned indicating that the task may be a duplicate and prompting
     * the user for further action.
     *
     * @param full The string to check for duplicate task entries. It is expected to
     *             contain a list of tasks, separated by newline characters.
     * @return A string message warning about the possible duplicate if the most
     *         recent task is found in `full`, or an empty string if no duplicate is detected.
     */
    public String findDuplicate(String full) {
        String curr = list.get(this.size() - 1).toString();
        if (full.contains(curr + "\n")) {
            return "   Task added seems to already exist. Do you want to continue? [yes/no]";
        }
        return "";
    }

    /**
     * Adds a new Todo task to the list and returns a confirmation message.
     *
     * @param s The description of the Todo task.
     * @throws BroException If the description is empty.
     * @return The confirmation message that Todo task is added.
     */
    public String addTodo(String s) throws BroException {
        if (s.trim().isEmpty()) {
            throw new BroException("Please provide the name of the task!!!");
        }
        Task curr = new Todo(s.trim());
        list.add(curr);
        return ui.printStatus(curr, list.size());
    }

    /**
     * Adds a new Deadline task to the list by parsing the input string and returnd a confirmation message.
     *
     * @param s The description and deadline of the task, expected to contain "/by".
     * @throws BroException If the description is empty or does not contain "/by".
     * @return The confirmation message that Deadline task is added.
     */
    public String addDeadline(String s) throws BroException {
        if (s.trim().isEmpty()) {
            throw new BroException("Please provide the name of the task!!!");
        } else if (!s.contains(" /by ")) {
            throw new BroException("Please include \"/by\" in description!!!");
        }
        return parser.parseDeadline(s, this);
    }

    /**
     * Adds a new Event task to the list by parsing the input string and returns a confirmation message.
     *
     * @param s The description and time period of the event, expected to contain "/from" and "/to".
     * @throws BroException If the description is empty or does not contain both "/from" and "/to".
     * @return The confirmation message that Event task is added.
     */
    public String addEvent(String s) throws BroException {
        if (s.trim().isEmpty()) {
            throw new BroException("Please provide the name of the task!!!");
        } else if (!s.contains(" /from ") || !s.contains(" /to ")) {
            throw new BroException("Please include \"/from\" and \"/to\" in description!!!");
        }
        return parser.parseEvent(s, this);
    }

    /**
     * Returns the number of tasks currently in the list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return list.size();
    }

    /**
     * Retrieves a task from the list based on its index.
     *
     * @param i The 0-based index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task get(int i) {
        return list.get(i);
    }

    /**
     * Adds a task to the list.
     *
     * @param t The task to be added.
     */
    public void add(Task t) {
        list.add(t);
    }
}
