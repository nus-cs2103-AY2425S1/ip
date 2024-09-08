package processes;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import exceptions.InvalidDateException;
import exceptions.InvalidTaskNameException;
import exceptions.TaskOutOfBoundsError;
import tasks.DeadLine;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;



/**
 * TaskList class stores the state of the programme.
 * It stores user inputs in previous runs of the programme (provided they are loaded in).
 * Contains various methods to manipulate the tasks
 */
public class TaskList {

    private final ArrayList<Task> taskList;


    /**
     * Constructor for TaskList object.
     * Creates the data structure used to store the user's tasks.
     * The data structure used is an ArrayList
     *
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }


    /**
     * Getter method that returns the current list of tasks
     *
     * @return The current list of tasks
     *
     */
    public ArrayList<Task> getTasks() {
        return taskList;
    }

    /**
     * Does not return anything.
     * Taking in the user input from an Ui object, create a ToDo class and add it to the current list of tasks
     *
     * @param arg The string received from the Ui object to create a ToDo object.
     * @throws InvalidTaskNameException If no task name is provided.
     */
    public Task addToDo(String arg) throws InvalidTaskNameException {
        ToDo newToDo = new ToDo(arg);
        taskList.add(newToDo);
        return newToDo;
    }

    /**
     * Does not return anything.
     * Taking in the user input from an Ui object, create a DeadLine class and add it to the current list of tasks
     *
     * @param arg The string received from the Ui object to create a DeadLine object.
     * @throws InvalidTaskNameException If no task name is provided.
     * @throws InvalidDateException If invalid date/no date is provided.
     */
    public Task addDeadline(String arg) throws InvalidTaskNameException, InvalidDateException {
        Task newDeadline = new DeadLine(arg);
        taskList.add(newDeadline);
        return newDeadline;
    }


    /**
     * Does not return anything.
     * Taking in the user input from an Ui object, create an Event class and add it to the current list of tasks
     *
     * @param arg The string received from the Ui object to create an Event object.
     * @throws InvalidTaskNameException If no task name is provided.
     * @throws InvalidDateException If invalid date/no date is provided.
     */
    public Task addEvent(String arg) throws InvalidDateException, InvalidTaskNameException {
        Task newEvent = new Event(arg);
        taskList.add(newEvent);
        return newEvent;
    }

    /**
     * Does not return anything.
     * Removes the task at the specified index from the current list of tasks
     *
     * @param index The index of the object to be removed.
     *
     */
    public Task deleteTask(int index) throws TaskOutOfBoundsError {
        if (index < 1 || index > taskList.size()) {
            throw new TaskOutOfBoundsError(index);
        }
        index--;
        Task currTask = taskList.get(index);
        taskList.remove(index);
        return currTask;

    }

    /**
     * Does not return anything.
     * Receive index of task to mark and whether the programme should mark or unmark the task
     * If isMark is true, the task will be marked, and if isMark is false, the task will be unmarked.
     *
     * @param index The index of the task to mark/unmark.
     * @param isMark Determines whether the task should be marked or not.
     * @throws TaskOutOfBoundsError If index provided is not within taskList.
     */
    public Task markAndUnmark(int index, boolean isMark) throws TaskOutOfBoundsError {
        if (index == Integer.MAX_VALUE) {
            throw new RuntimeException();
        } else if (index < 1 || index > taskList.size()) {
            throw new TaskOutOfBoundsError(index);
        } else {
            index--;
            Task curr = taskList.get(index);
            if (isMark) {
                curr.mark();
            } else {
                curr.unMark();
            }
            return curr;
        }
    }

    /**
     * Does not return anything.
     * Receive the prompt from the user and searches the current list of tasks for task names that contain the prompt.
     * After getting the list of matching tasks, print them out to the terminal
     *
     * @param prompt The prompt provided by the user.
     *
     */
    public ArrayList<Task> find(String prompt) {
        Stream<Task> stream = this.taskList.stream().filter(t -> t.getName().contains(prompt));
        return stream.collect(Collectors.toCollection(ArrayList::new));
    }
}
