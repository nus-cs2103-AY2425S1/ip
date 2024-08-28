package echobot.task;

import echobot.exception.InvalidDeadlineFormatException;
import echobot.exception.TaskNotFoundException;
import echobot.io.Saveable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskList implements Saveable {
    private final List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public Task deleteTaskByIndex(int index) throws TaskNotFoundException {
        try {
            return this.taskList.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNotFoundException();
        }
    }

    public List<Task> getTaskList() {
        return this.taskList;
    }

    public Task markTaskByIndex(int index) throws TaskNotFoundException {
        try {
            Task task = this.taskList.get(index);
            task.markDone();
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNotFoundException();
        }
    }

    public Task unmarkTaskByIndex(int index) throws TaskNotFoundException {
        try {
            Task task = this.taskList.get(index);
            task.markUndone();
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNotFoundException();
        }
    }

    public List<Task> getTasksOccurringOn(LocalDate date) throws InvalidDeadlineFormatException {
        List<Task> taskList1 = new ArrayList<>();

        for (Task task : this.taskList) {
            if ((task instanceof echobot.task.ScheduledTask) && !task.isDone() && ((echobot.task.ScheduledTask) task).isTaskWithinThisDate(date)) {
                taskList1.add(task);
            }
        }

        return taskList1;
    }

    public int size() {
        return this.taskList.size();
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (Task task : this.taskList) {
            output.append(task);
            output.append("\n");
        }
        return output.toString();
    }

    @Override
    public String save() {
        StringBuilder output = new StringBuilder();
        for (Saveable task : this.taskList) {
            output.append(task.save());
            output.append("\n");
        }
        return output.toString();
    }
}
