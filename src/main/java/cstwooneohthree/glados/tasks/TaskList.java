package cstwooneohthree.glados.tasks;

import java.time.LocalDate;
import java.util.ArrayList;

import cstwooneohthree.glados.enums.TaskType;
import cstwooneohthree.glados.exceptions.GladosException;
import cstwooneohthree.glados.exceptions.TaskNotFoundException;
import cstwooneohthree.glados.utils.ParsedInfo;
import cstwooneohthree.glados.utils.Parser;
import cstwooneohthree.glados.utils.Storage;

/**
* TaskList is used to store and manage tasks in GLaDOS.
*
* @author jayjay19630
*/
public class TaskList {

    /* ArrayList to keep track of all tasks */
    private ArrayList<Task> tasks;
    /* Integer to keep track of tasks length */
    private int listIndex;

    /**
     * Constructs a new TaskList object.
     * Tasks are loaded depending on argument.
     *
     * @param shouldLoadTasks Boolean denoting whether tasks should be loaded.
     */
    public TaskList(boolean shouldLoadTasks) {
        if (shouldLoadTasks) {
            this.tasks = Storage.loadTasks();
            this.listIndex = this.tasks.size();
        } else {
            this.tasks = new ArrayList<>();
            this.listIndex = 0;
        }
    }

    /**
     * Adds a new task to tasks based on taskType.
     * Saves tasks to data memory.
     *
     * @param taskType Type of task to be added.
     * @param input Arguments after add comand.
     * @return Added task description and updated index in String array.
     * @throws GladosException If there is a parsing or task list error.
     */
    public String[] add(TaskType taskType, String input) throws GladosException {

        assert taskType == TaskType.TODO || taskType == TaskType.DEADLINE || taskType == TaskType.EVENT;

        switch (taskType) {
        case TODO:
            ParsedInfo parsedTodoInputs = Parser.parseTask(taskType, input);
            tasks.add(new Todo(
                    parsedTodoInputs.getDescription()));
            break;
        case EVENT:
            ParsedInfo parsedEventInputs = Parser.parseTask(taskType, input);
            LocalDate[] eventDates = parsedEventInputs.getDates();
            tasks.add(new Event(
                    parsedEventInputs.getDescription(),
                    eventDates[0],
                    eventDates[1]));
            break;
        case DEADLINE:
            ParsedInfo parsedDeadlineInputs = Parser.parseTask(taskType, input);
            tasks.add(new Deadline(
                    parsedDeadlineInputs.getDescription(),
                    parsedDeadlineInputs.getDates()[0]));
            break;
        default:
            break;
        }
        listIndex++;

        Storage.saveTasks(tasks);

        return new String[]{tasks.get(listIndex - 1).toString(), String.valueOf(listIndex)};
    }

    /**
     * Deletes task in tasks based on index.
     * If index is non applicable, exception is thrown.
     *
     * @param index Index of task to be deleted.
     * @return Deleted task description and updated index.
     * @throws TaskNotFoundException If index is outside of array list.
     */
    public String[] delete(int index) throws TaskNotFoundException {

        assert listIndex == tasks.size();

        if (index - 1 < 0 || index - 1 >= listIndex) {
            throw new TaskNotFoundException();
        }

        Task task = tasks.remove(index - 1);
        listIndex--;

        Storage.saveTasks(tasks);

        return new String[]{task.toString(), String.valueOf(listIndex)};
    }

    /**
     * Returns list of tasks.
     */
    public ArrayList<Task> list() {
        return this.tasks;
    }

    /**
     * Marks tasks as done based on index in tasks.
     *
     * @param index Index of task to be marked in tasks.
     * @return Task description.
     * @throws TaskNotFoundException If index is not in array.
     */
    public String mark(int index) throws TaskNotFoundException {

        assert listIndex == tasks.size();

        if (index < 0 || index - 1 >= listIndex) {
            throw new TaskNotFoundException();
        }

        Task task = tasks.get(index - 1);
        task.mark();

        Storage.saveTasks(tasks);

        return task.toString();
    }

    /**
     * Unmarks tasks based on index in tasks.
     *
     * @param index Index of task to be unmarked in tasks.
     * @return Task description.
     * @throws TaskNotFoundException If index is not in array.
     */
    public String unmark(int index) throws TaskNotFoundException {

        assert listIndex == tasks.size();

        if (index < 0 || index - 1 >= listIndex) {
            throw new TaskNotFoundException();
        }

        Task task = tasks.get(index - 1);
        task.unmark();

        Storage.saveTasks(tasks);
        return task.toString();
    }

    /**
     * Finds task based on string name.
     *
     * @param input Substring to be included in name.
     * @return Task found.
     */
    public ArrayList<Task> find(String input) {
        ArrayList<Task> foundTasks = new ArrayList<>();

        for (Task task : tasks) {
            if (task.getDescription().contains(input)) {
                foundTasks.add(task);
            }
        }

        return foundTasks;
    }
}
