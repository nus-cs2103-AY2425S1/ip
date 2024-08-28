package bro;

import java.util.ArrayList;

public class TaskList {
    private static final ArrayList<Task> list = new ArrayList<>();
    private Ui ui;
    private Parser parser;

    public TaskList(Ui ui, Parser parser) {
        this.ui = ui;
        this.parser = parser;
    }

    /**
     * Marks a task as done based on its index in the list and prints a confirmation message.
     *
     * @param i The 1-based index of the task to mark as done.
     */
    public void markTask(int i) {
        list.get(i - 1).mark();
        ui.printMark(i, this);
    }

    /**
     * Marks a task as not done based on its index in the list and prints a confirmation message.
     *
     * @param i The 1-based index of the task to mark as not done.
     */
    public void unmarkTask(int i) {
        list.get(i - 1).unmark();
        ui.printUnmark(i, this);
    }

    /**
     * Deletes a task from the list based on its index and prints a confirmation message.
     *
     * @param i The 1-based index of the task to delete.
     */
    public void deleteTask(int i) {
        ui.printDelete(i, this);
        list.remove(i - 1);
    }

    /**
     * Adds a new Todo task to the list and prints a confirmation message.
     *
     * @param s The description of the Todo task.
     * @throws BroException If the description is empty.
     */
    public void addTodo(String s) throws BroException {
        if (s.trim().isEmpty()) {
            throw new BroException("Please provide the name of the task!!!");
        }
        Task curr = new Todo(s.trim());
        list.add(curr);
        ui.printStatus(curr, list.size());
    }

    /**
     * Adds a new Deadline task to the list by parsing the input string and prints a confirmation message.
     *
     * @param s The description and deadline of the task, expected to contain "/by".
     * @throws BroException If the description is empty or does not contain "/by".
     */
    public void addDeadline(String s) throws BroException {
        if (s.trim().isEmpty()) {
            throw new BroException("Please provide the name of the task!!!");
        } else if (!s.contains(" /by ")) {
            throw new BroException("Please include \"/by\" in bro.Deadline!!!");
        }
        parser.parseDeadline(s, this);
    }

    /**
     * Adds a new Event task to the list by parsing the input string and prints a confirmation message.
     *
     * @param s The description and time period of the event, expected to contain "/from" and "/to".
     * @throws BroException If the description is empty or does not contain both "/from" and "/to".
     */
    public void addEvent(String s) throws BroException {
        if (s.trim().isEmpty()) {
            throw new BroException("Please provide the name of the task!!!");
        } else if (!s.contains(" /from ") || !s.contains(" /to ")) {
            throw new BroException("Please include \"/from\" and \"/to\" in bro.Event!!!");
        }
        parser.parseEvent(s, this);
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
