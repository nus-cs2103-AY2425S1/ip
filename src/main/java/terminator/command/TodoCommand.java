package terminator.command;

import terminator.task.Task;
import terminator.task.TodoTask;

import java.util.ArrayList;

public class TodoCommand extends Command {

    private final String input;

    public TodoCommand(String input) {
        this.input = input;
    }

    private static final String ERR_MSG = """
            Todo description cannot be empty.
            
            Usage: todo <description>""";

    @Override
    public void execute(ArrayList<Task> todoList) throws DukeException {
        if (input == null) {
            throw new DukeException(ERR_MSG);
        }
        String description = input.trim();
        if (description.isEmpty()) {
            throw new DukeException(ERR_MSG);
        }
        Task t = new TodoTask(description);
        todoList.add(t);
        System.out.println("Mission parameters updated. Added new objective:\n\n" + t);
    }
}
