package mediell;

import mediell.exception.IncorrectIndexException;
import mediell.task.Deadline;
import mediell.task.Event;
import mediell.task.Task;
import mediell.task.ToDo;

import java.util.Objects;
import java.util.stream.Stream;

/** Represents an array of Task. */
public class TaskList {
    private Task[] listItems;
    private int size;

    public TaskList() {
        listItems = new Task[100];
        size = 0;
    }

    /**
     * returns the tasks in the list.
     */
    public String displayList() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < size; i++) {
            output.append(i + 1).append(". ").append(listItems[i]).append("\n");
        }
        return output.toString();
    }

    public String displayFoundList(String keyword) {
        if (Objects.equals(keyword, "")) {
            return displayList();
        }
        StringBuilder output = new StringBuilder();
        int j = 0;
        for (int i = 0; i < size; i++) {
            if (listItems[i].find(keyword)) {
                output.append(j + 1).append(". ").append(listItems[i]).append("\n");
                j++;
            }
        }
        return output.toString();
    }

    public String displaySortedList() {
        Stream<Task> temp = Stream.of(listItems).limit(size).sorted();
        StringBuilder output = new StringBuilder();
        int i = 1;
        temp.forEach((t) -> {
            output.append(i).append(". ").append(t).append("\n");
        });
        return output.toString();
    }

    /**
     * Adds a task to the list.
     * @param item the format of the task
     */
    public void addTask(Task item) {
        listItems[size] = item;
        size++;
    }

    public Task getTask(int index) throws IncorrectIndexException {
        if (index >= size || index < 0) {
            throw new IncorrectIndexException();
        }
        return listItems[index];
    }

    /**
     * Marks a task in the list as completed.
     * @param index the index to mark
     */
    public void markItem(int index) throws IncorrectIndexException {
        if (index >= size || index < 0) {
            throw new IncorrectIndexException();
        }
        listItems[index].markCompleted();
    }

    /**
     * Marks a task in the list as uncompleted.
     * @param index the index to unmark
     */
    public void unmarkItem(int index) throws IncorrectIndexException {
        if (index >= size || index < 0) {
            throw new IncorrectIndexException();
        }
        listItems[index].markUncompleted();

    }

    /**
     * Deletes a task from the list.
     * @param index the index to delete
     */
    public void deleteTask(int index) throws IncorrectIndexException {
        if (index >= size || index < 0) {
            throw new IncorrectIndexException();
        }
        for (int i = index; i < size - 1; i++) {
            listItems[i] = listItems[i + 1];
        }
        size--;
    }

    /**
     * Exports all the task from the list
     * @return String[] the tasks in an array of string
     */
    public String[] exportTasks() {
        String[] temp = new String[size];
        for (int i = 0; i < size; i++) {
            temp[i] = listItems[i].taskToStorageFormat();
        }
        return temp;
    }

    /**
     * Initialises all tasks into TaskList
     * @param tasks string of tasks
     */
    public void initTasks(String[] tasks) {
        size = tasks.length;
        for (int i = 0; i < size; i++) {
            if (ToDo.isToDoFormat(tasks[i])) {
                listItems[i] = new ToDo();
                listItems[i].initStorageFormat(tasks[i]);
            }
            else if (Event.isEventFormat(tasks[i])) {
                listItems[i] = new Event();
                listItems[i].initStorageFormat(tasks[i]);
            }
            else if (Deadline.isDeadlineFormat(tasks[i])) {
                listItems[i] = new Deadline();
                listItems[i].initStorageFormat(tasks[i]);
            }
            else {
                listItems[i] = new Event();
                listItems[i].initStorageFormat(tasks[i]);
            }
        }
    }
}
