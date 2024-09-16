package snah.commands;

import snah.TaskList;
import snah.errors.ParsingException;
import snah.task.ToDo;
import snah.util.Parser;
import snah.util.Storage;

/**
 * CreateTodo command to add a todo task
 */
public class CreateTodo extends Command {
    public CreateTodo(String input) {
        super(input);
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws ParsingException {
        String[] todoPayload = Parser.getTodoPayload(getInput());
        tasks.add(new ToDo(todoPayload[0]));
        tasks.save(storage);
        return String.format("Added todo to list\n  %s", tasks.get(tasks.size() - 1));
    }

}
