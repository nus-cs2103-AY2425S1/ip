package carly.utils;

import java.util.stream.IntStream;

/** Handles task printing with various formats and conditions. */
public class TaskPrinter {

    private final TaskList taskList;

    public TaskPrinter(TaskList taskList) {
        this.taskList = taskList;
    }

    /** Prints a list of tasks with a custom message. */
    private String printTaskList(TaskList taskList, String msg) {
        StringBuilder sb = new StringBuilder();

        if (taskList.isEmpty()) {
            sb.append("Oh no. There's no tasks in your list.");
        } else {
            sb.append(msg).append("\n");
            IntStream.range(0, taskList.getSize())
                    .forEach(i -> sb.append(String.format("%d.%s\n", i + 1, taskList.get(i).toString())));
        }
        sb.append("\n");
        return sb.append(this.taskListSize()).toString();
    }

    /** Prints the sorted list of tasks. */
    public String printSortedTasks() {
        return printTaskList(taskList, "Here's your sorted list: ");
    }

    /** Prints the list of tasks found with a custom message. */
    public String printFindResults(String keyword) {
        return printTaskList(taskList, "Here's the tasks that matches " + keyword + ": ");
    }

    /** Prints all tasks in the list. */
    public String printAllTasks() {
        return printTaskList(taskList, "Here are the tasks in your list:");
    }

    /** Returns the size of the task list. */
    private String taskListSize() {
        return String.format("Total tasks: %d", taskList.getSize());
    }
}
