package seedu.avo.tasks;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import seedu.avo.storage.Storage;
import seedu.avo.ui.AppUI;

public class TaskManager {
    private final List<Task> tasks;
    private final Storage<Task, String> storage;
    private final AppUI ui;
    public TaskManager(Storage<Task, String> storage, AppUI ui) {
        tasks = storage.fetchAll();
        this.storage = storage;
        this.ui = ui;
    }
    public void listTasks() {
        ui.printTaskCount(tasks.size());
        List<Integer> indexes = IntStream.range(0, tasks.size())
                .boxed().toList();
        ui.printTasksFromList(tasks, indexes);
    }
    public void completeTask(int index) {
        Task task = tasks.get(index);
        task.complete();
        saveList();
        ui.printTaskMarked(tasks, index);
    }
    public void unCompleteTask(int index) {
        Task task = tasks.get(index);
        task.unComplete();
        saveList();
        ui.printTaskUnmarked(tasks, index);
    }
    public void addTask(Task task) {
        tasks.add(task);
        saveList();
        ui.printTaskAdded(tasks);
    }
    public void deleteTask(int index) {
        Task task = tasks.remove(index);
        saveList();
        ui.printTaskRemoved(task);
    }
    private void saveList() {
        storage.write(this.formatData());
    }
    public void getTasksByDate(LocalDate date) {
        List<Integer> indexes = filterByDate(date);
        ui.printTaskCount(indexes.size());
        ui.printTasksFromList(tasks, indexes);
    }
    public String formatData() {
        StringBuilder str = new StringBuilder();
        for (Task task : tasks) {
            str.append(task.formatData()).append("\n");
        }
        return str.toString();
    }
    public void getTasksByName(String name) {
        List<Integer> indexes = filterByName(name);
        ui.printTaskCount(indexes.size());
        ui.printTasksFromList(tasks, indexes);
    }
    private List<Integer> filterByName(String name) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).matchName(name)) {
                result.add(i);
            }
        }
        return result;
    }
    private List<Integer> filterByDate(LocalDate date) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).isOccurringOnDate(date)) {
                result.add(i);
            }
        }
        return result;
    }
}
