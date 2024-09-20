package flychat.command;

import flychat.core.Parser;
import flychat.core.TaskList;
import flychat.core.Ui;
import flychat.tasks.Todo;

public class AddTodoCommand extends Command {
    @Override
    public String execute(TaskList taskList, Ui ui, Parser parser, String inputString) {
        Todo newToDo = Todo.createNewTodo(parser.getTaskDescription(inputString), false);
        taskList.addToList(newToDo);
        return "Task added:\n  " + newToDo + "\nNow you have " + taskList.getSize()
                + " tasks in the list. HAVE FUN ^o^";
    }
}
