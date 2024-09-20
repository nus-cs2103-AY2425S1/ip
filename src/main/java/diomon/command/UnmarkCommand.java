package diomon.command;

import diomon.Storage;
import diomon.task.TaskList;

public class UnmarkCommand extends Command{
    public UnmarkCommand(String input) {
        this.input = input;
    }
    @Override
    public void execute(TaskList tasks, Storage storage) {
        assert input != null;
        try {
            int i = Integer.parseInt(input);
            tasks.unmark( i- 1);
            setResponse(String.format("( %s ) has been unmarked\n" +
                    "Ya did a little oopies, just like your mom", tasks.get(i - 1)));
        } catch (NumberFormatException e) {
            setResponse("Argument given for undoing a completed task is wrong, please try again");
        } catch (ArrayIndexOutOfBoundsException e) {
            setResponse("Index out of bound");
        }
    }
}
