package duke;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Stores Tasks as a list.
 */
public class TaskList {
    private ArrayList<Task> userInputs;

    public TaskList(ArrayList<Task> userInputs) {
        setTaskList(userInputs);
    }

    /*
    IntelliJ IDEA AI recommended removing "Task" from the typing of the ArrayList to make use of Generics.
     */
    public TaskList() {
        userInputs = new ArrayList<>();
    }

    private void setTaskList(ArrayList<Task> userInputs) {
        this.userInputs = userInputs;
    }

    public void set(int idx, boolean bool) throws TaskListOutOfBoundsException {
        if (idx < 0 || idx >= userInputs.size()) {
            throw new TaskListOutOfBoundsException();
        }
        userInputs.get(idx).setDone(bool);
    }

    public Task get(int idx) throws TaskListOutOfBoundsException {
        if (idx < 0 || idx > userInputs.size() - 1) {
            throw new TaskListOutOfBoundsException();
        }
        return userInputs.get(idx);
    }

    public void add(Task task) {
        userInputs.add(task);
    }

    public ArrayList<Task> getTaskList() {
        return userInputs;
    }

    /**
     * Deletes the specified index from the TaskList object.
     * @param idx The index to be deleted
     * @throws TaskListOutOfBoundsException
     */
    public void delete(int idx) throws TaskListOutOfBoundsException {
        if (idx < 0 || idx > userInputs.size() - 1) {
            throw new TaskListOutOfBoundsException();
        }
        userInputs.remove(idx);
    }

    public int size() {
        return userInputs.size();
    }

    /**
     * Snoozes the task specified by the index.
     * @param idx The 
     * @param newDate
     * @throws TaskListOutOfBoundsException
     */
    public void snooze(int idx, LocalDate newDate) throws TaskListOutOfBoundsException {
        if (idx < 0 || idx > userInputs.size() - 1) {
            throw new TaskListOutOfBoundsException();
        }
        userInputs.get(idx).snooze(newDate);
    }

    /**
     * Searches for Tasks that have a description matching or including the input string.
     * @param matching The string that is to be checked against the description of the listed tasks.
     * @return The tasks that have a description that includes the parameter.
     */
    public TaskList findAll(String... matching) {
        TaskList taskList = new TaskList();
        for (Task task : userInputs) {
            for (String match : matching) {
                if (task.name.contains(match)) {
                    taskList.add(task);
                    break;
                }
            }
        }
        return taskList;
    }
}
