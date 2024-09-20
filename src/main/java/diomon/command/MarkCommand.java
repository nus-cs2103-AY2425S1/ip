package diomon.command;

import diomon.Storage;
import diomon.task.TaskList;

public class MarkCommand extends Command{
    public MarkCommand(String input) {
        this.input = input;
    }
    @Override
    public void execute(TaskList tasks, Storage storage) {
        assert input != null;
        try {
            int i = Integer.parseInt(input);
            tasks.mark( i- 1);
            setResponse(String.format("( %s ) completed!!!", tasks.get(i - 1)));
        } catch (NumberFormatException e) {
            setResponse("Argument given for completing a task is wrong, please try again");
        } catch (IndexOutOfBoundsException e) {
            setResponse("Index out of bound, please try again");
        }
    }
}
