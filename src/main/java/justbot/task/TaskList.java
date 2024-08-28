package justbot.task;

import justbot.exception.JustbotException;

import java.util.ArrayList;
import java.util.Iterator;


public class TaskList implements Iterable<Task> {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public Iterator<Task> iterator() {
        return tasks.iterator();
    }

    public int size() {
        return this.tasks.size();
    }

    public Task get(int index) {
        return this.tasks.get(index);
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public void delete(int deleteNumber) {
        tasks.remove(deleteNumber -1);
    }

    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    public void validateNotEmpty() throws JustbotException {
        if (isEmpty()) {
            throw new JustbotException("Hey man you have no tasks in your list!");
        }
    }

    public void validateMarkTaskNumber(int taskNumber) throws JustbotException {
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new JustbotException("Hey man there is no such task!");
        }
        if (tasks.get(taskNumber - 1).getIsDone()) {
            throw new JustbotException("Hey man this task is already marked!");
        }
    }

    public void validateUnmarkTaskNumber(int taskNumber) throws JustbotException {
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new JustbotException("Hey man there is no such task!");
        }
        if (!tasks.get(taskNumber - 1).getIsDone()) {
            throw new JustbotException("Hey man this task is already unmarked!");
        }
    }

    public void validateDeleteTaskNumber(int taskNumber) throws JustbotException {
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new JustbotException("Hey man there is no such task!");
        }
    }

}
