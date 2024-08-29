package dgpt.task;

import dgpt.task.Deadline;
import dgpt.task.Event;
import dgpt.task.Task;
import dgpt.task.ToDo;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class TaskList {

    List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public Task addToDoToList(String description) {
        Task newTask = new ToDo(description);
        this.taskList.add(newTask);
        return newTask;
    }

    public Task addDeadlineToList(String description, String deadline) throws DateTimeParseException {
        Task newTask = new Deadline(description, deadline);
        this.taskList.add(newTask);
        return newTask;
    }

    public Task addEventToList(String description, String startTime, String endTime) {
        Task newTask = new Event(description, startTime, endTime);
        this.taskList.add(newTask);
        return newTask;
    }

    public Task markTask(int index) {
        this.taskList.get(index).mark();
        return this.taskList.get(index);
    }

    public Task unmarkTask(int index) {
        this.taskList.get(index).unmark();
        return this.taskList.get(index);
    }

    public Task deleteTask(int index) {
        Task curr = this.taskList.get(index);
        this.taskList.remove(index);
        return curr;
    }

    public List<Task> getTaskList() {
        return this.taskList;
    }

    public int getSize() {
        return this.taskList.size();
    }
}
