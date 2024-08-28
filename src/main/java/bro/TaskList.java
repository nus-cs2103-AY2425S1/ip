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

    public void markTask(int i) {
        list.get(i - 1).mark();
        ui.printMark(i, this);
    }

    public void unmarkTask(int i) {
        list.get(i - 1).unmark();
        ui.printUnmark(i, this);
    }

    public void deleteTask(int i) {
        ui.printDelete(i, this);
        list.remove(i - 1);
    }

    public void addTodo(String s) throws BroException {
        if (s.trim().isEmpty()) {
            throw new BroException("Please provide the name of the task!!!");
        }
        Task curr = new Todo(s.trim());
        list.add(curr);
        ui.printStatus(curr, list.size());
    }

    public void addDeadline(String s) throws BroException {
        if (s.trim().isEmpty()) {
            throw new BroException("Please provide the name of the task!!!");
        } else if (!s.contains(" /by ")) {
            throw new BroException("Please include \"/by\" in bro.Deadline!!!");
        }
        parser.parseDeadline(s, this);
    }

    public void addEvent(String s) throws BroException {
        if (s.trim().isEmpty()) {
            throw new BroException("Please provide the name of the task!!!");
        } else if (!s.contains(" /from ") || !s.contains(" /to ")) {
            throw new BroException("Please include \"/from\" and \"/to\" in bro.Event!!!");
        }
        parser.parseEvent(s, this);
    }

    public int size() {
        return list.size();
    }

    public Task get(int i) {
        return list.get(i);
    }

    public void add(Task t) {
        list.add(t);
    }
}
