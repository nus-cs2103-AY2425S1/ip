package bob;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.ArrayList;
import java.util.List;

import bob.task.Task;

public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    boolean isEmpty() {
        return tasks.isEmpty();
    }

    List<Task> getTasks() {
        return tasks;
    }

    int getNumTasks() {
        return tasks.size();
    }

    Task getTask(int taskNum) {
        return tasks.get(taskNum - 1);
    }

    void addTask(Task task) {
        tasks.add(task);
    }

    void delTask(int taskNum) throws BobException {
        try {
            tasks.remove(taskNum - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new BobException("Invalid task number provided!");
        }
    }

    String printTasks() {
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= getNumTasks(); i++) {
            Task currTask = tasks.get(i - 1);
            if (i == getNumTasks()) {
                sb.append(i).append(". ").append(currTask);
                continue;
            }
            sb.append(i).append(". ").append(currTask).append("\n");
        }
        return sb.toString();
    }

    String printRelevantTasks(String dateStr) throws BobException {
        try {
            // Process the date string
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(dateStr, formatter);

            // Obtain relevant tasks to be printed
            StringBuilder sb = new StringBuilder();
            int numRelevantTasks = 0;
            for (Task currTask : tasks) {
                if (currTask.isRelevant(date)) {
                    numRelevantTasks++;
                    sb.append(numRelevantTasks).append(". ").append(currTask).append("\n");
                }
            }
            DateTimeFormatter formatterWords = DateTimeFormatter.ofPattern("MMM dd yyyy");
            sb.append("Total number of relevant tasks for ")
                    .append(date.format(formatterWords)).append(": ").append(numRelevantTasks);
            return sb.toString();
        } catch (DateTimeParseException e) {
            throw new BobException("Invalid date format. Required format: relevant yyyy-MM-dd");
        }
    }
}

