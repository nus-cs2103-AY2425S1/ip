package diomon.command;

import diomon.Storage;
import diomon.task.TaskList;
import diomon.ui.Ui;

public class UnmarkCommand extends Command{
    public UnmarkCommand(String input) {
        this.input = input;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        assert input != null;
        try {
            int i = Integer.parseInt(input);
            tasks.unmark( i- 1);
            setResponse(String.format("( %s ) has been unmarked\n" +
                    "Ya did a little oopies, just like your mom", tasks.get(i - 1)));
        } catch (NumberFormatException e) {
            setResponse("Param given for unmarking a task is wrong, please try again");
        } catch (ArrayIndexOutOfBoundsException e) {
            setResponse("Index out of bound");
        }
    };
}
