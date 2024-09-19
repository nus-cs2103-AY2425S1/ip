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
            throw new XBotException("I cannot add a todo with empty description >.<");
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
        Task newTask = new ToDo(rest);
        list.add(newTask);
        String output = "Here comes another todo! \n" +
                "I've added this task:\n";
        output = output + (newTask.toString() + "\n");
        output = output + ("And now you have " + list.size() + " tasks in the list!! Jiayouu :D");
        return output;
    }
}

