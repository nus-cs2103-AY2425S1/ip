package ahmad.processor.task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Class to store list of tasks.
 */
public class TaskList {
    private static final List<Task> list = new ArrayList<>();


    private static List<String> getStringList(List<Task> list) {
        if (list.isEmpty()) {
            return List.of("No items!");
        }
        return List.of(IntStream.range(0, list.size()).mapToObj(i -> (i + 1) + ". " + list.get(i))
                               .reduce("", (acc, cur) -> acc + '\n' + cur));
    }


    public static List<String> getStringList(ArrayList<Function<List<Task>, List<Task>>> mutators) {
        List<Task> newList = mutators.stream().reduce(TaskList.list, (acc, mutator) -> mutator.apply(acc), (
                a, b) -> {
            throw new UnsupportedOperationException();
        });

        return getStringList(newList);
    }

    /**
     * Returns the list of tasks in a single string.
     *
     * @return A list of single element, which is the required string.
     */
    public static List<String> getStringList() {
        return getStringList(TaskList.list);
    }

    public static List<Task> sortAscendingTime(List<Task> tasks) {
        final List<Task> sortedList = (new ArrayList<>(tasks));

        sortedList.sort(Task::compareTimeAscending);

        return sortedList;
    }

    public static Function<List<Task>, List<Task>> filterList(TaskType task) {
        final Predicate<Task> filterPredicate;

        switch (task) {
        case Todo:
            filterPredicate = currentTask -> currentTask instanceof Todo;
            break;
        case Deadline:
            filterPredicate = currentTask -> currentTask instanceof Deadline;
            break;
        case Event:
            filterPredicate = currentTask -> currentTask instanceof Event;
            break;
        default:
            assert false : "Illegal task state";
            throw new IllegalStateException();
        }

        return list -> new ArrayList<>((new ArrayList<>(list)).stream().filter(filterPredicate).toList());
    }

    /**
     * Returns string of a specific task index.
     *
     * @param idx Index of the task in question.
     * @return String of task at index.
     */
    public static String getSpecificTask(int idx) {
        return list.get(idx).toString();
    }

    /**
     * Returns number of tasks.
     *
     * @return Number of tasks.
     */
    public static int getTaskCount() {
        return list.size();
    }

    /**
     * Adds new task to list.
     *
     * @param newTask The new task in question.
     */
    public static void addTask(Task newTask) {
        list.add(newTask);
    }

    /**
     * Deletes task from list.
     *
     * @param idx Index of task to be deleted.
     */
    public static void deleteTask(int idx) {
        list.remove(idx);
    }

    /**
     * Marks a task in the list as done.
     *
     * @param idx Index of task to be marked.
     */
    public static void mark(int idx) {
        list.set(idx, list.get(idx).mark());
    }

    /**
     * Marks a task in the list as not done.
     *
     * @param idx Index of task to be unmarked.
     */
    public static void unmark(int idx) {
        list.set(idx, list.get(idx).unmark());
    }

    /**
     * Finds and returns task via supplied keyword.
     *
     * @param keyword Keyword to find task.
     * @return List of task in string based on supplied keyword.
     */
    public static List<String> findTask(String keyword) {
        Stream<Task> stream = list.stream().filter((Task task) -> task.toString().contains(keyword));
        return getStringList(stream.toList());
    }
}
