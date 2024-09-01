package bob;

import bob.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

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

    Task getTask(int taskNum) throws BobException {
        try {
            return tasks.get(taskNum - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new BobException("The task number provided is invalid.");
        }
    }

    void addTask(Task task) {
        tasks.add(task);
    }

    void delTask(int taskNum) throws BobException {
        try {
            tasks.remove(taskNum - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new BobException("The task number provided is invalid.");
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

    String printRelevantTasksByDate(String dateStr) throws BobException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(dateStr, formatter);

            StringBuilder sb = new StringBuilder();
            int numRelevantTasks = 0;

            for (Task currTask : tasks) {
                if (currTask.isRelevant(date)) {
                    numRelevantTasks++;
                    sb.append(numRelevantTasks).append(". ").append(currTask).append("\n");
                }
            }

            DateTimeFormatter formatterWords = DateTimeFormatter.ofPattern("MMM dd yyyy");
            sb.append("Total number of relevant tasks for ").append(date.format(formatterWords))
                    .append(": ").append(numRelevantTasks);
            return sb.toString();

        } catch (DateTimeParseException e) {
            throw new BobException("Invalid date format. Required format: relevant yyyy-MM-dd");
        }
    }

    String printTasksByKeyword(String keyword) throws BobException {
        if (keyword.isEmpty()) {
            throw new BobException("Please provide a keyword or a phrase.");
        }

        StringBuilder sb = new StringBuilder();
        int numMatchingTasks = 0;

        for (Task currTask : tasks) {
            String description = currTask.getDescription();
            if (description.contains(keyword.toLowerCase())) {
                numMatchingTasks++;
                sb.append(numMatchingTasks).append(". ").append(currTask).append("\n");
            }
        }

        sb.append("Total number of tasks containing \"").append(keyword.toLowerCase())
                .append("\": ").append(numMatchingTasks);
        return sb.toString();
    }
}

