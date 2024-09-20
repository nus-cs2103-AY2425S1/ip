package diomon.command;

import diomon.Storage;
import diomon.exception.MissingInputException;
import diomon.parser.Parser;
import diomon.task.Task;
import diomon.task.TaskList;

import java.util.List;
import java.util.Objects;

public class UnmarkCommand extends Command{
    public UnmarkCommand(String input) {
        this.input = input;
    }
    @Override
    public void execute(TaskList tasks, Storage storage) {
        try {
            if (input == null) {
                throw new MissingInputException();
            }

            StringBuilder response = new StringBuilder();
            List<Integer> indexList = Parser.processNumbers(input);
            for (Integer i : indexList) {
                tasks.unmark(i - 1);
                response.append(String.format("( %s ) has been unmarked\n", tasks.get(i - 1)));
                assert Objects.equals(tasks.get(i - 1).getStatusIcon(), Task.INCOMPLETEICON);
            }
            response.append("Ya did a little oopies, just like your mom");

            setResponse(response.toString());
        } catch (NumberFormatException e) {
            setResponse("Argument given for undoing a completed task is wrong, please try again");
        } catch (ArrayIndexOutOfBoundsException e) {
            setResponse("Index out of bound");
        } catch (RuntimeException e) {
            setResponse("Something went wrong, please check your input");
        }
    }
}
