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
    private ArrayList<Task> taskList;  
    private int listIndex;

    public TaskList(boolean areTasksLoaded) {
        if (areTasksLoaded) {
            this.taskList = Storage.loadTasks();
            this.listIndex = this.taskList.size();
        } else {
            this.taskList = new ArrayList<>();
            this.listIndex = 0;
        }
    }

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

    public String[] delete(int index) throws TaskNotFoundException {
        if (index - 1 < 0 || index - 1 >= listIndex) {
            throw new TaskNotFoundException();
        }
        Task task = taskList.remove(index - 1);
        listIndex--;
        Storage.saveTasks(taskList);
        return new String[]{task.toString(), String.valueOf(listIndex)};
    }

    public ArrayList<Task> list() {
        return this.taskList;
    }

    public String mark(int index) throws TaskNotFoundException {
        if (index < 0 || index - 1 >= listIndex) {
            throw new TaskNotFoundException();
        }
        Task task = taskList.get(index - 1);
        task.mark();
        Storage.saveTasks(taskList);
        return task.toString();
    }

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
