package tasks;
import java.util.ArrayList;
import enums.TaskType;
import exceptions.GladosException;
import exceptions.TaskNotFoundException;
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
            String[] parsedTodoInputs = Parser.parseTask(taskType, input);
            taskList.add(new Todo(parsedTodoInputs[0]));
            break;
        case EVENT:
            String[] parsedEventInputs = Parser.parseTask(taskType, input);
            taskList.add(new Event(parsedEventInputs[0], parsedEventInputs[1], parsedEventInputs[2]));
            break;
        case DEADLINE:
            String[] parsedDeadlineInputs = Parser.parseTask(taskType, input);
            taskList.add(new Deadline(parsedDeadlineInputs[0], parsedDeadlineInputs[1]));
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
