package terminator.command;

import terminator.task.Task;

import java.util.ArrayList;

public class ListCommand extends Command {

    private final String input;

    public ListCommand(String input) {
        super();
        this.input = input;
    }

    private static final String ERR_MSG = """
            List command takes no arguments.
            
            Usage: list""";

    @Override
    public void execute(ArrayList<Task> todoList) throws DukeException {
        if (!(input == null)) {
            throw new DukeException(ERR_MSG);
        }
        System.out.println("Listing current mission objectives:\n");
        for (int i = 0; i < todoList.size(); i++) {
            Task currentTask = todoList.get(i);
            System.out.println((i + 1) + "." + currentTask);
        }
    }
}
