package bonnieGUI;

import java.util.ArrayList;
import java.util.function.Function;
import java.util.stream.Collectors;

import Tasks.*;

public class GUITaskList {
    protected static ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Retrieves the list of tasks.
     * @return The list of tasks.
     */
    protected static ArrayList<Task> getTasks() {
        return taskList;
    }

    /**
     * Retrieves the size of the task list.
     *
     * @return The size of the task list.
     */
    protected static int getSize() {
        return taskList.size();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    protected static void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Marks a task in the task list as done.
     *
     * @param taskNumber The number of the task to be marked as done.
     */
    protected static void markTaskAsDone(int taskNumber) {
        taskList.get(taskNumber - 1).markAsDone();
    }

    /**
     * Unmarks a task as done.
     *
     * @param taskNumber The task number of the task to be unmarked as done.
     */
    protected static void unmarkTaskAsDone(int taskNumber) {
        taskList.get(taskNumber - 1).unmarkAsDone();
    }

    /**
     * Removes a task from the task list.
     *
     * @param taskNumber The number of the task to be removed.
     * @throws IndexOutOfBoundsException If the task number is invalid.
     */
    protected static void removeTask(int taskNumber) {
        try {
            taskList.remove(taskNumber - 1);
            System.out.println(String.format("You have successfully deleted task %d!", taskNumber));
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("You cannot delete a task that does not exist!");
        }
    }

    /**
     * Finds tasks that contain a specified keyword.
     *
     * @param keyword The keyword to search for in the tasks.
     * @return An ArrayList of tasks that contain the keyword.
     */
    protected static ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : taskList) {
            if (task.toString().contains(keyword)) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }

    /**
     * Sorts the task list by arranging the deadline tasks first followed by the non-deadline tasks.
     *
     * The method starts by getting all the non-deadline tasks by filtering the tasks and collecting them into an ArrayList.
     * Next, the method retrieves the sorted list of deadline tasks using the helper method getSortedDeadlineTasks.
     * Then, it creates a new ArrayList called combinedList and adds all the deadline tasks to it.
     * Finally, it adds all the non-deadline tasks to the combinedList. The taskList is then updated with the combinedList.
     *
     * This method does not return any value.
     */
    protected static void sort() {
        ArrayList<Task> nonDeadlineTasks = getTasks().stream() // Get non deadline tasks
                .filter(task -> !(task instanceof Deadline))
                .collect(Collectors.toCollection(ArrayList::new));
        ArrayList<Task> deadlineTasks = new ArrayList<>(getSortedDeadlineTasks(getTasks())); // Get sorted deadline tasks
        ArrayList<Task> combinedList = new ArrayList<>(deadlineTasks); // Arrange deadline tasks first
        combinedList.addAll(nonDeadlineTasks); // Put the rest later
        taskList = combinedList;
        return;
    }

    /**
     * Retrieves and returns a sorted list of Deadline tasks from the given list of tasks,
     * sorted by chronological order, earliest to latest
     *
     * @param tasks The list of tasks.
     * @return A sorted ArrayList of Deadline tasks.
     */
    protected static ArrayList<Deadline> getSortedDeadlineTasks(ArrayList<Task> tasks) {
        ArrayList<Deadline> deadlineTasks = tasks.stream()
                                            .filter(task -> task instanceof Deadline)
                                            .map(task -> (Deadline) task)
                                            .collect(Collectors.toCollection(ArrayList::new));
        if (deadlineTasks.size() <= 1) {
            return deadlineTasks;
        } else if (deadlineTasks.size() == 2) {
            Deadline first = deadlineTasks.get(0);
            Deadline second = deadlineTasks.get(1);
            if (first.compareTo(second) > 0) {
                deadlineTasks.set(0, second);
                deadlineTasks.set(1, first);
            }
            return deadlineTasks;
        } else {
            int mid = deadlineTasks.size() / 2;
            ArrayList<Task> subList1 = new ArrayList<>(deadlineTasks.subList(0, mid));
            ArrayList<Task> subList2 = new ArrayList<>(deadlineTasks.subList(mid, deadlineTasks.size()));
            ArrayList<Deadline> wish1 = getSortedDeadlineTasks(subList1);
            ArrayList<Deadline> wish2 = getSortedDeadlineTasks(subList2);
            return zipDeadlineTasks(wish1, wish2);
        }
    }

    private static ArrayList<Deadline> zipDeadlineTasks(ArrayList<Deadline> l1, ArrayList<Deadline> l2) {
        if (l1.isEmpty() && l2.isEmpty()) {
            return new ArrayList<Deadline>();
        } else if (l1.isEmpty() && !l2.isEmpty()) {
            return l2;
        } else if (!l1.isEmpty() && l2.isEmpty()) {
            return l1;
        } else {
            Deadline first = l1.get(0);
            Deadline second = l2.get(0);
            ArrayList<Deadline> singleton = new ArrayList<>();
            if (first.compareTo(second) < 0) {
                singleton.add(first);
                singleton.addAll(zipDeadlineTasks(new ArrayList<>(l1.subList(1, l1.size())), l2));
            } else {
                singleton.add(second);
                singleton.addAll(zipDeadlineTasks(l1, new ArrayList<>(l2.subList(1, l2.size()))));
            }
            return singleton;
        }
    }
}