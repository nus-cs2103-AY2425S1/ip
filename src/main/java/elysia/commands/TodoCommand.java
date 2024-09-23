package elysia.commands;

import java.util.Objects;

import elysia.exceptions.EmptyTaskArgumentsException;
import elysia.exceptions.WrongArgumentException;
import elysia.storage.FileReaderWriter;
import elysia.tasks.TaskList;
import elysia.tasks.Todo;

/**
 * Represents a command to add a todo task to the task list.
 * Extends the {@code Command} class and handles the parsing and validation of todo task arguments.
 */
public class TodoCommand extends Command {

    /**
     * Constructs a {@code TodoCommand} with the specified task list, file reader/writer, and command arguments.
     * Command format: todo [optional: r] [DESCRIPTION]
     *
     * @param taskList the task list to which the todo task will be added.
     * @param fileReaderWriter the file reader/writer for saving or loading task data.
     * @param args the command arguments, where the first argument is the command type and
     *             the second is the todo task description.
     */
    public TodoCommand(TaskList taskList, FileReaderWriter fileReaderWriter, String[] args) {
        super(taskList, fileReaderWriter);
        this.args = args;
    }

    /**
     * Executes the {@code TodoCommand} to add a new todo task to the task list.
     * Validates the command arguments and creates a new todo task with the provided description.
     *
     * @return a string indicating the result of adding the todo task,
     *     including confirmation and the current task count.
     * @throws EmptyTaskArgumentsException if no description is provided for the todo task.
     */
    @Override
    public String execute() throws EmptyTaskArgumentsException, WrongArgumentException {
        StringBuilder output = new StringBuilder();

        if (args.length == 1) {
            throw new EmptyTaskArgumentsException(args[0]);
        }

        String[] splitArgs = args[1].split(" ", 2);
        boolean isRecurring;
        String description;
        if (Objects.equals(splitArgs[0], "r")) {
            isRecurring = true;
            if (splitArgs.length == 1) {
                throw new EmptyTaskArgumentsException(args[0]);
            }
            description = splitArgs[1];
        } else {
            isRecurring = false;
            description = args[1];
        }
        Todo todo = new Todo(description, isRecurring);
        taskList.addTask(todo);
        assert(!Objects.equals(taskList.getSizeAsString(), "0"));
        output.append("Added the task below to your list~\n").append(todo).append("\n");
        output.append("Wow! You now have ").append(taskList.getSizeAsString()).append(" tasks in your list!");
        return output.toString();
    }
}
