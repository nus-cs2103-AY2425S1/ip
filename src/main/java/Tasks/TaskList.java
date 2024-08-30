package Tasks;

import java.util.ArrayList;

import Exceptions.*;

/**
 * Represents a list of tasks stored by the Testament chatbot.
 */
public class TaskList {
    private ArrayList<Task> listOfTasks;

    /**
     * Constructor for TaskList.
     */
    public TaskList() {
        listOfTasks = new ArrayList<>();
    }

    /**
     * Adds a task to the taskList.
     *
     * @param task Task to add.
     */
    public void add(Task task) {
        listOfTasks.add(task);
    }

    /**
     * Marks a task in the taskList.
     * If the task does not exist in the taskList, throws NotInTaskListException.
     *
     * @param i taskNumber of task to mark.
     * @throws NotInTaskListException thrown if taskNumber does not exist in taskList.
     */
    public void mark(int i) throws NotInTaskListException {
        if (i > listOfTasks.size() || i < 1) {
            throw new NotInTaskListException();
        }
        listOfTasks.get(i - 1).done();
    }

    /**
     * unMarks a task in the taskList.
     * If the task does not exist in the taskList, throws NotInTaskListException.
     *
     * @param i taskNumber of task to unMark.
     * @throws NotInTaskListException thrown if taskNumber does not exist in taskList.
     */
    public void unMark(int i) throws NotInTaskListException {
        if (i > listOfTasks.size() || i < 1) {
            throw new NotInTaskListException();
        }
        listOfTasks.get(i - 1).undone();
    }

    /**
     * Retrieves a task in the taskList.
     * If the task does not exist in the taskList, throws NotInTaskListException.
     *
     * @param i taskNumber of task to retrieve.
     * @throws NotInTaskListException thrown if taskNumber does not exist in taskList.
     */
    public Task getTask(int i) throws NotInTaskListException {
        if (i > listOfTasks.size() || i < 1) {
            throw new NotInTaskListException();
        }
        return listOfTasks.get(i - 1);
    }

    /**
     * Deletes a task in the taskList.
     * If the task does not exist in the taskList, throws NotInTaskListException.
     *
     * @param i taskNumber of task to retrieve.
     * @throws NotInTaskListException thrown if taskNumber does not exist in taskList.
     */
    public void deleteTask(int i) throws NotInTaskListException {
        if (i > listOfTasks.size() || i < 1) {
            throw new NotInTaskListException();
        }
        listOfTasks.remove(i - 1);
    }

    /**
     * Returns size of tasklist.
     *
     * @return Size of tasklist.
     */
    public int getSize() {
        return listOfTasks.size();
    }

    /**
     * Returns a string that stores the data of all tasks in the taskList.
     *
     * @return String that stores the data of all tasks in taskList.
     */
    public String fileTaskInfo() {
        StringBuilder str = new StringBuilder();
        for (Task t : listOfTasks) {
            str.append(t.infoForFile());
            str.append("\n");
        }
        return str.toString();
    }

    /**
     * returns toString representation of taskList.
     *
     * @return toString representation of taskList.
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        int length = listOfTasks.size();
        for (int i = 0; i < length; i++) {
            str.append((i + 1) + ". ");
            str.append(listOfTasks.get(i).toString());
            if (i != length - 1) {
                str.append("\n");
            }
        }
        return str.toString();
    }
}
