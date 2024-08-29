package task;

import exceptions.AlreadyCompletedException;
import exceptions.TaskDoesNotExistException;

import java.util.ArrayList;


public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void add(Task item) {
        tasks.add(item);
    }

    public void complete(int index) throws AlreadyCompletedException, TaskDoesNotExistException {
        try {
            Task task = tasks.get(index);
            if (task == null) throw new TaskDoesNotExistException(index);
            task.complete();
        } catch (IndexOutOfBoundsException e) {
            throw new TaskDoesNotExistException(index);
        }
    }

    public String taskAt(int index) {
        return tasks.get(index).toString();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public String delete(int index) throws TaskDoesNotExistException {
        try {
            Task task = tasks.get(index);
            if (task == null) throw new TaskDoesNotExistException(index);
            tasks.remove(index);
            return task.toString();
        } catch (IndexOutOfBoundsException e) {
            throw new TaskDoesNotExistException(index);
        }
    }

    public String toData() {
        StringBuilder data = new StringBuilder();
        for (Task task : tasks) {
            data.append(task.toData()).append("\n");
        }
        return data.toString();
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            output.append(String.format("%d.[%s][%s] %s\n", i + 1, tasks.get(i).getTypeIcon(),
                    tasks.get(i).getStatusIcon(), tasks.get(i)));
        }
        return output.toString();
    }
}