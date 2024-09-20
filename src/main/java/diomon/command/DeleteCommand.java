package diomon.command;

import diomon.Storage;
import diomon.exception.MissingInputException;
import diomon.parser.Parser;
import diomon.task.TaskList;

import java.util.List;

public class DeleteCommand extends Command{
    public DeleteCommand(String input) {
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
            for (Integer i = 0; i < indexList.size(); i++) {
                Integer index = indexList.get(i);
                response.append(String.format("Task ( %s ) has been thanosed\n", tasks.get(index - 1 - i)));
                tasks.remove( index - 1 - i);
            }
            setResponse(response.toString());
        } catch (NumberFormatException e) {
            setResponse("Param given for marking a task is wrong, please try again");
        } catch (IndexOutOfBoundsException e) {
            setResponse("Index out of bound, please try again");
        }
    }
}
