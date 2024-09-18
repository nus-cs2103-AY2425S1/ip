package task;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * The TaskList class keeps track of the current tasks
 */
public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    public Task get(int idx) throws IndexOutOfBoundsException {
        return this.taskList.get(idx);
    }

    public Task removeTask(int idx) throws IndexOutOfBoundsException {
        return this.taskList.remove(idx);
    }

    public void addTask(Task t) {
        this.taskList.add(t);
    }

    public int size() {
        return this.taskList.size();
    }

    /**
     * Factory method for predicate that filters tasks by keyword
     * @param keyword String representing the keyword to match with task description
     * @return Predicate that checks whether a task description contains the keyword
     */
    public static Predicate<Task> getKeywordPredicate(String keyword) {
        return t -> t.getDescription().contains(keyword);
    }

    /**
     * Factory method for predicate that filters tasks by type
     * @param taskType Char representing the type of tasks (e.g. 'E' for Event)
     * @return Predicate that checks whether a task matches the type
     */
    public static Predicate<Task> getTaskTypePredicate(String taskType) {
        return t -> t.getTaskType().equals(taskType);
    }

    /**
     * Filters the task list by predicate
     *
     * @param predicates Predicates used to filter the task list
     * @return ArrayList of matching tasks
     */
    public ArrayList<Task> filterByPredicates(List<Predicate<Task>> predicates) {
        Predicate<Task> andPredicates = predicates
                .stream()
                .reduce(Predicate::and)
                .orElse(x -> true);

        return new ArrayList<>(this.taskList.stream().filter(andPredicates).toList());
    }

    /**
     * Filters the task list by tasks with descriptions that match the keyword
     *
     * @param keyword String representing the keyword to match
     * @return ArrayList of matching tasks
     */
    public ArrayList<Task> filterByKeyword(String keyword) {
        List<Predicate<Task>> predicate = new ArrayList<>();
        predicate.add(TaskList.getKeywordPredicate(keyword));
        return this.filterByPredicates(predicate);
    }
}