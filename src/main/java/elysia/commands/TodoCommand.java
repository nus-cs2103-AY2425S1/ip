package elysia.commands;

import elysia.exceptions.EmptyTaskArgumentsException;
import elysia.storage.FileReaderWriter;
import elysia.tasks.TaskList;
import elysia.tasks.Todo;
import elysia.ui.Message;

import java.util.Objects;

public class TodoCommand extends Command {
    String[] args;

    public TodoCommand (TaskList taskList, FileReaderWriter fileReaderWriter, String[] args) {
        super(taskList, fileReaderWriter);
        this.args = args;
    }

    @Override
    public String execute() throws EmptyTaskArgumentsException {
        StringBuilder output = new StringBuilder();
        if (args.length == 1) {
            throw new EmptyTaskArgumentsException(args[0]);
        }
        Todo todo = new Todo(args[1]);
        taskList.addTask(todo);
        assert(!Objects.equals(taskList.getSizeAsString(), "0"));
        output.append("Added the task below to your list~\n").append(todo.toString()).append("\n");
        output.append("Wow! You now have ").append(taskList.getSizeAsString()).append(" tasks in your list!");
        return output.toString();
    }
}
