package rex.task;

import rex.exception.InvalidInputException;
import rex.util.Parser;
import rex.util.Storage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskList {
    private Storage storage;
    private ArrayList<Task> list;

    public TaskList(Storage storage) throws IOException {
        this.storage = storage;
        this.list = new ArrayList<>();
        loadList();
    }

    public ToDo addToDo(String argument) throws IOException {
        ToDo toDo = new ToDo(argument, false);
        list.add(toDo);
        updateStorage();
        return toDo;
    }

    public Deadline addDeadline(String argument) throws IOException, InvalidInputException {
        String[] argumentTokens = Parser.parseDeadline(argument);
        String description = argumentTokens[0];
        LocalDateTime by = Parser.parseDateTime(argumentTokens[1]);

        Deadline deadine = new Deadline(description, false, by);
        list.add(deadine);
        updateStorage();
        return deadine;
    }

    public Event addEvent(String argument) throws IOException, InvalidInputException {
        String[] argumentTokens = Parser.parseEvent(argument);
        String description = argumentTokens[0];
        LocalDateTime from = Parser.parseDateTime(argumentTokens[1]);
        LocalDateTime to = Parser.parseDateTime(argumentTokens[2]);

        Event event = new Event(description, false, from, to);
        list.add(event);
        updateStorage();
        return event;
    }

    public void loadTask(String description, boolean isDone) {
        list.add(new ToDo(description, isDone));
    }

    public void loadTask(String description, boolean isDone, LocalDateTime by) {
        list.add(new Deadline(description, isDone, by));
    }

    public void loadTask(String description, boolean isDone, LocalDateTime from, LocalDateTime to) {
        list.add(new Event(description, isDone, from, to));
    }

    public Task getTask(String argument) {
        return getTask(Integer.parseInt(argument));
    }

    public Task getTask(int index) {
        return list.get(index - 1);
    }

    public String getListDisplay() {
        if (isEmpty()) return "The list is empty! rawr\n";

        String output = "";
        for (int i = 1; i <= list.size(); i++) {
            output += i + "." + getTask(i) + "\n";
        }

        return output;
    }

    public int size() {
        return list.size();
    }

    private boolean isEmpty() {
        return size() == 0;
    }

    public String findTasks(String keyword) {
        int taskNumber = 1;
        String output = "Here are the matching tasks in your list:\n";

        for (int i = 1; i <= list.size(); i++) {
            Task task = getTask(i);
            if (task.getDescription().contains(keyword)) {
                output += taskNumber + "." + task + "\n";
                taskNumber++;
            }
        }

        return output;
    }

    public Task markTask(String argument) throws IOException {
        Task actionTask = getTask(argument);
        actionTask.markDone();
        updateStorage();
        return actionTask;
    }

    public Task unmarkTask(String argument) throws IOException {
        Task actionTask = getTask(argument);
        actionTask.unmarkDone();
        updateStorage();
        return actionTask;
    }

    public Task deleteTask(String argument) throws IOException {
        Task actionTask = getTask(argument);
        list.remove(actionTask);
        Task.removeTask();
        updateStorage();
        return actionTask;
    }

    private void loadList() throws IOException {
        storage.loadFile(this);
    }
    private void updateStorage() throws IOException {
        storage.updateFile(this);
    }
}
