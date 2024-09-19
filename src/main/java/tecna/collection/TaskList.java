package tecna.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import tecna.exception.TaskDuplicateException;
import tecna.task.Task;

public class TaskList {
    private ArrayList<Task> tasks;
    private int size;

    public TaskList() {
        this.tasks = new ArrayList<>();
        this.size = 0;
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.size = tasks.size();
    }

    public int getSize() {
        return size;
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Adds new item to the list of tasks
     *
     * @param task extracted from the user input
     */
    public void addItem(Task task) throws TaskDuplicateException {
        assert task != null;

        if (isDuplicate(task)) {
            throw new TaskDuplicateException();
        }

        this.tasks.add(task);
        ++this.size;
    }

    public boolean isDuplicate(Task task) {
        for (Task t : this.tasks) {
            if (task.equals(t)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Deletes the specified items.
     *
     * @param index
     */
    public String deleteItem(int index) {
        String item = this.tasks.get(index).toString();
        this.tasks.remove(index);
        --this.size;
        StringBuilder sb= new StringBuilder("Sure! I've deleted this task:");
        sb.append(item);
        sb.append(">> Now you have " + this.size + (size > 1 ? " tasks" : " task") + " in the list.");
        String response = sb.toString();
        System.out.println(response);
        return response;
    }

    /**
     * Marks the index-th task as done.
     *
     * @param index
     */
    public void mark(int index) {
        this.tasks.get(index).markAsDone();
    }

    /**
     * Unmarks the index-th task.
     *
     * @param index
     */
    public void unmark(int index) {
        this.tasks.get(index).unMarkAsDone();
    }

    /**
     * Finds and prints all the matching tasks.
     *
     * @param keyword is one word that user enters to filter the task.
     * @author Generated with the help of ChatGPT at 11:13PM 12 Sep 2024.
     */
    public String findTasks(String keyword) {
        assert !keyword.isEmpty(); // handled by CommandScanner

        List<Task> matchingTasks = tasks.stream()
                                             .filter(task -> {
                                                 String[] words = task.getTaskName().split(" ");
                                                 return Stream.of(words)
                                                              .anyMatch(word -> word.toLowerCase().contains(keyword
                                                                      .toLowerCase()));
                                             })
                                             .collect(Collectors.toList());

        String response = "Here are the matching tasks in your list:\n" +
                matchingTasks.stream()
                             .map(task -> (matchingTasks.indexOf(task) + 1) + ". " + task)
                             .collect(Collectors.joining("\n"));

        System.out.println(response);
        return response;
    }

    /**
     * Prints string representation of the taskList.
     *
     * @return A string of all tasks in the list.
     * @author brendanng7.
     */
    @Override
    public String toString() {
        if (this.size == 0) {
            return "You don't have any tasks!";
        }
        return tasks.stream()
                .map(t -> (tasks.indexOf(t) + 1) + ". " + t.toString())
                .collect(Collectors.joining("\n"));
    }
}


