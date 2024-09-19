package karen.tasks;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import karen.commands.SortCommand.Order;

/**
 * TaskList class which manages the List of Tasks and contains methods to modify it
 */
public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public Task getTask(int i) {
        return this.tasks.get(i);
    }

    public int getSize() {
        return this.tasks.size();
    }

    public void addTask(Task t) {
        tasks.add(t);
    }

    public void deleteTask(int i) {
        tasks.remove(i);
    }

    public void markTask(int i) {
        tasks.get(i).mark();
    }

    public void unmarkTask(int i) {
        tasks.get(i).unmark();
    }

    /**
     * Returns an array of all the taskStrings for each <code>Task</code> in the <code>TaskList</code>
     * @return String[]
     */
    public String[] toTaskStrings() {
        if (this.tasks.isEmpty()) {
            return new String[] {};
        } else {
            String[] taskStrings = new String[this.tasks.size()];
            for (int i = 0; i < this.tasks.size(); i++) {
                taskStrings[i] = this.tasks.get(i).toString();
            }
            return taskStrings;
        }
    }

    /**
     * Returns a {@code List<String>} containing each Task's fileString
     * @return {@code List<String>}
     */
    public List<String> toFileStrings() {
        if (this.tasks.isEmpty()) {
            return new ArrayList<>();
        } else {
            List<String> fileStrings = new ArrayList<>();
            for (Task t : tasks) {
                fileStrings.add(t.toFileString());
            }
            return fileStrings;
        }
    }

    /**
     * Searches the List by name with the specified substring
     * @param searchWord String representing the substring to search with
     * @return a List of Task containing <code>Tasks</code> which satisfy the search condition
     */
    public List<Task> searchTasks(String searchWord) {
        List<Task> result = new ArrayList<>();
        for (Task t : this.tasks) {
            int found = t.getName().indexOf(searchWord);
            if (found != -1) { //found is -1 if task name does not contain searchWord
                result.add(t);
            }
        }
        return result;
    }

    /**
     * Sorts the TaskList by date, in ascending order
     */
    public void sortByDate(Order order) {
        Comparator<Task> dateComparator = (Task thisTask, Task otherTask) -> {
            if (thisTask instanceof Todo) {
                if (otherTask instanceof Todo) {
                    return thisTask.getName().compareTo(otherTask.getName());
                } else {
                    // Place Todos at front of TaskList
                    return -1;
                }
            } else if (thisTask instanceof Deadline) {
                LocalDateTime thisDate = ((Deadline) thisTask).getDueDate();
                if (otherTask instanceof Todo) {
                    return 1;
                } else if (otherTask instanceof Deadline) {
                    LocalDateTime otherDate = ((Deadline) otherTask).getDueDate();
                    return thisDate.compareTo(otherDate);
                } else {
                    LocalDateTime otherDate = ((Event) otherTask).getStartDate();
                    return thisDate.compareTo(otherDate);
                }
            } else {
                LocalDateTime thisDate = ((Event) thisTask).getStartDate();
                if (otherTask instanceof Todo) {
                    return 1;
                } else if (otherTask instanceof Deadline) {
                    LocalDateTime otherDate = ((Deadline) otherTask).getDueDate();
                    return thisDate.compareTo(otherDate);
                } else {
                    LocalDateTime otherDate = ((Event) otherTask).getStartDate();
                    return thisDate.compareTo(otherDate);
                }
            }
        };
        this.tasks.sort(dateComparator);

        if (order == Order.DESCENDING) {
            Collections.reverse(this.tasks);
        }
    }

    /**
     * Sorts the TaskList by alphabetical order
     */
    public void sortByAlphabet(Order order) {
        Comparator<Task> alphabetComparator = (Task thisTask, Task otherTask) -> {
            return thisTask.getName().compareTo(otherTask.getName());
        };
        this.tasks.sort(alphabetComparator);
        if (order == Order.DESCENDING) {
            Collections.reverse(this.tasks);
        }
    }

    /**
     * Converts a List of Task into a <code>TaskList</code>
     * @param listOfTask the <code>List</code> to convert
     */
    public static TaskList fromList(List<Task> listOfTask) {
        TaskList taskList = new TaskList();
        for (Task t : listOfTask) {
            taskList.addTask(t);
        }

        return taskList;
    }
}
