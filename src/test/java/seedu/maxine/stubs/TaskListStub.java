package seedu.maxine.stubs;

import seedu.maxine.MaxineList;
import seedu.maxine.exception.MaxineException;
import seedu.maxine.task.Deadline;
import seedu.maxine.task.Event;
import seedu.maxine.task.Task;
import seedu.maxine.task.Todo;

import java.util.ArrayList;
import java.util.Iterator;

public class TaskListStub implements MaxineList {
    private ArrayList<Task> list;
    public TaskListStub() {
        // nothing
    }

    public TaskListStub(ArrayList<Task> list) {
        this.list = list;
    }


    public void addTodo(String input) {
        Todo task = new Todo("homework");
        list.add(task);
    }

    public void addDeadline(String input) {
        Deadline task = new Deadline("description", "tomorrow");
        list.add(task);
    }

    public void addEvent(String input) throws MaxineException {
        Event task = new Event("test", "today", "tomorrow");
        list.add(task);
    }

    public void delete(int task) { 
        list.remove(task);
    }

    public int size() {
        return list.size();
    }

    public Task get(int num) {
        return list.get(num);
    }

    @Override
    public Iterator<Task> iterator() {
        return list.iterator();
    }
}
