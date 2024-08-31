package yappingbot.tasks.tasklist;

import yappingbot.exceptions.YappingBotException;
import yappingbot.exceptions.YappingBotOOBException;
import yappingbot.stringconstants.ReplyTextMessages;
import yappingbot.tasks.Task;
import yappingbot.ui.MultilineStringBuilder;
import yappingbot.ui.Ui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

import static yappingbot.tasks.tasklist.TaskParser.parseSingleTask;

public class TaskList implements Iterable<Task> {
    protected static ArrayList<Task> tasks;
    protected int size;
    public TaskList() {
        tasks = new ArrayList<>();
    }

    public static TaskList generateFromRaw(ArrayList<String> tasksRaw) {
        TaskList userList = new TaskList();
        ArrayList<Exception> errorLists = new ArrayList<>();
        for (String taskIndividualRaw : tasksRaw) {
            String[] s = taskIndividualRaw.split(":");
            try {
                userList.add(parseSingleTask(s));
            } catch (YappingBotException e) {
                errorLists.add(e);
            }
        }
        if (!errorLists.isEmpty()) {
            MultilineStringBuilder msb = new MultilineStringBuilder();
            for (Exception e : errorLists) {
                msb.addLine(e.getMessage());
            }
            Ui.printError(String.format(ReplyTextMessages.LOAD_FILE_ERROR_1s, msb));
        }
        return userList;
    }

    public void add(Task task) {
        tasks.add(task);
        size += 1;
    }
    public Task delete(int index) throws YappingBotOOBException {
        Task t = get(index);
        tasks.remove(index);
        size -= 1;
        return t;
    }
    public int size() {
        return size;
    }
    public Task get(int index) throws YappingBotOOBException {
        if (index < 0 || index >= size) {
            throw new YappingBotOOBException(index);
        }
        return tasks.get(index);
    }
    public boolean isEmpty() { return size == 0; }

    @Override
    public Iterator<Task> iterator() {
        return tasks.iterator();
    }

    @Override
    public void forEach(Consumer<? super Task> action) {
        tasks.forEach(action);
    }

    @Override
    public Spliterator<Task> spliterator() {
        return tasks.spliterator();
    }

    public ArrayList<String> toRawFormat() {
        ArrayList<String> taskListRaw = new ArrayList<>();
        for (Task t : tasks) {
            taskListRaw.add(t.serialize());
        }
        return taskListRaw;
    }
}
