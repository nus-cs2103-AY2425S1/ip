package main;

import exception.CommandFoundButInvalidException;
import exception.EmptyDescriptionException;
import exception.InvalidSyntaxException;
import task.Deadlines;
import task.Events;
import task.Task;
import task.ToDos;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TaskList {
    private List<Task> allTasks;
    private Stack<Task> deletedTasks, addedTasks, markedTasks, unmarkedTasks;
    public TaskList(List<Task> allTasks) {
        this.allTasks = allTasks;
        this.deletedTasks = new Stack<>();
        this.addedTasks = new Stack<>();
        this.markedTasks = new Stack<>();
        this.unmarkedTasks = new Stack<>();
    }

    public TaskList() {
        this.allTasks = new ArrayList<>();
    }

    public void delete(String description) throws CommandFoundButInvalidException {
        try {
            int num = Integer.parseInt(description);
            int index = num - 1;
            if (index >= 0 && index < allTasks.size()) {
                Task removedTask = allTasks.remove(index);
                this.deletedTasks.push(removedTask);
            } else {
                throw new InvalidSyntaxException("delete");
            }
        } catch (NumberFormatException e) {
            throw new InvalidSyntaxException("delete");
        }
    }

    public void addTodo(String description) throws CommandFoundButInvalidException {
        Task current = new ToDos(description);
        this.allTasks.add(current);
        this.addedTasks.push(current);
    }

    public void addDeadline(String description) throws CommandFoundButInvalidException {
        Task current = new Deadlines(description);
        this.allTasks.add(current);
        this.addedTasks.push(current);
    }

    public void addEvent(String description) throws CommandFoundButInvalidException {
        Task current = new Events(description);
        this.allTasks.add(current);
        this.addedTasks.push(current);
    }

    public Task getLastAdded() {
        return addedTasks.pop();
    }

    public int getSize() {
        return this.allTasks.size();
    }

    public List<Task> getAllTasks() {
        return this.allTasks;
    }

    public Task getLastDeleted() {
        return deletedTasks.pop();
    }

    public void mark(String description, List<Task> allTasks) throws CommandFoundButInvalidException {
        try {
            if (description.isEmpty()) {
                throw new EmptyDescriptionException("mark");
            }
            int index = Integer.parseInt(description) - 1;
            if (index < 0 || index >= allTasks.size()) {
                throw new InvalidSyntaxException("mark");
            }
            Task targetTask = allTasks.get(index);
            targetTask.markAsDone();
            this.markedTasks.push(targetTask);
        } catch (NumberFormatException e) {
            throw new InvalidSyntaxException("mark");
        }
    }

    public Task getLastMarked() {
        return this.markedTasks.pop();
    }

    public void unmark(String description, List<Task> allTasks) throws CommandFoundButInvalidException {
        try {
            if (description.isEmpty()) {
                throw new EmptyDescriptionException("unmark");
            }
            int index = Integer.parseInt(description) - 1;
            if (index < 0 || index >= allTasks.size()) {
                throw new InvalidSyntaxException("unmark");
            }
            Task targetTask = allTasks.get(index);
            targetTask.markAsNotDone();
            this.unmarkedTasks.push(targetTask);
        } catch (NumberFormatException e) {
            throw new InvalidSyntaxException("unmark");
        }
    }

    public List<Task> find(String str, List<Task> allTasks) {
        ArrayList<Task> result = new ArrayList<>();
        for (Task t : allTasks) {
            if (t.toString().contains(str)) {
                result.add(t);
            }
        }
        return result;
    }

    public Task getLastUnmarked() {
        return this.unmarkedTasks.pop();
    }

}
