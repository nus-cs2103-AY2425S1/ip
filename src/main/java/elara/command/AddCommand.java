package elara.command;

import elara.storage.Storage;

import elara.task.DeadlineException;
import elara.task.DeadlineTask;
import elara.task.EventException;
import elara.task.EventTask;
import elara.task.Task;
import elara.task.TaskList;
import elara.task.ToDoException;
import elara.task.ToDoTask;

import elara.ui.Ui;

public class AddCommand implements Command {
    private final String commandType;
    private final String taskDetails;

    public AddCommand(String commandType, String taskDetails) {
        this.commandType = commandType;
        this.taskDetails = taskDetails;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task newTask = switch(commandType) {
            case "todo":
                yield parseToDo(taskDetails);
            case "deadline":
                yield parseDeadline(taskDetails);
            case "event":
                yield parseEvent(taskDetails);
            default:
                throw new IllegalArgumentException("Unknown command type: " + commandType);
        };
        taskList.addTask(newTask);
        ui.showAddTaskMessage(newTask, taskList);
        storage.write(taskList);
    }

    public static Task parseToDo(String taskDetails) {
        try {
            if (taskDetails.isEmpty()) {
                throw new ToDoException();
            }
            return new ToDoTask(taskDetails);
        } catch (ToDoException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static Task parseDeadline(String taskDetails) {
        String[] deadlineArgs = taskDetails.split("/by ");
        try {
            if (deadlineArgs.length == 2) {
                return new DeadlineTask(deadlineArgs[0].trim(), deadlineArgs[1].trim());
            }
            throw new DeadlineException();
        } catch (DeadlineException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static Task parseEvent(String taskDetails) {
        String[] eventArgs = taskDetails.split("/from |/to ");
        try {
            if (eventArgs.length == 3) {
                return new EventTask(eventArgs[0].trim(), eventArgs[1].trim(), eventArgs[2].trim());
            }
            throw new EventException();
        } catch (EventException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
