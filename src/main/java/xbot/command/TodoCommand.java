package xbot.command;

import xbot.TaskList;
import xbot.exception.XBotException;
import xbot.storage.Storage;
import xbot.task.Task;
import xbot.task.ToDo;
import xbot.ui.Ui;

/**
 * Handles the "todo" command.
 */
public class TodoCommand extends AddCommand {
    @Override
    public String execute(TaskList list, Ui ui, Storage storage, String rest) throws XBotException {
        if (rest.trim().isEmpty()) {
            throw new XBotException("The description of the todo cannot be empty!");
        }
        String output = addTodo(list, rest);
        storage.saveTask(list);
        return output;
    }

    /**
     * Adds a new ToDo task to the task list.
     *
     * @param rest The description of the ToDo task.
     */
    public String addTodo(TaskList list, String rest) {
        System.out.println("Got it. I've added this task:");
        Task newTask = new ToDo(rest);
        String output;
        list.add(newTask);
        output = (newTask.toString() + "\n");
        output = output + ("Now you have " + list.size() + " tasks in the list.");
        return output;
    }
}

