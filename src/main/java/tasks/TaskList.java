package tasks;

import java.time.LocalDate;

import java.util.ArrayList;

import enums.TaskType;

import exceptions.GladosException;
import exceptions.TaskNotFoundException;

import utils.ParsedInfo;
import utils.Parser;
import utils.Storage;

public class TaskList {

    /* ArrayList to keep track of all tasks */
    private ArrayList<Task> taskList;  
    /* Integer to keep track of taskList length */
    private int listIndex;

    /**
     * Constructs a new TaskList object.
     * Tasks are loaded depending on argument.
     * 
     * @param areTasksLoaded Boolean denoting whether tasks should be loaded.
     */
    public TaskList(boolean areTasksLoaded) {
        if (areTasksLoaded) {
            this.taskList = Storage.loadTasks();
            this.listIndex = this.taskList.size();
        } else {
            this.taskList = new ArrayList<>();
            this.listIndex = 0;
        }
    }

    /**
     * Adds a new task to the task list based on taskType.
     * Saves taskList to data memory.
     * 
     * @param taskType Type of task to be added.
     * @param input Arguments after add comand.
     * @return Added task description and updated index in String array.
     * @throws GladosException If there is a parsing or task list error.
     */
    public String[] add(TaskType taskType, String input) throws GladosException {
        switch (taskType) {
        case TODO:
            ParsedInfo parsedTodoInputs = Parser.parseTask(taskType, input);
            taskList.add(new Todo(parsedTodoInputs.getDescription()));
            break;
        case EVENT:
            ParsedInfo parsedEventInputs = Parser.parseTask(taskType, input);
            LocalDate[] eventDates = parsedEventInputs.getDates();
            taskList.add(new Event(
                    parsedEventInputs.getDescription(), 
                    eventDates[0], 
                    eventDates[1]));
            break;
        case DEADLINE:
            ParsedInfo parsedDeadlineInputs = Parser.parseTask(taskType, input);
            taskList.add(new Deadline(
                    parsedDeadlineInputs.getDescription(), 
                    parsedDeadlineInputs.getDates()[0]));
            break;
        default:
            break;
        }
        listIndex++;
        Storage.saveTasks(taskList);
        return new String[]{taskList.get(listIndex - 1).toString(), String.valueOf(listIndex)};
    }

    /**
     * Deletes task in taskList based on index.
     * If index is non applicable, exception is thrown.
     * 
     * @param index Index of task to be deleted.
     * @return Deleted task description and updated index.
     * @throws TaskNotFoundException If index is outside of array list.
     */
    public String[] delete(int index) throws TaskNotFoundException {
        if (index - 1 < 0 || index - 1 >= listIndex) {
            throw new TaskNotFoundException();
        }
        Task task = taskList.remove(index - 1);
        listIndex--;
        Storage.saveTasks(taskList);
        return new String[]{task.toString(), String.valueOf(listIndex)};
    }

    /**
     * Returns list of tasks.
     */
    public ArrayList<Task> list() {
        return this.taskList;
    }

    /**
     * Marks tasks as done based on index in taskList.
     * 
     * @param index Index of task to be marked in taskList.
     * @return Task description.
     * @throws TaskNotFoundException If index is not in array.
     */
    public String mark(int index) throws TaskNotFoundException {
        if (index < 0 || index - 1 >= listIndex) {
            throw new TaskNotFoundException();
        }
        Task task = taskList.get(index - 1);
        task.mark();
        Storage.saveTasks(taskList);
        return task.toString();
    }

    /**
     * Unmarks tasks based on index in taskList.
     * 
     * @param index Index of task to be unmarked in taskList.
     * @return Task description.
     * @throws TaskNotFoundException If index is not in array.
     */
    public String unmark(int index) throws TaskNotFoundException {
        if (index < 0 || index - 1>= listIndex) {
            throw new TaskNotFoundException();
        }
        Task task = taskList.get(index - 1);
        task.unmark();
        Storage.saveTasks(taskList);
        return task.toString();
    }
}
